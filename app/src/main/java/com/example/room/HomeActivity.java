package com.example.room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.example.room.Adapter.FragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private NoSwipePager viewPager;
    BottomNavigationView navigation;

    HomeFragment frag1 = new HomeFragment();
    PropertyFragment frag2 = new PropertyFragment();
    AddPropertyFragment frag3 = new AddPropertyFragment();
    ProfileFragment frag4=new ProfileFragment();

    SearchView searchView;
    ImageView imageView;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.ivMore);
        searchView=findViewById(R.id.searchView);
        navigation = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPager);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        Boolean b1=sharedPreferences.getBoolean("Login",true);

        imageView.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,MoreActivity.class)));
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
                    if(b1){
                        Intent intent=new Intent(HomeActivity.this,LogInActivity.class);
                        startActivity(intent);
                    }else{
                        viewPager.setCurrentItem(2);
                    }
                    return true;
                case R.id.tab4:
                    if(b1){
                        Intent intent=new Intent(HomeActivity.this,LogInActivity.class);
                        startActivity(intent);

                    }else{
                        viewPager.setCurrentItem(3);
                    }
                    return true;
            }
            return false;
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}