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

package com.test.dailydomain.martapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.test.dailydomain.martapp.vo.Product;

import java.util.List;


/**
 * Interface for database access on Product related operations.
 */
@Dao
public abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertList(List<Product> productList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Product product);

    @Query("SELECT * FROM product ORDER BY title asc")
    public abstract LiveData<List<Product>> getProducts();

    @Query("SELECT * FROM product WHERE id = :id")
    public abstract LiveData<Product> getProduct(String id);
}
