package com.example.room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.Adapter.FragmentAdapter;
import com.example.room.Model.PropertyModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private NoSwipePager viewPager;
    BottomNavigationView navigation;
//    RecyclerView recyclerView;
//    ArrayList<PropertyModel> propertyList;
//    //SearchViewAdapter propertyAdapter;

    HomeFragment frag1 = new HomeFragment();
    PropertyFragment frag2 = new PropertyFragment();
    AddPropertyFragment frag3 = new AddPropertyFragment();
    ProfileFragment frag4=new ProfileFragment();

    ImageView imageView;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.ivMore);
        navigation = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Room Searching");
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);

        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        Boolean b1 = sharedPreferences.getBoolean("Login", true);

        imageView.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MoreActivity.class)));
        //optimisation
        viewPager.setOffscreenPageLimit(4);
        viewPager.setPagingEnabled(false);
        FragmentAdapter.BottomBarAdapter pagerAdapter = new FragmentAdapter.BottomBarAdapter(getSupportFragmentManager());
        pagerAdapter.addFragments(frag1);
        pagerAdapter.addFragments(frag2);
        pagerAdapter.addFragments(frag3);
        pagerAdapter.addFragments(frag4);

        viewPager.setAdapter(pagerAdapter);
        //Handling the tab clicks
        navigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.tab1:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.tab2:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.tab3:
                    // setting preferences for login logout
                    if (b1) {
                        Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
                        startActivity(intent);
                    } else {
                        viewPager.setCurrentItem(2);
                    }
                    return true;
                case R.id.tab4:
                    if (b1) {
                        Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
                        startActivity(intent);

                    } else {
                        viewPager.setCurrentItem(3);
                    }
                    return true;
            }
            return false;
        });

//        //for searchbar4
//        recyclerView = (RecyclerView)findViewById(R.id.tule);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        SearchList();
//    }
//    private void SearchList() {
//        String s=null;
//        ApiInterface apiInterface= RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
//        Call<List<PropertyModel>> call=apiInterface.getData(s);
//        call.enqueue(new Callback<List<PropertyModel>>() {
//            @Override
//            public void onResponse(Call<List<PropertyModel>> call, Response<List<PropertyModel>> response) {
//
//            }
//            @Override
//            public void onFailure(Call<List<PropertyModel>> call, Throwable t) {
//            }
//        });
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbaritem, menu);
//        MenuItem search = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//        search(searchView);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        return super.onOptionsItemSelected(item);
//    }
//    private void search(SearchView searchView) {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (propertyAdapter != null){
//                    propertyAdapter.getFilter().filter(newText);
//                }
//                return true;
//            }
//        });
//    }
    }
}