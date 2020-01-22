package com.betcher.jordan.sivisomapschedule;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        //*
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mapFragment.getView().setVisibility(View.GONE);

        //*/

        createList();
    }

    private void createList() {
        ListView list = (ListView) findViewById(R.id.listTop);

        ListItemLocationSiViSo location1 = new ListItemLocationSiViSo("location 1", "silent");
        ListItemLocationSiViSo location2 = new ListItemLocationSiViSo("location 2 test", "vibrate");
        ListItemLocationSiViSo location3 = new ListItemLocationSiViSo("location 3", "sound");
        ListItemLocationSiViSo location4 = new ListItemLocationSiViSo("location 4", "silent");

        ArrayList<ListItemLocationSiViSo> listLocationSiViSo = new ArrayList<ListItemLocationSiViSo>();
        listLocationSiViSo.add(location1);
        listLocationSiViSo.add(location2);
        listLocationSiViSo.add(location3);
        listLocationSiViSo.add(location4);

        LocationSiViSoListAdapter adapter = new LocationSiViSoListAdapter(this, R.layout.layout_list_item, listLocationSiViSo);
        list.setAdapter(adapter);

        ArrayList<ListItemLocationSiViSo> t = new ArrayList<ListItemLocationSiViSo>();
        ListItemLocationSiViSo location5 = new ListItemLocationSiViSo("location 5 das", "silent");
        t.add(location5);

        //*
        ListView listBottom = (ListView) findViewById(R.id.listBottom);
        LocationSiViSoListAdapter adapterBottom = new LocationSiViSoListAdapter(this, R.layout.layout_list_item, t);
        listBottom.setAdapter(adapterBottom);
        //*/
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onClick(View view) {
        mapFragment.getView().setVisibility(View.VISIBLE);
    }
}
