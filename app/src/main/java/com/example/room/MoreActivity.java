package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MoreActivity extends AppCompatActivity {
    TextView tvLogin,LogoOut;
    ImageView ivLogin;
    LinearLayout contactUs;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar;
        toolbar=findViewById(R.id.actionbar);

       toolbar.setTitle("More");
       toolbar.setTitleTextColor(Color.BLACK);
       toolbar.setNavigationIcon(R.drawable.back);
       setSupportActionBar(toolbar);

        tvLogin=findViewById(R.id.tvLogIn);
        ivLogin=findViewById(R.id.ivLogin);
        LogoOut=findViewById(R.id.tvLogOut);
        contactUs=findViewById(R.id.ly_ContactUs);

        // setting preferences for login logout
        sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        Boolean b1=sharedPreferences.getBoolean("Login",true);
        if(b1){
            LogoOut.setVisibility(View.GONE);
        }else{
            tvLogin.setVisibility(View.GONE);
        }
        LogoOut.setOnClickListener(v -> {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.clear();
            editor.apply();
            LogoOut.setVisibility(View.GONE);
            tvLogin.setVisibility(View.VISIBLE);
        });

        //open Login Activity
        tvLogin.setOnClickListener(v -> {
            startActivity(new  Intent(MoreActivity.this,LogInActivity.class));
        });

        // open Contact Us Activity
        contactUs.setOnClickListener(v -> startActivity(new  Intent(MoreActivity.this,ContactUs.class)));

    }

}