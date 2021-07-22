package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.Adapter.PopularAreasAdapter;
import com.example.room.Adapter.PropertyAdapter;
import com.example.room.Model.PopularAreasModel;
import com.example.room.Model.PropertyModel;
import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedAreaActivity extends AppCompatActivity {
 RecyclerView recyclerView;
 PopularAreasModel popularAreasModel;
    List<PropertyModel> propertyList;
    PropertyAdapter propertyAdapter;
    String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_area);

        Toolbar toolbar=(Toolbar) findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Area");
        toolbar.setNavigationIcon(R.drawable.back);

        Intent intent=getIntent();
        if(intent.getExtras()!=null) {
            popularAreasModel = (PopularAreasModel) intent.getSerializableExtra("data");
            locationName = popularAreasModel.getDescription();
        }

        recyclerView= (RecyclerView)findViewById(R.id.rvSelectedArea);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SelectedAreaActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        propertyAdapter = new PropertyAdapter(this,propertyList);
        recyclerView.setAdapter(propertyAdapter);

        SearchedProperty();
    }

    private void SearchedProperty() {
        String location=locationName;
        Log.e("lo",location);
        ApiInterface apiInterface= RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<PropertyModel>> cal=apiInterface.getSearch(location);
        cal.enqueue(new Callback<List<PropertyModel>>() {
            @Override
            public void onResponse(Call<List<PropertyModel>> call, Response<List<PropertyModel>> response) {
                Log.e("lo", String.valueOf(response.code()));
                propertyList=response.body();
                Log.d("TAG","Response = "+propertyList);
                propertyAdapter.setPropertyList(propertyList);

                propertyAdapter.setOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onFailure(Call<List<PropertyModel>> call, Throwable t) {

            }
        });
    }
    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            PropertyModel thisItem = propertyList.get(position);
            startActivity(new Intent(SelectedAreaActivity.this,DetailActivity.class).putExtra("data",propertyList.get(position)));
        }
    };

}