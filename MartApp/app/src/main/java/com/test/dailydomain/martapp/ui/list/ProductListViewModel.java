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

package com.test.dailydomain.martapp.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.test.dailydomain.martapp.vo.Resource;
import com.test.dailydomain.martapp.repository.ProductRepository;
import com.test.dailydomain.martapp.util.AbsentLiveData;
import com.test.dailydomain.martapp.vo.Product;

import java.util.List;

import javax.inject.Inject;

public class ProductListViewModel extends ViewModel {

    private final MutableLiveData<String> query = new MutableLiveData<>();

    private final LiveData<Resource<List<Product>>> products;

    @Inject
    public ProductListViewModel(ProductRepository productRepository) {
        products = Transformations.switchMap(query, search -> {
            if (search == null || search.trim().length() == 0) {
                return AbsentLiveData.create();
            } else {
                return productRepository.getProducts();
            }
        });
    }

    @VisibleForTesting
    public LiveData<Resource<List<Product>>> getProducts() {
        return products;
    }

    public void setQuery() {
        query.setValue("1");
    }

    public void refresh() {
        if (query.getValue() != null) {
            query.setValue(query.getValue());
        }
        else
            query.setValue("");
    }
}
