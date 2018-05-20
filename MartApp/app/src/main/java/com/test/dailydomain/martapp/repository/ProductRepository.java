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

package com.test.dailydomain.martapp.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.test.dailydomain.martapp.AppExecutors;
import com.test.dailydomain.martapp.api.ApiResponse;
import com.test.dailydomain.martapp.api.MartService;
import com.test.dailydomain.martapp.db.MartDb;
import com.test.dailydomain.martapp.db.ProductDao;
import com.test.dailydomain.martapp.util.RateLimiter;
import com.test.dailydomain.martapp.vo.Product;
import com.test.dailydomain.martapp.vo.ProductListWrapper;
import com.test.dailydomain.martapp.vo.Resource;

/**
 * Repository that handles Product instances.
 */
@Singleton
public class ProductRepository {
    private final MartDb db;

    private final ProductDao productDao;

    private final MartService martService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> productListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public ProductRepository(AppExecutors appExecutors, MartDb db, ProductDao productDao,
                             MartService martService) {
        this.db = db;
        this.productDao = productDao;
        this.martService = martService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Product>>> getProducts() {
        return new NetworkBoundResource<List<Product>, ProductListWrapper>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull ProductListWrapper item) {
                db.beginTransaction();
                try {
                    productDao.insertList(item.getProducts());
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Product> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Product>> loadFromDb() {
                return productDao.getProducts();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ProductListWrapper>> createCall() {
                return martService.getProducts();
            }
        }.asLiveData();
    }

    public LiveData<Resource<Product>> getProduct(String productID) {
        return new NetworkBoundResource<Product, Product>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Product item) {
                productDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Product data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Product> loadFromDb() {
                return productDao.getProduct(productID);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Product>> createCall() {
                return martService.getProduct(productID);
            }
        }.asLiveData();
    }
}
