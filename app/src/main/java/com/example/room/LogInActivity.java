package com.example.room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LogInActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    EditText email,password;
    Button btn;
    TextInputLayout layout1,layout2;
    ImageView imageView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
       btn=findViewById(R.id.btnLogin);
       imageView=findViewById(R.id.ivLogo);
       textView1=findViewById(R.id.tvTitle);
       textView2=findViewById(R.id.tvRegister);
       textView3=findViewById(R.id.tvJoin);
       email=findViewById(R.id.userEmail);
       layout1=findViewById(R.id.etEmail);
       layout2=findViewById(R.id.etPassword);
       password=findViewById(R.id.userPassword);


        textView3.setOnClickListener(v ->
                startActivity(new  Intent(LogInActivity.this,RegisterActivity.class)));

        btn.setOnClickListener(v -> {
            if(!validateEmail()| ! validatePassword()){
                return;
            }
            login();
        });
    }
    public void login(){
        Retrofit retrofit=RetrofitClient.getRetrofitInstance();
        final ApiInterface api=retrofit.create(ApiInterface.class);
        api.userLogin(email.getText().toString(),password.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    String s= null;
                    try {
                        s = response.body().string();
                        JSONObject responseBody=new JSONObject(s);
                        String token=responseBody.getString("token");
                        Log.e("token",token);
                        editor.putString("token",token);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }



                        Toast.makeText(LogInActivity.this,"Login Successful" ,Toast.LENGTH_SHORT).show();
                 //sending to another activity

                    // sharedPreferences

                    boolean loggedIn =sharedPreferences.getBoolean("Login",true);


                    if(loggedIn){


                        editor.putBoolean("Login",false);
                        editor.apply();

                        btn.setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
                        textView1.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.GONE);
                        email.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        layout1.setVisibility(View.GONE);
                        layout2.setVisibility(View.GONE);

                        startActivity(new Intent(LogInActivity.this,HomeActivity.class));

                    }
                }else{
                    Toast.makeText(LogInActivity.this, "incorrect Credential", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LogInActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private Boolean validateEmail(){
        String val= email.getText().toString();
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field Cannot be empty");
            return false;
        }else if(!val.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}