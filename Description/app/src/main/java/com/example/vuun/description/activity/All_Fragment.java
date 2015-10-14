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
public class All_Fragment extends Fragment {

    MapView mapView;
    GoogleMap map;
    LatLng position;

    private final LatLng Toilet_computer = new LatLng(13.8461058, 100.5686874);
    private final LatLng Toilet_chem1 = new LatLng(13.8459238, 100.56988);
    private final LatLng Toilet_chem2  = new LatLng(13.8459236, 100.569877);
    private final LatLng Toilet_IUP = new LatLng(13.8459254, 100.5698766);
    private final LatLng Toilet_chochard = new LatLng(13.8472256, 100.5703862);
    private final LatLng Toilet_Yotha = new LatLng(13.8456741, 100.5690166);

    private final LatLng G6_Document = new LatLng(13.84622585, 100.5693774);
    private final LatLng Chem_Document = new LatLng(13.8459245, 100.5698786);
    private final LatLng B3_Document = new LatLng(13.845903, 100.5698964);
    private final LatLng Yotha_Document = new LatLng(13.8460843, 100.5685586);

    private final LatLng Yotha_Store = new LatLng(13.8460742, 100.5685375);
    private final LatLng Arrow_Store = new LatLng(13.8454589, 100.5703644);

    public All_Fragment() {

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

//        if (map != null) {
//            map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//
//                @Override
//                public void onMyLocationChange(Location location) {
//                    position = new LatLng(location.getLatitude(), location.getLongitude());
//                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 14);
//                    map.animateCamera(cameraUpdate);
//                    //map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("It's Me!"));
//                }
//            });
//
//        }

        MarkerOptions Toilet_com = new MarkerOptions();
        Toilet_com.position(Toilet_computer);
        Toilet_com.title("ห้องน้ำ ภาควิชาวิศวกรรมคอมพิวเตอร์");
        Toilet_com.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(Toilet_com);

        MarkerOptions Toilet_chem = new MarkerOptions();
        Toilet_chem.position(Toilet_chem1);
        Toilet_chem.title("ห้องน้ำ ภาควิชาวิศวกรรมเคมี(ข้างร้านถ่ายเอกสาร)");
        Toilet_chem.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(Toilet_chem);

        MarkerOptions Toilet_chemtwo = new MarkerOptions();
        Toilet_chemtwo.position(Toilet_chem2);
        Toilet_chemtwo.title("ห้องน้ำ ภาควิชาวิศวกรรมเคมี(ใต้ห้องสโม)");
        Toilet_chemtwo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(Toilet_chemtwo);

        MarkerOptions Toilet_iup = new MarkerOptions();
        Toilet_iup.position(Toilet_IUP);
        Toilet_iup.title("ห้องน้ำ IUP");
        Toilet_iup.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(Toilet_iup);

        MarkerOptions Toilet_chard = new MarkerOptions();
        Toilet_chard.position(Toilet_chochard);
        Toilet_chard.title("ห้องน้ำ อาคารชูชาติ");
        Toilet_chard.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(Toilet_chard);

        MarkerOptions Toilet_civil = new MarkerOptions();
        Toilet_civil.position(Toilet_Yotha);
        Toilet_civil.title("ห้องน้ำ ภาควิชาวิศวกรรมโยธา");
        Toilet_civil.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(Toilet_civil);
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
    ////////////////////////////////////////////////////////////////////////////////////////////////

        MarkerOptions Civil_S = new MarkerOptions();
        Civil_S.position(Yotha_Store);
        Civil_S.title("ร้านเครื่องเขียน ภาควิศวกรรมโยธา");
        Civil_S.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        map.addMarker(Civil_S);

        MarkerOptions Arrow_S = new MarkerOptions();
        Arrow_S.position(Arrow_Store);
        Arrow_S.title("ร้านถ่ายเอกสาร ลานวิศวกรรมการบิน");
        Arrow_S.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        map.addMarker(Arrow_S);
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
