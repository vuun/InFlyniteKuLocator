package com.example.vuun.description;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuun.description.app.AppController;
import com.example.vuun.description.app.SessionManager;

public class AccountActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtEmail;
    Button btnLogout;
    Button btnFav;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnFav = (Button) findViewById(R.id.btnFav);

        //setting toolbar
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);


        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

//        String name = AppController.getString(getApplicationContext(), "name");
//        String email = AppController.getString(getApplicationContext(), "email");

        String name = session.getUsername();
        String email = session.getEmail();
        txtName.setText(name);
        txtEmail.setText(email);


        btnFav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendtoFav();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }


    private void logoutUser() {
        session.setLogin(false);
        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void sendtoFav() {
        Intent intent = new Intent(AccountActivity.this, FavActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Toast.makeText(getApplicationContext(), "Go back to MainMenu", Toast.LENGTH_SHORT).show();
        finish();
    }
}
