package org.hrytsiuk.mapmarkers.map;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.hrytsiuk.mapmarkers.R;
import org.hrytsiuk.mapmarkers.base.BaseActivity;
import org.hrytsiuk.mapmarkers.places.Place;

import java.util.List;

public final class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private List<Place> places;

    private static final String CHECKED_PLACES = "Checked places";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        places = bundle.getParcelableArrayList(CHECKED_PLACES);
    }

    @Override
    public void onMapReady(final GoogleMap gMap) {

        gMap.setTrafficEnabled(true);
        gMap.setIndoorEnabled(true);
        gMap.setBuildingsEnabled(true);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (places.isEmpty()) {
            showToast(getString(R.string.message_no_selected));
        } else {
            final LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Place place : places) {
                final LatLng placeLocation = new LatLng(place.getLatitude(),
                        place.getLongitude());
                final Marker marker = gMap.addMarker(new MarkerOptions().position(placeLocation)
                        .title(place.getTitle()));
                builder.include(marker.getPosition());
            }
            final LatLngBounds bounds = builder.build();
            final int padding = 250;
            final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            gMap.moveCamera(cameraUpdate);
            if (bounds.northeast.equals(bounds.southwest)) {
                gMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            } else gMap.animateCamera(cameraUpdate);
        }
    }
}
