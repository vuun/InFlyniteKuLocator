package com.example.vuun.description.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuun.description.FavActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.vuun.description.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    class ClassTest {
        private String PlaceName;
        private String Detail;
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //private double ID;
    private String place;

    //for connect to db
    TextView txtDesc;
    TextView txtPlaceName;
    ImageButton btnFav;

    private String placename;
    private String detail;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        //ID = bundle.getDouble("ID");
        place = bundle.getString("PLACE");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.activity_desc, container, false);

//        TextView ID_place = (TextView)myFragmentView.findViewById(R.id.ID);
//        ID_place.setText(Double.toString(ID));
        //TextView place_place = (TextView)myFragmentView.findViewById(R.id.ID);
        //place_place.setText(place);
        btnFav = (ImageButton) myFragmentView.findViewById(R.id.buttonfav);

        txtDesc = (TextView) myFragmentView.findViewById(R.id.PlaceDesc);
        txtPlaceName = (TextView) myFragmentView.findViewById(R.id.PlaceName);
        txtPlaceName.setTypeface(null, Typeface.BOLD);
//        try {
//            getPlaceD(); }
//        catch(IOException e){
//        }
//        txtDesc.setText("abx");
//        txtPlaceName.setText("aaa");

        btnFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendActFav(v);
            }
        });
        return myFragmentView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    public void sendActFav(View view)
    {
        // Do something in response to button
        // go to MainActivitySearch
        Intent myIntent = new Intent(getActivity(), FavActivity.class);
        getActivity().startActivity(myIntent);
    }


    public void getPlaceD() throws IOException {
        Gson gson = new Gson();
        URL url = new URL("http://inzzpk.in.th/a.php");
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("PlaceId", "tl_01"); // ส่งพารามิเตอร์แก้ตรงนี้

        StringBuilder postData = addParam(params);
        String urlParameters = postData.toString();

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(urlParameters);
        writer.flush();

        String output;
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        output = reader.readLine();
        ClassTest obj = gson.fromJson(output, ClassTest.class);
        System.out.println(obj.PlaceName); // obj.PlaceName เก็บค่า PlaceName
        placename = obj.PlaceName;
        System.out.println(obj.Detail); // obj.PlaceName เก็บค่า Detail
        detail = obj.Detail;
        writer.close();
        reader.close();
    }

    public StringBuilder addParam(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return postData;
    }


}
