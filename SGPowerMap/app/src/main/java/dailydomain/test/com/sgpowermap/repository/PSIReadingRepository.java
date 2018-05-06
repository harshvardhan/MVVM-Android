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

package dailydomain.test.com.sgpowermap.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dailydomain.test.com.sgpowermap.AppExecutors;
import dailydomain.test.com.sgpowermap.api.ApiResponse;
import dailydomain.test.com.sgpowermap.api.SGPowerService;
import dailydomain.test.com.sgpowermap.db.PSIReadingDao;
import dailydomain.test.com.sgpowermap.db.SGPowerMapDb;
import dailydomain.test.com.sgpowermap.util.RateLimiter;
import dailydomain.test.com.sgpowermap.vo.PSIReading;
import dailydomain.test.com.sgpowermap.vo.Resource;

@Singleton
public class PSIReadingRepository {
    private final SGPowerMapDb db;

    private final PSIReadingDao psiReadingDao;

    private final SGPowerService sgPowerService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> psiReadingRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public PSIReadingRepository(AppExecutors appExecutors, SGPowerService sgPowerService, SGPowerMapDb db, PSIReadingDao psiReadingDao) {
        this.db = db;
        this.psiReadingDao = psiReadingDao;
        this.sgPowerService = sgPowerService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<PSIReading>> getPSIReading() {
        return new NetworkBoundResource<PSIReading, PSIReading>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull PSIReading item) {
                db.beginTransaction();
                try {
                    psiReadingDao.insert(item);
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable PSIReading data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<PSIReading> loadFromDb() {
                return psiReadingDao.getPSIReading();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<PSIReading>> createCall() {
                return sgPowerService.getPSIReading();
            }

            @Override
            protected PSIReading processResponse(ApiResponse<PSIReading> response) {
                PSIReading body = response.body;
                return body;
            }
        }.asLiveData();
    }
}
