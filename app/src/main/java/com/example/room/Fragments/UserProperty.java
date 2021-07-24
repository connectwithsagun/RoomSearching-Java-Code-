package com.example.room.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.room.Adapter.AllPropertyAdapter;
import com.example.room.Adapter.MyPropertyAdapter;
import com.example.room.Adapter.PropertyAdapter;
import com.example.room.Model.PropertyModel;
import com.example.room.R;
import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class UserProperty extends Fragment {
    List<PropertyModel> propertyList;
    MyPropertyAdapter propertyAdapter;
    RecyclerView recyclerView;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_user_property, container, false);;



        btn=v.findViewById(R.id.deleteProp);
        recyclerView = (RecyclerView)v.findViewById(R.id.rvMyProperty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        propertyAdapter = new MyPropertyAdapter(v.getContext(),propertyList);
        recyclerView.setAdapter(propertyAdapter);

        getYourPro();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



        return v;
    }

    private void getYourPro() {
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("Login", MODE_PRIVATE);

        int userid=sharedPreferences.getInt("userid",0);

        Log.e("idd", String.valueOf(userid));

        ApiInterface apiInterface= RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<PropertyModel>> call=apiInterface.getData(userid);
        call.enqueue(new Callback<List<PropertyModel>>() {
            @Override
            public void onResponse(Call<List<PropertyModel>> call, Response<List<PropertyModel>> response) {

                propertyList=response.body();
                Log.d("TAGl", String.valueOf(response.code()));


                Log.d("TAGl","Response = "+propertyList);
                propertyAdapter.setPropertyList(propertyList);

            }

            @Override
            public void onFailure(Call<List<PropertyModel>> call, Throwable t) {

            }
        });

    }

    public static UserProperty newInstance(String text){
        UserProperty userProperty=new UserProperty();
        Bundle bundle=new Bundle();
        bundle.putString("msg",text);
        userProperty.setArguments(bundle);
        return userProperty;
    }
}