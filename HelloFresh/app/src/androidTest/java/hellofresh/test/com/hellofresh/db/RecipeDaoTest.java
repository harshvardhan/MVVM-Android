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

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import hellofresh.test.com.hellofresh.util.LiveDataTestUtil;
import hellofresh.test.com.hellofresh.util.TestUtil;
import hellofresh.test.com.hellofresh.vo.Recipe;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class RecipeDaoTest extends DbTest {
    @Test
    public void insertAndRead() throws InterruptedException {
        Recipe recipe = null;
        try {
            recipe = TestUtil.createRecipe();
            db.recipeDao().insert(recipe);
            Recipe loaded = LiveDataTestUtil.getValue(db.recipeDao().getRecipe("533143aaff604d567f8b4571"));
            assertThat(loaded, notNullValue());
            assertThat(loaded.name, is("Crispy Fish Goujons "));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
