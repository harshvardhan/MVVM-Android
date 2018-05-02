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

package hellofresh.test.com.hellofresh.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.repository.RecipeRepository;
import hellofresh.test.com.hellofresh.util.AbsentLiveData;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

public class RecipeListViewModel extends ViewModel {

    private final MutableLiveData<String> query = new MutableLiveData<>();

    private final LiveData<Resource<List<Recipe>>> recipes;

    @Inject
    public RecipeListViewModel(RecipeRepository recipeRepository) {
        recipes = Transformations.switchMap(query, search -> {
            if (search == null || search.trim().length() == 0) {
                return AbsentLiveData.create();
            } else {
                return recipeRepository.getRecipes();
            }
        });
    }

    @VisibleForTesting
    public LiveData<Resource<List<Recipe>>> getRecipes() {
        return recipes;
    }

    public void setQuery() {
        query.setValue("1");
    }

    public void refresh() {
        if (query.getValue() != null) {
            query.setValue(query.getValue());
        }
        else
            query.setValue("");
    }
}
