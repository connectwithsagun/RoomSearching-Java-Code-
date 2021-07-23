package com.example.room;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.room.Adapter.PopularAreasAdapter;
import com.example.room.Adapter.PropertyAdapter;
import com.example.room.Model.PopularAreasModel;
import com.example.room.Model.PropertyModel;
import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView, recyclerView1;
    List<PropertyModel> propertyList;
    PropertyAdapter propertyAdapter;
    PopularAreasAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        PopularAreasModel[] myListData = new PopularAreasModel[]{
                new PopularAreasModel(R.drawable.ktm,
                        "Kathmandu"),
                new PopularAreasModel(R.drawable.ctn,
                        "Chitwan"),
                new PopularAreasModel(R.drawable.pkr,
                        "Pokhara"),
                new PopularAreasModel(R.drawable.btl,
                        "Butwal")

        };
        recyclerView = (RecyclerView)view.findViewById(R.id.rvPopular);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager1);
        adapter = new PopularAreasAdapter(view.getContext(), Arrays.asList(myListData));
        recyclerView.setAdapter(adapter);
        adapter.setPopularAreasModelList(Arrays.asList(myListData));




        //for properties recyclerView
        recyclerView1 = (RecyclerView)view.findViewById(R.id.rvProperty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView1.setLayoutManager(layoutManager);
        propertyAdapter = new PropertyAdapter(view.getContext(),propertyList);
        recyclerView1.setAdapter(propertyAdapter);

        availableProperty();

        return view;
    }

    private void availableProperty() {
        Log.v("Sdcx","fvfd");
        //api fetch
        ApiInterface apiInterface= RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<PropertyModel>> call=apiInterface.getProperty();
        call.enqueue(new Callback<List<PropertyModel>>() {
            @Override
            public void onResponse(Call<List<PropertyModel>> call, Response<List<PropertyModel>> response) {
                propertyList=response.body();
                Log.d("TAGll", String.valueOf(response.code()));

                Log.d("TAG","Response = "+propertyList);
                propertyAdapter.setPropertyList(propertyList);
                propertyAdapter.setOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onFailure(Call<List<PropertyModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

    }
    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            PropertyModel thisItem = propertyList.get(position);
            startActivity(new Intent(getContext(),DetailActivity.class).putExtra("data",propertyList.get(position)));
        }
    };


}
