package com.example.vuun.description.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Teevarapat on 10/7/2015.
 */
public class Document_Fragment extends Fragment {

    MapView mapView;
    GoogleMap map;
    LatLng position;

    private final LatLng G6_Document = new LatLng(13.84622585, 100.5693774);
    private final LatLng Chem_Document = new LatLng(13.8459245, 100.5698786);
    private final LatLng B3_Document = new LatLng(13.845903, 100.5698964);
    private final LatLng Yotha_Document = new LatLng(13.8460843, 100.5685586);

    public Document_Fragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all, container, false);

        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
        map.getUiSettings().isIndoorLevelPickerEnabled();

        position = new LatLng(13.8463337, 100.5693643);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 17);
        map.animateCamera(cameraUpdate);

        MarkerOptions G6 = new MarkerOptions();
        G6.position(G6_Document);
        G6.title("ร้านถ่ายเอกสาร จี6");
        G6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(G6);

        MarkerOptions chem = new MarkerOptions();
        chem.position(Chem_Document);
        chem.title("ร้านถ่ายเอกสาร ภาควิชาวิศวกรรมเคมี");
        chem.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(chem);

        MarkerOptions Building3 = new MarkerOptions();
        Building3.position(B3_Document);
        Building3.title("ร้านถ่ายเอกสาร อาคาร3");
        Building3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(Building3);

        MarkerOptions Civil = new MarkerOptions();
        Civil.position(Yotha_Document);
        Civil.title("ร้านถ่ายเอกสาร ภาควิศวกรรมโยธา");
        Civil.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(Civil);

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
}
