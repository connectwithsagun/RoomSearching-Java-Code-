package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.Adapter.PropertyAdapter;
import com.example.room.Model.PropertyModel;

public class SelectedAreaActivity extends AppCompatActivity {
 RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_area);

        Toolbar toolbar=(Toolbar) findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Area");
        toolbar.setNavigationIcon(R.drawable.back);


//        recyclerView=findViewById(R.id.rvSelectedArea);
//        recyclerView.setHasFixedSize(true);
//        PropertyAdapter adapter = new PropertyAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

    }

}