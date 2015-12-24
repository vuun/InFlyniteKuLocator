package com.example.vuun.description;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuun.description.app.SessionManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;

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
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class FavActivity extends AppCompatActivity {
    private final String serverUrl = "http://www.zp9039.tld.122.155.167.199.no-domain.name/KUmap/getFavorite.php";
//    private List<Category> catList;

    private SessionManager session;
    private String email;


    private String[] place = new String[0] ;

    ShareDialog shareDialog;
    CallbackManager callbackManager;
    private ListView lv;
    // Listview Adapter
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
        AsyncDataClass asyncRequestObject = new AsyncDataClass();
        asyncRequestObject.execute(serverUrl, "", "");

        //String[] place = getResources().getStringArray(R.array.place);
        //String name = session.getUsername();


    }
    private void logoutUser() {
        session.setLogin(false);
        Intent intent = new Intent(FavActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

//
//    private void initData() {
//        catList = new ArrayList<Category>();
//
//        Category cat1 = createCategory("Games", "Game for console", 1);
//        cat1.setItemList(createItems("Game Item", "This is the game n.", 5));
//
//        Category cat2 = createCategory("Mobile Phone", "All the mobile phone", 2);
//        cat2.setItemList(createItems("Phone Item", "This is the phone n.", 5));
//
//        catList.add(cat1);
//        catList.add(cat2);
//    }
//
//    private Category createCategory(String name, String descr, long id) {
//        return new Category(id, name, descr);
//    }
//
//
//    private List<ItemDetail> createItems(String name, String descr, int num) {
//        List<ItemDetail> result = new ArrayList<ItemDetail>();
//
//        for (int i=0; i < num; i++) {
//            ItemDetail item = new ItemDetail(i, 0, "item" + i, "Descr" + i);
//            result.add(item);
//        }
//
//        return result;
//    }

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
                email = session.getEmail();
                nameValuePairs.add(new BasicNameValuePair("email", email));//ส่งค่า input
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
            JSONArray jsonResult = null;
            try {
                jsonResult = new JSONArray(result);
                System.out.println(jsonResult.toString());


            for (int i = 0; i < jsonResult.length(); i++) {
                try {
                    System.out.println("--------------------------------- PlaceName : " + jsonResult.get(i)); //ค่าอยู่ในนี้ output
                    place = Arrays.copyOf(place, place.length + 1);
                    place[i] = jsonResult.get(i).toString() ;
                    System.out.println("!!!!!!!added PlaceName : " + place[i]); //ค่าอยู่ในนี้ output

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            email = session.getEmail();
            Log.v("fcheck", "email : " + email);
            if (place != null) {
                lv = (ListView) findViewById(R.id.list);
                adapter = new ArrayAdapter<String>(lv.getContext(), R.layout.list_item, R.id.label, place);
                lv.setAdapter(adapter);

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
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            intent.putExtra("USERNAME",save);
//            intent.putExtra("MESSAGE", "You have been successfully login");
//            startActivity(intent);
//            }
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
    public void postFacebook(View view) {
        SharedPreferences getShare = getSharedPreferences("getShare", Context.MODE_PRIVATE);
        String getPlace = getShare.getString("sendPlace", "ERROR");
        String getDesc = getShare.getString("sendDesc", "ERROR");
        shareDialog = new ShareDialog(FavActivity.this);
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
}
