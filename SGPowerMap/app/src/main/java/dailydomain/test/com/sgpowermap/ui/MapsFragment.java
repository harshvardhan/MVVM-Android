package dailydomain.test.com.sgpowermap.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, Injectable {
    private GoogleMap mMap;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    public NavigationController navigationController;

    public FragmentDataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    //AutoClearedValue<LoginFragmentBinding> binding;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng singapore = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(singapore).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(singapore));
    }
}
