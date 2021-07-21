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
import android.widget.Toast;

import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, password, phone, location;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        phone = findViewById(R.id.etPhone);
        location = findViewById(R.id.etAddress);
        button = findViewById(R.id.btnRegister);

        button.setOnClickListener(v -> {

            if (!validateName() | !validateEmail() | !validatePassword() | !validatePhoneNo() | !validateLocation()) {
                return;
            }
            Register();
        });
    }
    private Boolean validateName() {
        String val = name.getText().toString();

        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            //  emailPattern.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            // password.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        String val = phone.getText().toString();

        if (val.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        } else {
                phone.setError(null);
                //phone.setErrorEnabled(false);
                return true;
        }
    }
    private Boolean validateLocation() {
        String val = location.getText().toString();

        if (val.isEmpty()) {
            location.setError("Field cannot be empty");
            return false;
        } else {
            location.setError(null);
            //phone.setErrorEnabled(false);
            return true;
        }
    }
    private void Register() {
        Retrofit retrofit= RetrofitClient.getRetrofitInstance();
        final ApiInterface api=retrofit.create(ApiInterface.class);
        api.userRegister(name.getText().toString(),email.getText().toString(),password.getText().toString(),phone.getText().toString(),
                location.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User reg", Toast.LENGTH_SHORT).show();
                      // startActivity(new  Intent(RegisterActivity.this,VerificationActivity.class));
                    Intent i=new Intent(RegisterActivity.this,VerificationActivity.class);
                    i.putExtra("email",email.getText().toString());
                    startActivity(i);
                }else{
                    Toast.makeText(RegisterActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error "+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}