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

package dailydomain.test.com.sgpowermap.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import dailydomain.test.com.sgpowermap.repository.PSIReadingRepository;
import dailydomain.test.com.sgpowermap.util.AbsentLiveData;
import dailydomain.test.com.sgpowermap.vo.PSIReading;
import dailydomain.test.com.sgpowermap.vo.Resource;

public class MapsViewModel extends ViewModel {

    private final MutableLiveData<String> regionID;
    private final PSIReadingRepository psiReadingRepository;
    private final LiveData<Resource<PSIReading>> psiReading;

    @Inject
    public MapsViewModel(PSIReadingRepository psiReadingRepository) {
        this.regionID = new MutableLiveData<>();
        psiReading = Transformations.switchMap(regionID, input -> {
            if (input.isEmpty()) {
                return AbsentLiveData.create();
            }
            return psiReadingRepository.getPSIReading();
        });
        this.psiReadingRepository = psiReadingRepository;
    }

    @VisibleForTesting
    public void setRegionID(String id) {
        this.regionID.setValue(id);
    }

    public LiveData<Resource<PSIReading>> getPSIReading() {
        return psiReading;
    }
}
