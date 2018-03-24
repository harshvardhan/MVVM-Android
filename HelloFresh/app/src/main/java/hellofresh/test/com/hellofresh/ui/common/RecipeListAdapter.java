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

package hellofresh.test.com.hellofresh.ui.common;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.databinding.CardRecipeBinding;
import hellofresh.test.com.hellofresh.util.Objects;
import hellofresh.test.com.hellofresh.vo.Recipe;

/**
 * A RecyclerView adapter for {@link Recipe} class.
 */
public class RecipeListAdapter extends DataBoundListAdapter<Recipe, CardRecipeBinding> {
    private final DataBindingComponent dataBindingComponent;
    private final RecipeClickCallback recipeClickCallback;
    private final boolean showFullName;

    public RecipeListAdapter(DataBindingComponent dataBindingComponent, boolean showFullName,
                             RecipeClickCallback recipeClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.recipeClickCallback = recipeClickCallback;
        this.showFullName = showFullName;
    }

    @Override
    protected CardRecipeBinding createBinding(ViewGroup parent) {
        CardRecipeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.card_recipe,
                        parent, false, dataBindingComponent);
        //binding.setShowFullName(showFullName);
        binding.getRoot().setOnClickListener(v -> {
            Recipe recipe = binding.getRecipe();
            if (recipe != null && recipeClickCallback != null) {
                recipeClickCallback.onClick(recipe);
            }
        });
        return binding;
    }

    @Override
    protected void bind(CardRecipeBinding binding, Recipe item) {
        binding.setRecipe(item);
    }

    @Override
    protected boolean areItemsTheSame(Recipe oldItem, Recipe newItem) {
        return Objects.equals(oldItem.id, newItem.id) &&
                Objects.equals(oldItem.name, newItem.name);
    }

    @Override
    protected boolean areContentsTheSame(Recipe oldItem, Recipe newItem) {
        return Objects.equals(oldItem.name, newItem.name) &&
                oldItem.id == newItem.id;
    }

    public interface RecipeClickCallback {
        void onClick(Recipe recipe);
    }
}
