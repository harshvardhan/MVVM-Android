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

package hellofresh.test.com.hellofresh.ui;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.binding.FragmentBindingAdapters;
import hellofresh.test.com.hellofresh.testing.SingleFragmentActivity;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;
import hellofresh.test.com.hellofresh.ui.details.RecipeDetailsFragment;
import hellofresh.test.com.hellofresh.ui.details.RecipeDetailsViewModel;
import hellofresh.test.com.hellofresh.util.EspressoTestUtil;
import hellofresh.test.com.hellofresh.util.TaskExecutorWithIdlingResourceRule;
import hellofresh.test.com.hellofresh.util.TestUtil;
import hellofresh.test.com.hellofresh.util.ViewModelUtil;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule =
            new TaskExecutorWithIdlingResourceRule();
    private MutableLiveData<Resource<Recipe>> recipe = new MutableLiveData<>();
    private RecipeDetailsFragment recipeDetailsFragment;
    private RecipeDetailsViewModel viewModel;

    private FragmentBindingAdapters fragmentBindingAdapters;
    private NavigationController navigationController;

    @Before
    public void init() {
        EspressoTestUtil.disableProgressBarAnimations(activityRule);
        recipeDetailsFragment = new RecipeDetailsFragment();
        viewModel = mock(RecipeDetailsViewModel.class);
        fragmentBindingAdapters = mock(FragmentBindingAdapters.class);
        navigationController = mock(NavigationController.class);
        //doNothing().when(viewModel).setId("");
        when(viewModel.getRecipe()).thenReturn(recipe);

        recipeDetailsFragment.viewModelFactory = ViewModelUtil.createFor(viewModel);
        recipeDetailsFragment.navigationController = navigationController;
        activityRule.getActivity().setFragment(recipeDetailsFragment);
    }

    @Test
    public void testLoading() {
        recipe.postValue(Resource.loading(null));
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testValueWhileLoading() {
        try {
            Recipe recipe  = setReceipe();
            this.recipe.postValue(Resource.loading(recipe));
            onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
            onView(withId(R.id.recipe_name)).check(matches(withText("Crispy Fish Goujons ")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadedRecipe() {
        Recipe recipe  = null;
        try {
            recipe = setReceipe();
            this.recipe.postValue(Resource.loading(recipe));
            onView(withId(R.id.recipe_name)).check(matches(withText(recipe.getName())));
            onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullRecipe() {
        this.recipe.postValue(null);
        onView(withId(R.id.recipe_name)).check(matches(not(isDisplayed())));
    }

    private Recipe setReceipe() throws Exception {
        return TestUtil.createRecipe();
    }
}