package com.example.vuun.description;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vuun.description.activity.All_Fragment;
import com.example.vuun.description.activity.Document_Fragment;
import com.example.vuun.description.activity.FragmentDrawer;
import com.example.vuun.description.activity.Store_Fragment;
import com.example.vuun.description.activity.Toilet_Fragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookDialog;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;

public class MapActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    ShareDialog shareDialog;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if(id == R.id.action_search){
            //Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new All_Fragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new Store_Fragment();
                title = getString(R.string.title_stationary);
                break;
            case 2:
                fragment = new Toilet_Fragment();
                title = getString(R.string.title_toilet);
                break;
            case 3:
                fragment = new Document_Fragment();
                title = getString(R.string.title_document);
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();


            // set the maptoolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Toast.makeText(getApplicationContext(), "Go back to MainMenu", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void postFacebook(View view) {
        SharedPreferences getShare = getSharedPreferences("getShare", Context.MODE_PRIVATE);
        String getPlace = getShare.getString("sendPlace", "ERROR");
        String getDesc = getShare.getString("sendDesc", "ERROR");
        shareDialog = new ShareDialog(MapActivity.this);
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
