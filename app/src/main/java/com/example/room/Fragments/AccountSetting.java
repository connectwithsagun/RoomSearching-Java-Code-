package com.example.room.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room.ProfileUpdateActivity;
import com.example.room.R;

public class AccountSetting extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_account_setting, container, false);

        ImageView imageView=v.findViewById(R.id.Logo);
        ImageView imageView1=v.findViewById(R.id.Logo1);
        ImageButton button1=v.findViewById(R.id.Next);
        ImageButton button=v.findViewById(R.id.Next1);

        TextView tv=v.findViewById(R.id.tvTitle);
        TextView tv1=v.findViewById(R.id.tvTitle1);
        TextView tv2=v.findViewById(R.id.tvDesc);
        TextView tv3=v.findViewById(R.id.tvDesc1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ProfileUpdateActivity.class));
            }
        });

        return v;
    }
    public static AccountSetting newInstance(String text) {

        AccountSetting  f = new AccountSetting();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

}