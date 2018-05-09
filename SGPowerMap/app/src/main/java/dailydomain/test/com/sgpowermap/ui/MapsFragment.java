package dailydomain.test.com.sgpowermap.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import dailydomain.test.com.sgpowermap.di.Injectable;
import dailydomain.test.com.sgpowermap.vo.PSIReading;
import dailydomain.test.com.sgpowermap.vo.RegionalReadings;
import dailydomain.test.com.sgpowermap.vo.Resource;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, Injectable {
    private GoogleMap mMap;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private MapsViewModel mapsViewModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.getMapAsync(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapsViewModel = ViewModelProviders.of(this, viewModelFactory).get(MapsViewModel.class);
        mapsViewModel.setRegionID("Test");
        LiveData<Resource<PSIReading>> psiReading = mapsViewModel.getPSIReading();
        psiReading.observe(this, resource -> {
            Log.d("", "");
            if (resource != null && resource.data != null)
            {
                this.setMarkersAndLabel(mapsViewModel.getRegionalReadings(resource.data));
            }
        });
    }

    public void setMarkersAndLabel(List<RegionalReadings> regionalReadingsList){
        for (RegionalReadings regionalReadings : regionalReadingsList){
            if (mMap != null){
                LatLng latlong = new LatLng(regionalReadings.getLatitude(), regionalReadings.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latlong).snippet(regionalReadings.getReadingsMap().toString()).title(regionalReadings.getRegion()));
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //setting custom info window for the reading details to be rendered clearly
        mMap.setInfoWindowAdapter(new InfoWindowCustom(this.getContext()));

        //Move the camera to the Singapore Location and zoom in!
        LatLng singapore = new LatLng(1.370337, 103.797224);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(singapore.latitude, singapore.longitude), 10.0f));
    }
}
