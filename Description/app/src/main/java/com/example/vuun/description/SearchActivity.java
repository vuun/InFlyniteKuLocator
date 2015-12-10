package com.example.vuun.description;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vuun.description.Search_Fragment;
import com.example.vuun.description.activity.DetailFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;

public class SearchActivity extends FragmentActivity {

    private ListView lv;
    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;

    //Facebook
    ShareDialog shareDialog;
    CallbackManager callbackManager;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String[] place = getResources().getStringArray(R.array.place);


        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, place));

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        //ListView lv = getListView();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, place);
        lv.setAdapter(adapter);
        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Fragment newFragment = new Search_Fragment();
                Bundle bundle = new Bundle();
                // selected item
                TextView textView = (TextView) view.findViewById(R.id.label);
                String place = textView.getText().toString();
                bundle.putString("place", place);
                newFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // and add the transaction to the back stack
                transaction.replace(android.R.id.content, newFragment);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();

                //Log.v("mSelectedProduct", product);
                // Launching new Activity on selecting single List Item
                //Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                // sending data to new activity
                //i.putExtra("place", place);
                //startActivity(i);

            }
        });

    }


    public void postFacebook(View view) {
        SharedPreferences getShare = getApplicationContext().getSharedPreferences("getShare",0);
        String getPlace = getShare.getString("sendPlace", "ERROR");
        String getDesc = getShare.getString("sendDesc", "ERROR");
        shareDialog = new ShareDialog(SearchActivity.this);
        ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                .putString("og:type", "kulocator:location")
                .putString("og:title", getPlace)
                .putString("og:description", getDesc).build();

        ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                .setActionType("kulocator:go_to").putObject("location", object)
                .build();
        ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                .setPreviewPropertyName("location")
                .setAction(action)
                .build();
        shareDialog.show(content);
//        if (ShareDialog.canShow(ShareLinkContent.class)) {
//            ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                    .setContentTitle("Hello Facebook")
//                    .setContentDescription(
//                            "The 'Hello Facebook' sample  showcases simple Facebook integration")
//                    .setContentUrl(Uri.EMPTY)
//                    .build();
//            shareDialog.show(linkContent);
//
//        }
   }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
