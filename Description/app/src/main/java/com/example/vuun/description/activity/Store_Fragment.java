package com.example.vuun.description.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuun.description.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Teevarapat on 10/7/2015.
 */
public class Store_Fragment extends Fragment implements GoogleMap.OnInfoWindowClickListener{

    MapView mapView;
    GoogleMap map;
    LatLng position;

    String[] name_store = new String[]{"ร้านเครื่องเขียน ภาควิศวกรรมโยธา", "ร้านเครื่องเขียน ลานวิศวกรรมการบิน"};
    double[] latitude = new double[]{13.8460742, 13.8454589};
    double[] longitude = new double[]{100.5685375, 100.5703644};
    private ArrayList<Marker> markers = new ArrayList<Marker>();

    public Store_Fragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);

        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.setOnInfoWindowClickListener(this);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
        map.getUiSettings().isIndoorLevelPickerEnabled();

        position = new LatLng(13.8463337, 100.5693643);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 17);
        map.animateCamera(cameraUpdate);

        for(int i = 0; i < name_store.length; i++) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(latitude[i],longitude[i]))
                    .title(name_store[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            markers.add(marker);
        }

        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(markers.get(0)))
        {
            //handle click here
            Fragment newFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            String place = "st_01";
            bundle.putString("PLACE", place);
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // and add the transaction to the back stack
            transaction.replace(R.id.container_body, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        }

        else if (marker.equals(markers.get(1)))
        {
            //handle click here
            // Fragment newFragment = new TestFragment();
            Fragment newFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            String place = "st_02";
            bundle.putString("PLACE", place);
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // and add the transaction to the back stack
            transaction.replace(R.id.container_body, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        }
    }
}
