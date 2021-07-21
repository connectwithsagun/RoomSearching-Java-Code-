package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileUpdateActivity extends AppCompatActivity {

    ImageButton editPhoto;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        editPhoto=findViewById(R.id.editPhoto);
        profileImage=findViewById(R.id.Avatar);

        editPhoto.setOnClickListener(v -> {
            Toast.makeText(ProfileUpdateActivity.this, "Edit button", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);

        });
    }
}