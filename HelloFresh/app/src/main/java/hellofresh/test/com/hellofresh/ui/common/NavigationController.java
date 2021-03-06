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

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.MainActivity;
import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.ui.details.RecipeDetailsFragment;
import hellofresh.test.com.hellofresh.ui.list.RecipeListFragment;
import hellofresh.test.com.hellofresh.ui.login.LoginFragment;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, loginFragment, LoginFragment.class.getSimpleName())
                .commitAllowingStateLoss();
    }

    public void navigateToRecipeList() {
        RecipeListFragment recipeListFragment = new RecipeListFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, recipeListFragment, RecipeListFragment.class.getSimpleName())
                .commitAllowingStateLoss();
    }

    public void navigateToRecipeDetail(String recipeID) {
        RecipeDetailsFragment recipeDetailFragment = RecipeDetailsFragment.create(recipeID);
        String tag = "recipe" + "/" + recipeID;
        fragmentManager.beginTransaction()
                .replace(containerId, recipeDetailFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
