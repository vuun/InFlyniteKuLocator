package com.example.vuun.description;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuun.description.R;
import com.example.vuun.description.activity.DetailFragment;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;
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
 * Created by Teevarapat on 12/3/2015.
 */
public class Search_Fragment extends Fragment implements GoogleMap.OnInfoWindowClickListener {

    MapView mapView;
    GoogleMap map;
    LatLng position;
    private String place;
    
    private ArrayList<Marker> markers = new ArrayList<Marker>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        place = bundle.getString("place");
        Log.d("TAG", "have value = " + place);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searchmap, container, false);

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
        
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(place.equals("ห้องน้ำ วิศวกรรม คอมพิวเตอร์")){
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8461058,100.5686874))
                    .title("ห้องน้ำ วิศวกรรมคอมพิวเตอร์")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            markers.add(marker);
            place = "tl_01";
        }

        else if(place.equals("ห้องน้ำ วิศวกรรม เคมี")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8459238, 100.56988))
                    .title("ห้องน้ำ วิศวกรรมเคม")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            markers.add(marker);
            place = "tl_02";
        }

        else if(place.equals("ห้องน้ำ วิศวกรรม โยธา")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8456741,100.5690166))
                    .title("ห้องน้ำ วิศวกรรม โยธา")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            markers.add(marker);
            place = "tl_03";
        }

        else if(place.equals("ห้องน้ำ IUP")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8459254,100.5698766))
                    .title("ห้องน้ำ IUP")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            markers.add(marker);
            place = "tl_04";
        }

        else if(place.equals("ห้องน้ำ อาคารชูชาติ")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8472256,100.5703862))
                    .title("ห้องน้ำ อาคารชูชาติ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            markers.add(marker);
            place = "tl_05";
        }

        else if(place.equals("ร้านถ่ายเอกสาร G6")) {
                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.84622585,100.5693774))
                        .title("ร้านถ่ายเอกสาร G6ิ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                markers.add(marker);
                place = "cp_01";
        }

        else if(place.equals("ร้านถ่ายเอกสาร ภาควิชาวิศวกรรม เคมี")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8459245,100.5698786))
                    .title("ร้านถ่ายเอกสาร ภาควิชาวิศวกรรมเคมีิ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            markers.add(marker);
            place = "cp_02";
        }

        else if(place.equals("ร้านถ่ายเอกสาร อาคาร 3")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.845903,100.5698964))
                    .title("ร้านถ่ายเอกสาร อาคาร 3")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            markers.add(marker);
            place = "cp_03";
        }

        else if(place.equals("ร้านถ่ายเอกสาร ภาควิศวกรรม โยธา")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8460843,100.5685586))
                    .title("ร้านถ่ายเอกสาร ภาควิศวกรรมโยธา")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            markers.add(marker);
            place = "cp_04";
        }

        else if(place.equals("ร้านเครื่องเขียน ภาควิศวกรรม โยธา")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8460742,100.5685375))
                    .title("ร้านเครื่องเขียน ภาควิศวกรรมโยธา")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            markers.add(marker);
            place = "st_01";
        }

        else if(place.equals("ร้านเครื่องเขียน ลานวิศวกรรม การบิน")) {
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(13.8454589,100.5703644))
                    .title("ร้านเครื่องเขียน ลานวิศวกรรมการบิน")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            markers.add(marker);
            place = "st_02";
        }
        // Inflate the layout for this fragment
        return rootView;
    }

    public Search_Fragment() {

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
            bundle.putString("PLACE", place);
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // and add the transaction to the back stack
            transaction.replace(android.R.id.content, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        }
    }
}
