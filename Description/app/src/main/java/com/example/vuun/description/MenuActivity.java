package com.example.vuun.description;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
//
//    Display dp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//    int screen_width = dp.getWidth();
//    int screen_height = dp.getHeight();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        ImageButton butt1 = (ImageButton) findViewById(R.id.accountButton);
//        ImageButton butt2 = (ImageButton) findViewById(R.id.mapButton);
//        ImageButton butt3 = (ImageButton) findViewById(R.id.searchButton);
//        butt1.setScaleX(screen_width/5);
//        butt1.setScaleY(screen_height/5);
   //      resize(butt1);
       // resize(butt2);
       // resize(butt3);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_my, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void resize(View view) {
//        ImageButton butt1 = (ImageButton) view;
//
//        ViewGroup.LayoutParams butt_layout = view.getLayoutParams();
//        butt_layout.height = screen_width/ 10;
//        butt_layout.width = screen_height/10;
//        butt1.setLayoutParams(butt_layout);
//    }

    /** Called when the user clicks the Send button */

    public void sendToAccount(View view) {
        // Do something in response to button

//        open when created SendAccountActivity class
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Send button */
    public void sendToMap(View view) {
        // Do something in response to button
//        Display dp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//        int screen_width = dp.getWidth();
//        int screen_height = dp.getHeight();
//
//        ImageButton butt1 = (ImageButton) view;
//
//        ViewGroup.LayoutParams butt1_layout = view.getLayoutParams();
//        butt1_layout.height = screen_width/10;
//        butt1_layout.width = screen_height/10;
//
//        butt1.setLayoutParams(butt1_layout);
//
//        open when created SendMapActivity class
//        Intent intent = new Intent(this, SendMapActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);

    }

    /** Called when the user clicks the Send button */
    public void sendToSearch(View view) {
        Intent intent = new Intent(this, MainActivitySearch.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MenuActivity.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you want to exit?");

        alertDialog.show();
    }

}
