package com.app.doctorsdoor.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.doctorsdoor.R;
import com.app.doctorsdoor.session.AppSession;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 2000; //2 sec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
                SplashScreen.this.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
