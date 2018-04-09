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

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.binding.FragmentDataBindingComponent;
import hellofresh.test.com.hellofresh.databinding.RecipeDetailsFragmentBinding;
import hellofresh.test.com.hellofresh.di.Injectable;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;
import hellofresh.test.com.hellofresh.util.AutoClearedValue;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

/**
 * The UI Controller for displaying a Recipe Information
 */
public class RecipeDetailsFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

    private static final String RECIPE_ID_KEY = "recipe_id_key";

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private RecipeDetailsViewModel recipeDetailsViewModel;

    @Inject
    public NavigationController navigationController;

    public DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    AutoClearedValue<RecipeDetailsFragmentBinding> binding;

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recipeDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailsViewModel.class);
        Bundle args = getArguments();
        if (args != null && args.containsKey(RECIPE_ID_KEY)) {
            recipeDetailsViewModel.setId(args.getString(RECIPE_ID_KEY));
        } else {
            recipeDetailsViewModel.setId(null);
        }
        LiveData<Resource<Recipe>> recipe = recipeDetailsViewModel.getRecipe();
        recipe.observe(this, resource -> {
            binding.get().setRecipe(resource == null ? null : resource.data);
            binding.get().setRecipeResource(resource);
            if (resource != null && resource.data != null)
                binding.get().recipeFav.setSelected(resource.data.isFavourite);
            binding.get().executePendingBindings();
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        RecipeDetailsFragmentBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_details_fragment, container, false, dataBindingComponent);
        dataBinding.setRetryCallback(() -> recipeDetailsViewModel.retry());
        binding = new AutoClearedValue<>(this, dataBinding);
        binding.get().recipeFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("", "Clicked");
                if (v.isSelected())
                    recipeDetailsViewModel.onFavClicked(false);
                else
                    recipeDetailsViewModel.onFavClicked(true);

                v.setSelected(!v.isSelected());
            }
        });
        return dataBinding.getRoot();
    }

    public static RecipeDetailsFragment create(String id) {
        RecipeDetailsFragment repoFragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID_KEY, id);
        repoFragment.setArguments(args);
        return repoFragment;
    }
}
