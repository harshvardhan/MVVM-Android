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

package dailydomain.test.com.sgpowermap.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dailydomain.test.com.sgpowermap.api.SGPowerService;
import dailydomain.test.com.sgpowermap.db.PSIReadingDao;
import dailydomain.test.com.sgpowermap.db.SGPowerMapDb;
import dailydomain.test.com.sgpowermap.util.LiveDataCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    SGPowerService provideSGPowerMapService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.data.gov.sg/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(SGPowerService.class);
    }

    @Singleton @Provides
    SGPowerMapDb provideDb(Application app) {
        return Room.databaseBuilder(app, SGPowerMapDb.class,"sgpowermapdb.db").build();
    }

    @Singleton @Provides
    PSIReadingDao providePSIReadingDao(SGPowerMapDb db) {
        return db.psiReadingDao();
    }
}
