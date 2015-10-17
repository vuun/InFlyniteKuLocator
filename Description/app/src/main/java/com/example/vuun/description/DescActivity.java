package com.example.vuun.description;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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


public class DescActivity extends AppCompatActivity {
    TextView txtDesc;
    TextView txtPlaceName;

    private String placename;
    private String detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        txtDesc = (TextView) findViewById(R.id.PlaceDesc);
        txtPlaceName = (TextView) findViewById(R.id.PlaceName);
        try {
        getPlaceD(); }
        catch(IOException e){
        }
        txtDesc.setText(placename);
        txtPlaceName.setText(detail);
    }

    public void sendActFav(View view)
    {
        // Do something in response to button
       // go to MainActivitySearch
        Intent intent = new Intent(this, FavActivity.class);
        startActivity(intent);
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
        PlaceDetail obj = gson.fromJson(output, PlaceDetail.class);
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



