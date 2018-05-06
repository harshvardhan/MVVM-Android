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

package dailydomain.test.com.sgpowermap.db;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.util.StringUtil;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dailydomain.test.com.sgpowermap.vo.ApiInfo;
import dailydomain.test.com.sgpowermap.vo.Item;
import dailydomain.test.com.sgpowermap.vo.RegionMetadatum;

public class SGPowerMapTypeConverters {
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
    public static List<RegionMetadatum> stringToregionMetadatumList(String data){
        if (data == null || TextUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        List<RegionMetadatum> regionMetadatums = new ArrayList<>();
        List<String> regionMetadatumStringList = Arrays.asList(TextUtils.split(data, "\t"));
        Gson gson = new Gson();
        for (String regionMetadataString : regionMetadatumStringList) {
            Log.i("TypeConverters", "JSON :" + regionMetadataString);
            RegionMetadatum regionMetadatum = gson.fromJson(regionMetadataString, RegionMetadatum.class);
            regionMetadatums.add(regionMetadatum);
        }
        return regionMetadatums;
    }

    @TypeConverter
    public static String regionMetadatumListToString(List<RegionMetadatum> regionMetadata) {
        if (regionMetadata == null) {
            return "";
        }
        Gson gson = new Gson();
        List<String> regionMetadataList = new ArrayList<>();
        for (RegionMetadatum regionMetadatum : regionMetadata) {
            regionMetadataList.add(gson.toJson(regionMetadatum));
        }
        return TextUtils.join("\t ", regionMetadataList);
    }

    @TypeConverter
    public static List<Item> stringToItemList(String data){
        if (data == null || TextUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        List<Item> items = new ArrayList<>();
        List<String> itemStringList = Arrays.asList(TextUtils.split(data, "\t"));
        Gson gson = new Gson();
        for (String itemString : itemStringList) {
            Log.i("TypeConverters", "JSON :" + itemString);
            Item item = gson.fromJson(itemString, Item.class);
            items.add(item);
        }
        return items;
    }

    @TypeConverter
    public static String itemListToString(List<Item> items) {
        if (items == null) {
            return "";
        }
        Gson gson = new Gson();
        List<String> itemList = new ArrayList<>();
        for (Item item : items) {
            itemList.add(gson.toJson(item));
        }
        return TextUtils.join("\t ", itemList);
    }

    @TypeConverter
    public static List<ApiInfo> stringToAPIInfoList(String data){
        if (data == null || TextUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        List<ApiInfo> apiInfoList = new ArrayList<>();
        List<String> apiInfoStringList = Arrays.asList(TextUtils.split(data, "\t"));
        Gson gson = new Gson();
        for (String apiInfoString : apiInfoStringList) {
            Log.i("TypeConverters", "JSON :" + apiInfoString);
            ApiInfo apiInfo = gson.fromJson(apiInfoString, ApiInfo.class);
            apiInfoList.add(apiInfo);
        }
        return apiInfoList;
    }

    @TypeConverter
    public static String apiInfoListToString(List<ApiInfo> apiInfoList) {
        if (apiInfoList == null) {
            return "";
        }
        Gson gson = new Gson();
        List<String> apiInfoStringList = new ArrayList<>();
        for (ApiInfo apiInfo : apiInfoList) {
            apiInfoStringList.add(gson.toJson(apiInfo));
        }
        return TextUtils.join("\t ", apiInfoStringList);
    }

    @TypeConverter
    public static ApiInfo stringToAPIInfo(String data){
        if (data == null || TextUtils.isEmpty(data)) {
            return null;
        }
        Gson gson = new Gson();
        ApiInfo apiInfo = gson.fromJson(data, ApiInfo.class);
        return apiInfo;
    }

    @TypeConverter
    public static String apiInfoToString(ApiInfo apiInfo) {
        if (apiInfo == null) {
            return "";
        }
        Gson gson = new Gson();
        return gson.toJson(apiInfo);
    }
}
