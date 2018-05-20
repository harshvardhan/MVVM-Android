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

package com.test.dailydomain.martapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.test.dailydomain.martapp.api.MartService;
import com.test.dailydomain.martapp.db.MartDb;
import com.test.dailydomain.martapp.db.ProductDao;
import com.test.dailydomain.martapp.util.LiveDataCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    MartService provideTMDBService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.redmart.com/v1.6.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(MartService.class);
    }

    @Singleton @Provides
    MartDb provideDb(Application app) {
        return Room.databaseBuilder(app, MartDb.class,"mart.db").build();
    }

    @Singleton @Provides
    ProductDao provideProductDao(MartDb db) {
        return db.productDao();
    }
}
