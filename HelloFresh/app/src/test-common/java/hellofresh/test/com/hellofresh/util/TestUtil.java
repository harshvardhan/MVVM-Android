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

package hellofresh.test.com.hellofresh.util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import hellofresh.test.com.hellofresh.vo.Recipe;

public class TestUtil {
    private static String recipeVal = "{\n" +
            "        \"calories\": \"516 kcal\",\n" +
            "        \"carbos\": \"47 g\",\n" +
            "        \"card\": \"https://api.hellofresh.com/recipe/card/533143aaff604d567f8b4571\",\n" +
            "        \"country\": \"GB\",\n" +
            "        \"deliverable_ingredients\": [\n" +
            "            \"375g Sweet Potatoes\",\n" +
            "            \"1 Tsp Paprika\",\n" +
            "            \"2 Tbsps Parmesan Cheese\",\n" +
            "            \"1 Lemon\",\n" +
            "            \"A Few Sprigs Thyme\",\n" +
            "            \"25g Panko Breadcrumbs\",\n" +
            "            \"1 Tbsp Butter\",\n" +
            "            \"2 Cod Fillets\",\n" +
            "            \"150g Sugar Snap Peas\",\n" +
            "            \"A Few Sprigs Mint\",\n" +
            "            \"75ml Sour Cream\"\n" +
            "        ],\n" +
            "        \"description\": \"There\\u2019s nothing like the simple things in life - the smell of freshly cut grass, sitting outside on a nice sunny day, spending time with friends and family. Well here is a recipe that delivers simple culinary pleasures - some nice fresh fish with a crispy crust, crunchy potato wedges and some delightfully sweet sugar snap peas flavoured with cooling mint. Slip into something comfortable and relax into a delicious dinner!\",\n" +
            "        \"difficulty\": 0,\n" +
            "        \"fats\": \"8 g\",\n" +
            "        \"favorites\": 1,\n" +
            "        \"fibers\": \"\",\n" +
            "        \"headline\": \"with Sweet Potato Wedges and Minted Snap Peas\",\n" +
            "        \"highlighted\": true,\n" +
            "        \"id\": \"533143aaff604d567f8b4571\",\n" +
            "        \"image\": \"https://d3hvwccx09j84u.cloudfront.net/web/image/533143aaff604d567f8b4571.jpg\",\n" +
            "        \"incompatibilities\": null,\n" +
            "        \"ingredients\": [\n" +
            "            \"375g Sweet Potatoes\",\n" +
            "            \"1 Tsp Paprika\",\n" +
            "            \"2 Tbsps Parmesan Cheese\",\n" +
            "            \"1 Lemon\",\n" +
            "            \"A Few Sprigs Thyme\",\n" +
            "            \"25g Panko Breadcrumbs\",\n" +
            "            \"1 Tbsp Butter\",\n" +
            "            \"2 Cod Fillets\",\n" +
            "            \"150g Sugar Snap Peas\",\n" +
            "            \"A Few Sprigs Mint\",\n" +
            "            \"75ml Sour Cream\"\n" +
            "        ],\n" +
            "        \"keywords\": [\n" +
            "            \"\"\n" +
            "        ],\n" +
            "        \"name\": \"Crispy Fish Goujons \",\n" +
            "        \"products\": [\n" +
            "            \"family-box\"\n" +
            "        ],\n" +
            "        \"proteins\": \"43 g\",\n" +
            "        \"rating\": null,\n" +
            "        \"ratings\": null,\n" +
            "        \"thumb\": \"https://d3hvwccx09j84u.cloudfront.net/thumb/image/533143aaff604d567f8b4571.jpg\",\n" +
            "        \"time\": \"PT35M\",\n" +
            "        \"undeliverable_ingredients\": [],\n" +
            "        \"user\": {\n" +
            "            \"email\": \"imp@hellofresh.hf\",\n" +
            "            \"latlng\": \"51.507351, -0.127758\",\n" +
            "            \"name\": \"Tyrion Lannister\"\n" +
            "        },\n" +
            "        \"weeks\": [\n" +
            "            \"2014-W20\"\n" +
            "        ]\n" +
            "    }";

    public static Recipe createRecipe() throws Exception{
        Gson gson = new Gson();
        return gson.fromJson(recipeVal, Recipe.class);
    }
}
