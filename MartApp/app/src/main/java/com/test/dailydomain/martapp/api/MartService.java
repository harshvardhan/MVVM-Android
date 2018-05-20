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

package com.test.dailydomain.martapp.api;

import android.arch.lifecycle.LiveData;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import com.test.dailydomain.martapp.vo.Product;
import com.test.dailydomain.martapp.vo.ProductListWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * REST API access points
 */
public interface MartService {

    //JSON with list of products
    @GET("catalog/search?page=0&pageSize=30")
    LiveData<ApiResponse<ProductListWrapper>> getProducts();

    //JSON with single a product
    @GET("catalog/products/{product_id}")
    LiveData<ApiResponse<Product>> getProduct(@Path("product_id") String productID);
}
