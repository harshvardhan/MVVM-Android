/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hellofresh.test.com.hellofresh.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import hellofresh.test.com.hellofresh.api.ApiResponse;
import hellofresh.test.com.hellofresh.api.HelloFreshService;
import hellofresh.test.com.hellofresh.db.HelloFreshDb;
import hellofresh.test.com.hellofresh.db.RecipeDao;
import hellofresh.test.com.hellofresh.util.InstantAppExecutors;
import hellofresh.test.com.hellofresh.util.TestUtil;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

import static hellofresh.test.com.hellofresh.util.ApiUtil.successCall;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class RecipeRepositoryTest {
    private RecipeRepository repository;
    private RecipeDao dao;
    private HelloFreshService service;
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Before
    public void init() {
        dao = mock(RecipeDao.class);
        service = mock(HelloFreshService.class);
        HelloFreshDb db = mock(HelloFreshDb.class);
        when(db.recipeDao()).thenReturn(dao);
        repository = new RecipeRepository(new InstantAppExecutors(), db, dao, service);
    }

    @Test
    public void loadRecipeFromNetwork() throws IOException {
        MutableLiveData<Recipe> dbData = new MutableLiveData<>();
        when(dao.getRecipe("533143aaff604d567f8b4571")).thenReturn(dbData);

        Recipe recipe = null;
        try {
            recipe = TestUtil.createRecipe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LiveData<ApiResponse<Recipe>> call = successCall(recipe);
        when(service.getRecipe()).thenReturn(call);

        LiveData<Resource<Recipe>> data = repository.getRecipe("533143aaff604d567f8b4571");
        verify(dao).getRecipe("533143aaff604d567f8b4571");
        verifyNoMoreInteractions(service);

        Observer observer = mock(Observer.class);
        data.observeForever(observer);
        verifyNoMoreInteractions(service);
        verify(observer).onChanged(Resource.loading(null));
        MutableLiveData<Recipe> updatedDbData = new MutableLiveData<>();
        when(dao.getRecipe("533143aaff604d567f8b4571")).thenReturn(updatedDbData);

        dbData.postValue(null);
        verify(service).getRecipe();
        verify(dao).insert(recipe);

        updatedDbData.postValue(recipe);
        verify(observer).onChanged(Resource.success(recipe));
    }
}