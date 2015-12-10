package com.example.vuun.description.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    public String sendPlace;
    public String sendDesc;

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
    private final String serverUrl = "http://www.zp9039.tld.122.155.167.199.no-domain.name/KUmap/b.php";
    //private final String serverUrl = "https://ns167.pathosting.com/phpmyadmin/index.php?db=zp9039_spoodimal&token=c6ccda6e9e8313ee5f464fdf888126e5";
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

        AsyncDataClass asyncRequestObject = new AsyncDataClass();
        asyncRequestObject.execute(serverUrl," "," ");

        txtDesc = (TextView) myFragmentView.findViewById(R.id.PlaceDesc);
        txtPlaceName = (TextView) myFragmentView.findViewById(R.id.PlaceName);
//
//        txtDesc.setText(detail);
//        txtPlaceName.setText(placename);

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

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(params[0]);

            String jsonResult = "";
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("username", params[1]));
                nameValuePairs.add(new BasicNameValuePair("PlaceId", place));//ส่งค่า input
//                nameValuePairs.add(new BasicNameValuePair("password", params[2]));
                nameValuePairs.add(new BasicNameValuePair("password", params[2]));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
//            if(result.equals("") || result == null){
//                Toast.makeText(MainActivity.this, "Server connection failed", Toast.LENGTH_LONG).show();
//                return;
//            }
            String[] jsonResult = returnParsedJsonObject(result);
            System.out.println("--------------------------------- PlaceName : " + jsonResult[0]); //ค่าอยู่ในนี้ output
            System.out.println("--------------------------------- Detail : " + jsonResult[1]);//ค่าอยู่ในนี้ output

            placename = jsonResult[0];
            detail = jsonResult[1];

            SharedPreferences pref = getActivity().getSharedPreferences("getShare", 0);
            SharedPreferences.Editor edt = pref.edit();
            edt.putString("sendPlace", placename);
            edt.putString("sendDesc", detail);
            edt.commit();



            txtDesc.setText(detail);
            txtPlaceName.setText(placename);
//            System.out.println(jsonResult);
//            String jsonResultStr = jsonResult
//            if(jsonResult == 0){
//                Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
//                return;
//            }
//            if(jsonResult == 1){
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            intent.putExtra("USERNAME","PlaceName : " + jsonResult[0] + " , Detail : " + jsonResult[1]);
////            intent.putExtra("USERNAME","EIEI");
//            intent.putExtra("MESSAGE", "You have been successfully login");
//            startActivity(intent);
////            }
        }
        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return answer;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
    private String[] returnParsedJsonObject(String result){

        JSONObject resultObject = null;
        String[] returnedResult = new String[2];
        try {
            resultObject = new JSONObject(result);
            returnedResult[0] = resultObject.getString("PlaceName");
            returnedResult[1] = resultObject.getString("Detail");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnedResult;
    }



}
