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

package hellofresh.test.com.hellofresh.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.repository.RecipeRepository;
import hellofresh.test.com.hellofresh.util.AbsentLiveData;
import hellofresh.test.com.hellofresh.util.Objects;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

public class RecipeDetailsViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<RecipeId> recipeId;
    private final LiveData<Resource<Recipe>> recipe;
    private RecipeRepository recipeRepository;
    private FavActionCallback mFavActionCallback;

    @Inject
    public RecipeDetailsViewModel(RecipeRepository recipeRepository) {
        this.recipeId = new MutableLiveData<>();
        recipe = Transformations.switchMap(recipeId, input -> {
            if (input.isEmpty()) {
                return AbsentLiveData.create();
            }
            return recipeRepository.getRecipe(input.id);
        });
        this.recipeRepository = recipeRepository;
        mFavActionCallback = createUpdateUserCallback();
    }

    private FavActionCallback createUpdateUserCallback() {
        return recipeID -> {
            Log.d("", "Favourite Updated");
            Log.d("","Favourite Updated");
        };
    }

    public void addFav()
    {
        recipe.getValue().data.setFavourite(true);
        this.recipeRepository.addFavRecipe(recipe.getValue().data, mFavActionCallback);
    }

    public void removeFav()
    {
        recipe.getValue().data.setFavourite(false);
        this.recipeRepository.removeFavRecipe(recipe.getValue().data, mFavActionCallback);
    }

    public LiveData<Resource<Recipe>> getRecipe() {
        return recipe;
    }

    void retry() {
        RecipeId current = recipeId.getValue();
        if (current != null && !current.isEmpty()) {
            recipeId.setValue(current);
        }
    }

    public void onFavClicked(boolean isSelected) {
        if (isSelected)
            addFav();
        else
            removeFav();
    }

    @VisibleForTesting
    public void setId(String id) {
        RecipeId update = new RecipeId(id);
        if (Objects.equals(recipeId.getValue(), update)) {
            return;
        }
        recipeId.setValue(update);
    }

    @VisibleForTesting
    static class RecipeId {
        public final String id;

        RecipeId(String id) {
            this.id = id;
        }

        boolean isEmpty() {
            return TextUtils.isEmpty(id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            RecipeId recipeId = (RecipeId) o;

            return recipeId.id == id;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
    }
}
