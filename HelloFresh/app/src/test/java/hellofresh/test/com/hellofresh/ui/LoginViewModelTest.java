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

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import hellofresh.test.com.hellofresh.repository.RecipeRepository;
import hellofresh.test.com.hellofresh.ui.login.LoginViewModel;
import hellofresh.test.com.hellofresh.vo.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class LoginViewModelTest {

    private static final String USER_EMAIL_ID = "rrmartin@got.com";
    private static final String USER_PASSWORD = "burnThemAll";

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private RecipeRepository recipeRepository;
    private LoginViewModel loginViewModel;

    @Before
    public void setup() {
        loginViewModel = new LoginViewModel();
    }

    @Test
    public void testCorrectCredentials() {
        Observer observer = mock(Observer.class);
        loginViewModel.getIsAuthenticated().observeForever(observer);
        loginViewModel.authenticate(USER_EMAIL_ID, USER_PASSWORD);
        assertThat(loginViewModel.getIsAuthenticated().getValue(), is(true));
        loginViewModel.getIsAuthenticated().removeObserver(observer);
    }

    @Test
    public void testIncorrectCredentials() {
        Observer observer = mock(Observer.class);
        loginViewModel.getIsAuthenticated().observeForever(observer);
        loginViewModel.authenticate("rrmartin@got.co", USER_PASSWORD);
        assertThat(loginViewModel.getIsAuthenticated().getValue(), is(false));
        loginViewModel.getIsAuthenticated().removeObserver(observer);
    }
}