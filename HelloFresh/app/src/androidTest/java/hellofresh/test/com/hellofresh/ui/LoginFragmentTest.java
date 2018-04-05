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

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hellofresh.test.com.hellofresh.R;
import hellofresh.test.com.hellofresh.binding.FragmentBindingAdapters;
import hellofresh.test.com.hellofresh.testing.SingleFragmentActivity;
import hellofresh.test.com.hellofresh.ui.common.NavigationController;
import hellofresh.test.com.hellofresh.ui.login.LoginFragment;
import hellofresh.test.com.hellofresh.ui.login.LoginViewModel;
import hellofresh.test.com.hellofresh.util.EspressoTestUtil;
import hellofresh.test.com.hellofresh.util.TaskExecutorWithIdlingResourceRule;
import hellofresh.test.com.hellofresh.util.ViewModelUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule =
            new TaskExecutorWithIdlingResourceRule();

    private FragmentBindingAdapters fragmentBindingAdapters;
    private NavigationController navigationController;

    private LoginViewModel viewModel;

    @Before
    public void init() {
        EspressoTestUtil.disableProgressBarAnimations(activityRule);
        LoginFragment loginFragment = new LoginFragment();
        viewModel = mock(LoginViewModel.class);

        navigationController = mock(NavigationController.class);
        loginFragment.viewModelFactory = ViewModelUtil.createFor(viewModel);
        loginFragment.navigationController = navigationController;
        activityRule.getActivity().setFragment(loginFragment);
    }

    @Test
    public void successfulLogin() {
        onView(withId(R.id.user_email)).perform(typeText("rrmartin@got.com"));
        onView(withId(R.id.user_password)).perform(typeText("BurnThemAll"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.login_button)).perform(click());
    }

    @Test
    public void invalidCredentials() {
        onView(withId(R.id.user_email)).perform(typeText("jkrowling@harrypotter.com"));
        onView(withId(R.id.user_password)).perform(typeText("AvadaKedavra"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }
}