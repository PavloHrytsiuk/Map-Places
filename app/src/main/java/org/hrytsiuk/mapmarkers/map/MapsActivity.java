package org.hrytsiuk.mapmarkers.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
import org.hrytsiuk.mapmarkers.places.Place;

import java.util.ArrayList;
import java.util.List;

public final class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<Place> places;
    private ArrayList<Integer> checkedPlaces;

    private static final String CHECKED_PLACES = "Checked places";
    private static final String LIST_PLACES = "List_places";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        places = bundle.getParcelableArrayList(LIST_PLACES);
        checkedPlaces = getIntent().getIntegerArrayListExtra(CHECKED_PLACES);
    }

    @Override
    public void onMapReady(final GoogleMap gMap) {

        googleMap = gMap;
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Integer x : checkedPlaces) {
            final LatLng placeLocation = new LatLng(places.get(x).getLatitude(),
                    places.get(x).getLongitude());
            final Marker marker = googleMap.addMarker(new MarkerOptions().position(placeLocation)
                    .title(places.get(x).getTitle()));

            builder.include(marker.getPosition());
        }
        final LatLngBounds bounds = builder.build();
        int padding = 250;
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
        googleMap.animateCamera(cu);
    }
}
