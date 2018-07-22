package com.mario.example.loginsampleproject.screens;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mario.example.loginsampleproject.screens.login.LoginActivity;

public class WelcomeHome extends AppCompatActivity {

    //only for this DEMO, otherwise it does not really make any sense
    public static final int DURATION_IN_MS = 3000;
    private Handler myWelcomeHandler;
    private Runnable startActivityRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivityRunnable = getRunnable();
        myWelcomeHandler=new Handler();
        //only for this DEMO, otherwise it does not really make any sense
        myWelcomeHandler.postDelayed(startActivityRunnable, DURATION_IN_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myWelcomeHandler.removeCallbacks(startActivityRunnable);
    }

    private Runnable getRunnable() {
       return new Runnable()
        {

            @Override
            public void run() {
                Intent intent = new Intent(WelcomeHome.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }





}

