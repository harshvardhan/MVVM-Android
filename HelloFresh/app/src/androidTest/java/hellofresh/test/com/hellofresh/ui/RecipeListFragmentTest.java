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
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.binding.FragmentBindingAdapters;
import hellofresh.test.com.hellofresh.testing.SingleFragmentActivity;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;
import hellofresh.test.com.hellofresh.ui.list.RecipeListFragment;
import hellofresh.test.com.hellofresh.ui.list.RecipeListViewModel;
import hellofresh.test.com.hellofresh.util.EspressoTestUtil;
import hellofresh.test.com.hellofresh.util.TaskExecutorWithIdlingResourceRule;
import hellofresh.test.com.hellofresh.util.TestUtil;
import hellofresh.test.com.hellofresh.util.ViewModelUtil;
import hellofresh.test.com.hellofresh.vo.Recipe;
import hellofresh.test.com.hellofresh.vo.Resource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class RecipeListFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule =
            new TaskExecutorWithIdlingResourceRule();
    private MutableLiveData<Resource<List<Recipe>>> recipes = new MutableLiveData<>();
    private RecipeListFragment recipeListFragment;
    private RecipeListViewModel viewModel;

    private FragmentBindingAdapters fragmentBindingAdapters;
    private NavigationController navigationController;


    @Before
    public void init() {
        EspressoTestUtil.disableProgressBarAnimations(activityRule);
        recipeListFragment = new RecipeListFragment();
        viewModel = mock(RecipeListViewModel.class);
        fragmentBindingAdapters = mock(FragmentBindingAdapters.class);
        navigationController = mock(NavigationController.class);
        doNothing().when(viewModel).setQuery();
        when(viewModel.getRecipes()).thenReturn(recipes);
        //when(viewModel.getContributors()).thenReturn(contributors);

        recipeListFragment.viewModelFactory = ViewModelUtil.createFor(viewModel);
        //recipeListFragment.dataBindingComponent = () -> fragmentBindingAdapters;
        recipeListFragment.navigationController = navigationController;
        activityRule.getActivity().setFragment(recipeListFragment);
    }

    @Test
    public void testLoading() {
        recipes.postValue(Resource.loading(null));
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testValueWhileLoading() {
        try {
            List<Recipe> listRecipes  = setReceipeList();
            this.recipes.postValue(Resource.loading(listRecipes));
            onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
            onView(withId(R.id.recipe_title)).check(matches(withText("Crispy Fish Goujons ")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRecipeClick() {
        List<Recipe> listRecipes  = null;
        try {
            listRecipes = setReceipeList();
            this.recipes.postValue(Resource.success(listRecipes));
            onView(withText("Crispy Fish Goujons ")).perform(click());
            verify(navigationController).navigateToRecipeDetail("533143aaff604d567f8b4571");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Recipe> setReceipeList() throws Exception {
        List<Recipe> listRecipes = new ArrayList<>();
        listRecipes.add(TestUtil.createRecipe());

        return listRecipes;
    }
}