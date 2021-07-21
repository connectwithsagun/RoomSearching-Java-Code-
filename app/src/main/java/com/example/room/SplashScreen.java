package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences onBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(() -> {
            onBoard=getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
            boolean isFirstTime=onBoard.getBoolean("firstTime",true);

            if(isFirstTime){
                SharedPreferences.Editor editor=onBoard.edit();
                editor.putBoolean("firstTime",false);
                editor.apply();
                Intent i=new Intent(SplashScreen.this,
                        OnBoarding.class);
                startActivity(i);
                finish();
            }else{
                Intent i=new Intent(SplashScreen.this,
                        HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, 500);
    }
}