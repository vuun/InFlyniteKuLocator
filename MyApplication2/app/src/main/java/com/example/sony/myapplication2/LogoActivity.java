package com.example.sony.myapplication2;

        import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Display;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.content.Intent;
        import android.view.ViewGroup;
        import android.view.WindowManager;
        import android.widget.ImageButton;
        import android.widget.Toast;

        import android.os.Handler;
        import java.util.logging.LogRecord;

public class LogoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
    }
    protected void onResume()
    {
        super.onResume();
        Handler handler= new Handler( );
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent sendToMenu = new Intent(getApplicationContext(), MyActivity.class);
                startActivity(sendToMenu);
            }
        },5000);

 /*       Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(3000);
                        handler.post(this);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        finish();
        Intent sendToMenu = new Intent(getApplicationContext(), MyActivity.class);
        startActivity(sendToMenu);*/
    }
 }
