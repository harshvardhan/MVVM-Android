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

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import hellofresh.test.com.hellofresh.AppExecutors;
import hellofresh.test.com.hellofresh.api.ApiResponse;
import hellofresh.test.com.hellofresh.api.HelloFreshService;
import hellofresh.test.com.hellofresh.db.HelloFreshDb;
import hellofresh.test.com.hellofresh.db.RecipeDao;
import hellofresh.test.com.hellofresh.ui.details.UpdateUserCallback;
import hellofresh.test.com.hellofresh.util.RateLimiter;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

/**
 * Repository that handles Recipe instances.
 */
@Singleton
public class RecipeRepository {
    private final HelloFreshDb db;

    private final RecipeDao recipeDao;

    private final HelloFreshService helloFreshService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> recipeListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public RecipeRepository(AppExecutors appExecutors, HelloFreshDb db, RecipeDao recipeDao,
                            HelloFreshService helloFreshService) {
        this.db = db;
        this.recipeDao = recipeDao;
        this.helloFreshService = helloFreshService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Recipe>>> getRecipes() {
        return new NetworkBoundResource<List<Recipe>, List<Recipe>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Recipe> item) {
                db.beginTransaction();
                try {
                    recipeDao.insertList(item);
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Recipe> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Recipe>> loadFromDb() {
                return recipeDao.getRecipies();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Recipe>>> createCall() {
                return helloFreshService.getRecipes();
            }

            @Override
            protected List<Recipe> processResponse(ApiResponse<List<Recipe>> response) {
                List<Recipe> body = response.body;
                return body;
            }
        }.asLiveData();
    }

    public LiveData<Resource<Recipe>> getRecipe(String recipeID) {
        return new NetworkBoundResource<Recipe, Recipe>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Recipe item) {
                recipeDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Recipe data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Recipe> loadFromDb() {
                return recipeDao.getRecipe(recipeID);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Recipe>> createCall() {
                return null;
            }
        }.asLiveData();
    }

    public void addFavRecipe(Recipe recipe, UpdateUserCallback mUpdateUserCallback) {
        final WeakReference<UpdateUserCallback> updateUserCallback = new WeakReference<>(mUpdateUserCallback);
        appExecutors.diskIO().execute(() -> {
            recipeDao.insert(recipe);
            // notify on the main thread
            appExecutors.mainThread().execute(() -> {
                UpdateUserCallback userCallback = updateUserCallback.get();
                if (userCallback != null) {
                    userCallback.onFavouriteRecipeUpdated(recipe);
                }
            });
        });
    }

    public void removeFavRecipe(Recipe recipe, UpdateUserCallback mUpdateUserCallback) {
        final WeakReference<UpdateUserCallback> updateUserCallback = new WeakReference<>(mUpdateUserCallback);
        appExecutors.diskIO().execute(() -> {
            recipeDao.insert(recipe);
            // notify on the main thread
            appExecutors.mainThread().execute(() -> {
                UpdateUserCallback userCallback = updateUserCallback.get();
                if (userCallback != null) {
                    userCallback.onFavouriteRecipeUpdated(recipe);
                }
            });
        });
    }
}
