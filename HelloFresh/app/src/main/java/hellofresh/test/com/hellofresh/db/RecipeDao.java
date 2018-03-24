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

package hellofresh.test.com.hellofresh.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import hellofresh.test.com.hellofresh.vo.Recipe;

/**
 * Interface for database access on Repo related operations.
 */
@Dao
public abstract class RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertList(List<Recipe> recipeList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Recipe recipe);

    @Query("SELECT * FROM recipe ORDER BY name asc")
    public abstract LiveData<List<Recipe>> getRecipies();

    @Query("SELECT * FROM recipe WHERE id = :id")
    public abstract LiveData<Recipe> getRecipe(String id);
}
