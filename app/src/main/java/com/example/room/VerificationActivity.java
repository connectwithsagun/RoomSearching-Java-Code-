package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerificationActivity extends AppCompatActivity {
    EditText editText;
    Button verify;
    EditText textView;
    public String email,otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        editText=findViewById(R.id.Code);
        textView=findViewById(R.id.RegEmail);
        verify=findViewById(R.id.btnVerify);
        Intent intent= getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            String s=(String) bundle.get("email");
            textView.setText(s);
        }

        verify.setOnClickListener(v -> {


            verifyDetails();

        });
    }
    private Boolean validatePhoneNo() {
        String val = editText.getText().toString();

        if (val.isEmpty()) {
            editText.setError("Field cannot be empty");
            return false;
        } else {
            editText.setError(null);
            //editText.setErrorEnabled(false);
            return true;
        }
    }
    private void verifyDetails() {
        Retrofit retrofit= RetrofitClient.getRetrofitInstance();
        final ApiInterface api=retrofit.create(ApiInterface.class);
        email=textView.getText().toString();
        otp=editText.getText().toString();
        api.userVerify(email,otp).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(VerificationActivity.this, " Registered Successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(VerificationActivity.this,LogInActivity.class));
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, " "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}