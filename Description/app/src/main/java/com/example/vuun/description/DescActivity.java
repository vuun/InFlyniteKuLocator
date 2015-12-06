//may not be use this anymore
package com.example.vuun.description;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
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
    ShareDialog shareDialog;


    private String place_name;
    private String detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        shareDialog = new ShareDialog(this);


        txtDesc = (TextView) findViewById(R.id.PlaceDesc);
        txtPlaceName = (TextView) findViewById(R.id.PlaceName);

        txtDesc.setText(place_name);
        txtPlaceName.setText(detail);
    }

    public void sendActFav(View view)
    {
        // Do something in response to button
       // go to MainActivitySearch
        Intent intent = new Intent(this, FavActivity.class);
        startActivity(intent);
    }



    public void postFacebook(View view){

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.EMPTY)
                    .build();
            shareDialog.show(linkContent);
        }
    }

}
//
//
//
