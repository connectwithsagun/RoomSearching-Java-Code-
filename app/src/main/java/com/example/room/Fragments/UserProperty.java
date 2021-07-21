package com.example.room.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.room.Adapter.MyPropertyAdapter;
import com.example.room.Model.MyPropertyModel;
import com.example.room.R;

public class UserProperty extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_user_property, container, false);;


        MyPropertyModel[] myListData=new MyPropertyModel[]{
         new MyPropertyModel(R.drawable.kitchen,"Room","200sq.ft","For sale","Bha-10,Chitwan","Pending"),
          new MyPropertyModel(R.drawable.room,"Room","200sq.ft","For sale","Bha-10,Chitwan","Approved"),
          new MyPropertyModel(R.drawable.room,"Room","200sq.ft","For sale","Bha-10,Chitwan","Pending"),
         new MyPropertyModel(R.drawable.room,"Room","200sq.ft","For sale","Bha-10,Chitwan","Pending"),
         new MyPropertyModel(R.drawable.room,"Room","200sq.ft","For sale","Bha-10,Chitwan","Pending")
        };

        RecyclerView recyclerView = v.findViewById(R.id.rvMyProperty);
        MyPropertyAdapter adapter=new MyPropertyAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);



        return v;
    }
    public static UserProperty newInstance(String text){
        UserProperty userProperty=new UserProperty();
        Bundle bundle=new Bundle();
        bundle.putString("msg",text);
        userProperty.setArguments(bundle);
        return userProperty;
    }
}