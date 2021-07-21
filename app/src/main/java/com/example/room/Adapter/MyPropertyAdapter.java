package com.example.room.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.DetailActivity;
import com.example.room.Model.AllPropertyModel;
import com.example.room.Model.MyPropertyModel;
import com.example.room.Model.PropertyModel;
import com.example.room.R;

public class MyPropertyAdapter extends RecyclerView.Adapter<MyPropertyAdapter.ViewHolder> {
    private MyPropertyModel[] listData;

    public MyPropertyAdapter(MyPropertyModel[] listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.myproperty_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyPropertyModel myListData=listData[position];
        holder.imageView.setImageResource(listData[position].getImage());
        holder.textView.setText(listData[position].getPropertyType());
        holder.textView1.setText(listData[position].getPropertyArea());
        holder.textView2.setText(listData[position].getPropertyTitle());
        holder.textView3.setText(listData[position].getPropertyLocation());
        holder.textView4.setText(listData[position].getStatus());
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView,textView1,textView2,textView3,textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.propertyImage);
            textView=itemView.findViewById(R.id.propertyType);
            textView1=itemView.findViewById(R.id.propertyArea);
            textView2=itemView.findViewById(R.id.propertyTitle);
            textView3=itemView.findViewById(R.id.propertyLocation);
            textView4=itemView.findViewById(R.id.status);

            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(v.getContext(), DetailActivity.class);
                v.getContext().startActivity(intent);
            });
        }
    }
}
