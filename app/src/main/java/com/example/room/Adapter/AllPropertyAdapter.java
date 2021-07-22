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

import com.bumptech.glide.Glide;
import com.example.room.DetailActivity;
import com.example.room.LogInActivity;
import com.example.room.Model.AllPropertyModel;
import com.example.room.Model.PropertyModel;
import com.example.room.R;
import com.example.room.RegisterActivity;

import java.util.List;

public class AllPropertyAdapter extends RecyclerView.Adapter<AllPropertyAdapter.ViewHolder> {
    Context context;
    List<PropertyModel> propertyList;
    private View.OnClickListener mOnItemClickListener;

    public AllPropertyAdapter(Context context, List<PropertyModel> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    public void setPropertyList(List<PropertyModel> propertyList) {
        this.propertyList = propertyList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AllPropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.allproperty,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPropertyAdapter.ViewHolder holder, int position) {
         String title=propertyList.get(position).getPropertyLocation();
         String s="For Rent at ";
         String resultTitle=s.concat(title);

        holder.propertyLocation.setText(propertyList.get(position).getPropertyLocation());
        holder.propertyAmount.setText(propertyList.get(position).getPropertyRent());
        holder.propertyName.setText(resultTitle);

        String propertyImage = propertyList.get(position).getPropertyImage();
        String img = "http://room.oxfordcollege.edu.np/storage";
        //get index of c from public of public/documents/sXyBPygG0YNIfRraVGeUcFQyURcwoKVE928sw7kW.jpg
        int index = propertyImage.indexOf("c");
        //remove string before index of /
        String result = propertyImage.substring(index+1);
        //create new image link joining server url
        String newImageUrl = img.concat(result);
        Glide.with(context).asBitmap().load(newImageUrl).into(holder.propertyImage);


    }

    @Override
    public int getItemCount() {
        if(propertyList != null){
            return propertyList.size();
        }
        return 0;
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView propertyImage;
        TextView propertyLocation,propertyAmount,propertyName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyImage=itemView.findViewById(R.id.proImage);
            propertyName=itemView.findViewById(com.example.room.R.id.title);
            propertyLocation=itemView.findViewById(R.id.location);
            propertyAmount=itemView.findViewById(R.id.amount);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
