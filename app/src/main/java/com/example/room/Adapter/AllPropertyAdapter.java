package com.example.room.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.DetailActivity;
import com.example.room.LogInActivity;
import com.example.room.Model.AllPropertyModel;
import com.example.room.R;
import com.example.room.RegisterActivity;

public class AllPropertyAdapter extends RecyclerView.Adapter<AllPropertyAdapter.ViewHolder> {
   private AllPropertyModel[] listData;

    public AllPropertyAdapter(AllPropertyModel[] listData) {
        this.listData = listData;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.allproperty,parent,false);
        ViewHolder  viewHolder= new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // final AllPropertyModel myListData=listData[position];
        holder.imageView.setImageResource(listData[position].getImageId());
        holder.tv1.setText(listData[position].getTitle());
        holder.tv2.setText(listData[position].getLocation());
        holder.tv3.setText(listData[position].getAmount());

    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv1,tv2,tv3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.proImage);
            this.tv1=itemView.findViewById(R.id.title);
            this.tv2=itemView.findViewById(R.id.location);
            this.tv3=itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(v.getContext(), DetailActivity.class);
                v.getContext().startActivity(intent);
            });


        }

    }
}
