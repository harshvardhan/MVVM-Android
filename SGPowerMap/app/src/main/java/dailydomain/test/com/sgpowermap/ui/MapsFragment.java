package dailydomain.test.com.sgpowermap.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import dailydomain.test.com.sgpowermap.binding.FragmentDataBindingComponent;
import dailydomain.test.com.sgpowermap.di.Injectable;
import dailydomain.test.com.sgpowermap.ui.common.NavigationController;
import dailydomain.test.com.sgpowermap.vo.PSIReading;
import dailydomain.test.com.sgpowermap.vo.Resource;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, Injectable {
    private GoogleMap mMap;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    public NavigationController navigationController;

    //public FragmentDataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private MapsViewModel mapsViewModel;

    //AutoClearedValue<LoginFragmentBinding> binding;

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
                mapsViewModel.getRegionalReadings(resource.data);
                Log.d("", "");
            }
        });
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
        // Add a marker in singapore and move the camera
        LatLng singapore = new LatLng(1.370337, 103.797224);
        mMap.addMarker(new MarkerOptions().position(singapore).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
        //Move the camera to the user's location and zoom in!
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(singapore.latitude, singapore.longitude), 10.0f));
    }

    //public void addMarker(LatLng )
}
