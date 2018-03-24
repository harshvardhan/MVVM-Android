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

package hellofresh.test.com.hellofresh.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import javax.inject.Inject;

import hellofresh.test.com.hellofresh.util.AbsentLiveData;

public class LoginViewModel extends ViewModel {

    private static final String USER_EMAIL_ID = "rrmartin@got.com";
    private static final String USER_PASSWORD = "burnThemAll";

    private UserCrendentials userCrendentials;

    private final MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();

    @VisibleForTesting
    public void authenticate(String userName, String password) {
        userCrendentials = new UserCrendentials(userName, password);
        authenticate();
    }

    @VisibleForTesting
    private void authenticate()
    {
        if (userCrendentials.userName.equals(LoginViewModel.USER_EMAIL_ID) && userCrendentials.password.equals(LoginViewModel.USER_PASSWORD))
            this.isAuthenticated.setValue(true);
        else
            this.isAuthenticated.setValue(false);
    }

    public LiveData<Boolean> getIsAuthenticated() {
        return this.isAuthenticated;
    }

    @VisibleForTesting
    static class UserCrendentials {
        public final String userName;
        public final String password;

        UserCrendentials(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        boolean isEmpty() {
            return TextUtils.isEmpty(userName) && TextUtils.isEmpty(password);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            UserCrendentials userCrendentials = (UserCrendentials) o;

            return userCrendentials.userName == userName;
        }

        @Override
        public int hashCode() {
            int result = userName != null ? userName.hashCode() : 0;
            result = 31 * result + (userName != null ? userName.hashCode() : 0);
            return result;
        }
    }
}
