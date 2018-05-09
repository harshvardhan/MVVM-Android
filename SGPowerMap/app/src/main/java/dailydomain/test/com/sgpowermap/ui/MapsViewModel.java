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
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dailydomain.test.com.sgpowermap.repository.PSIReadingRepository;
import dailydomain.test.com.sgpowermap.util.AbsentLiveData;
import dailydomain.test.com.sgpowermap.vo.LabelLocation;
import dailydomain.test.com.sgpowermap.vo.PSIReading;
import dailydomain.test.com.sgpowermap.vo.Readings;
import dailydomain.test.com.sgpowermap.vo.RegionMetadatum;
import dailydomain.test.com.sgpowermap.vo.RegionalReadings;
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

    public List<RegionalReadings> getRegionalReadings(PSIReading psiReading) {
        if (!psiReading.getItems().isEmpty() || !psiReading.getRegionMetadata().isEmpty()){
            List<RegionalReadings> regionalReadings;
            regionalReadings = this.populateRegionAndLocation(psiReading.getRegionMetadata());
            regionalReadings = this.populateReadings(psiReading.getItems().get(0).getReadings(), regionalReadings);

            return regionalReadings;
        }
        return null;
    }

    public List<RegionalReadings> populateRegionAndLocation(List<RegionMetadatum> regionMetadata){
        List<RegionalReadings> regionalReadings = new ArrayList<>();
        for (RegionMetadatum regionMetadatum : regionMetadata){
            RegionalReadings regionalReading = new RegionalReadings();
            regionalReading.setRegion(regionMetadatum.getName());

            LabelLocation labelLocation = regionMetadatum.getLabelLocation();
            regionalReading.setLatitude(labelLocation.getLatitude());
            regionalReading.setLongitude(labelLocation.getLongitude());

            regionalReadings.add(regionalReading);
        }

        return regionalReadings;
    }

    public List<RegionalReadings> populateReadings(Readings readings, List<RegionalReadings> regionalReadings){
        readings.setRegionalReadingDictionary();
        for (RegionalReadings regionalReading : regionalReadings) {
            if (regionalReading.getRegion().equalsIgnoreCase("east"))
                regionalReading.setReadingsMap(readings.getEastReadings());
            if (regionalReading.getRegion().equalsIgnoreCase("west"))
                regionalReading.setReadingsMap(readings.getWestReadings());
            if (regionalReading.getRegion().equalsIgnoreCase("north"))
                regionalReading.setReadingsMap(readings.getNorthReadings());
            if (regionalReading.getRegion().equalsIgnoreCase("south"))
                regionalReading.setReadingsMap(readings.getSouthReadings());
            if (regionalReading.getRegion().equalsIgnoreCase("central"))
                regionalReading.setReadingsMap(readings.getCentralReadings());
            else if (regionalReading.getRegion().equalsIgnoreCase("national"))
                regionalReading.setReadingsMap(readings.getNationalReadings());
        }

        return regionalReadings;
    }
}
