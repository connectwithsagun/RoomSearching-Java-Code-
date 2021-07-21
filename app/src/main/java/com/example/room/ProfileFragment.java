package com.example.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.room.Fragments.AccountSetting;
import com.example.room.Fragments.UserProperty;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProfileFragment extends Fragment {
    TabLayout tabLayout;
    public static ViewPager2 viewPager;
    private static final int NUM_PAGES = 2;
    private FragmentStateAdapter pagerAdapter;
    private final String[] titles = new String[]{"My Properties", "Account Setting"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.activity_profile, container, false);
        tabLayout = V.findViewById(R.id.tabLayout);
        viewPager = V.findViewById(R.id.viewPager);
        pagerAdapter= new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        //Displaying tabs
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> tab.setText(titles[position])).attach();

        return V;

    }

    private static class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(ProfileFragment profileFragment) {
            super(profileFragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: {
                    return UserProperty.newInstance("fragment 1");
                }
                case 1: {
                    return AccountSetting.newInstance("fragment 2");
                }
                default:
                    return UserProperty.newInstance("fragment 1, Default");
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}