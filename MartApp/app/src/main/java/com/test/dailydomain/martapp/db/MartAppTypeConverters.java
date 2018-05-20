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

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.util.StringUtil;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.test.dailydomain.martapp.vo.Img;
import com.test.dailydomain.martapp.vo.Pricing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MartAppTypeConverters {
    @TypeConverter
    public static List<Integer> stringToIntList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        return StringUtil.splitToIntList(data);
    }

    @TypeConverter
    public static String intListToString(List<Integer> ints) {
        return StringUtil.joinIntoString(ints);
    }

    @TypeConverter
    public static List<String> stringToStringList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(data.split("##"));
    }

    @TypeConverter
    public static String stringListToString(List<String> strings) {
        return concatStringsWSep(strings, "##");
    }

    public static String concatStringsWSep(Iterable<String> strings, String separator) {
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for(String s: strings) {
            sb.append(sep).append(s);
            sep = separator;
        }
        return sb.toString();
    }

    @TypeConverter
    public static Integer booleanToInt(boolean b) {
        return Integer.valueOf(b ? 1 : 0);
    }

    @TypeConverter
    public static boolean booleanToInt(Integer b) {
        if (1 == b) return true;
        else return false;
    }

    @TypeConverter
    public static Pricing stringToPricing(String data){
        if (data == null || TextUtils.isEmpty(data)) {
            return null;
        }
        Gson gson = new Gson();
        Pricing pricing = gson.fromJson(data, Pricing.class);
        return pricing;
    }

    @TypeConverter
    public static String pricingToString(Pricing pricing) {
        if (pricing == null) {
            return "";
        }
        return new Gson().toJson(pricing);
    }

    @TypeConverter
    public static Img stringToImg(String data){
        if (data == null || TextUtils.isEmpty(data)) {
            return null;
        }
        Gson gson = new Gson();
        Img img = gson.fromJson(data, Img.class);
        return img;
    }

    @TypeConverter
    public static String imgToString(Img img) {
        if (img == null) {
            return "";
        }
        return new Gson().toJson(img);
    }

}
