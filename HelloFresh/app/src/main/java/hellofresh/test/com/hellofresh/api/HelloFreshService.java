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

package hellofresh.test.com.hellofresh.api;

import android.arch.lifecycle.LiveData;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import hellofresh.test.com.hellofresh.vo.Recipe;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * REST API access points
 */
public interface HelloFreshService {

    //https://api.myjson.com/bins/ov4ah
    //Hosted a JSON with list of recipes on myjson.com
    @GET("bins/ov4ah")
    LiveData<ApiResponse<List<Recipe>>> getRecipes();

    //https://api.myjson.com/bins/15s0fr
    //Hosted a JSON with single a recipe on myjson.com
    @GET("bins/15s0fr")
    LiveData<ApiResponse<Recipe>> getRecipe();
}
