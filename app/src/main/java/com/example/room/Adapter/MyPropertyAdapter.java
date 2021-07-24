package com.example.room.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.room.Constants.Constant;
import com.example.room.DetailActivity;
import com.example.room.Model.PropertyModel;
import com.example.room.R;

import java.util.List;

public class MyPropertyAdapter extends RecyclerView.Adapter<MyPropertyAdapter.ViewHolder> {

    Context context;
    List<PropertyModel> mPropertyList;

    public MyPropertyAdapter(Context context, List<PropertyModel> mPropertyList) {
        this.context = context;
        this.mPropertyList = mPropertyList;
    }
    public void setPropertyList(List<PropertyModel> mPropertyList) {
        this.mPropertyList = mPropertyList;
        notifyDataSetChanged();
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

        String ss=mPropertyList.get(position).getPropertyType();
        ss=ss.replace("\""," ");
        holder.propertyType.setText(ss);

        String s=mPropertyList.get(position).getPropertySize();
        s=s.replace("\""," ");
        holder.propertyArea.setText(s);

        String location=mPropertyList.get(position).getPropertyLocation();
        location=location.replace("\""," ");
        holder.propertyLocation.setText(location);

        String rent=mPropertyList.get(position).getPropertyLocation();
        rent=rent.replace("\""," ");
        holder.propertyFor.setText("For Rent at " +rent);



        String propertyImage = mPropertyList.get(position).getPropertyImage();
//        String img = "http://room.oxfordcollege.edu.np/storage";
      //  String img = "http://192.168.100.47/storage";

        //get index of c from public of public/documents/sXyBPygG0YNIfRraVGeUcFQyURcwoKVE928sw7kW.jpg
        int index = propertyImage.indexOf("c");
        //remove string before index of /
        String result = propertyImage.substring(index+1);
        //create new image link joining server url
        String newImageUrl = Constant.imagerl.concat(result);
        Glide.with(context).asBitmap().load(newImageUrl).into(holder.propertyImage);

    }

    @Override
    public int getItemCount() {
        if(mPropertyList != null){
            return mPropertyList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView propertyImage;
        TextView propertyType,propertyArea,propertyFor,propertyLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyImage=itemView.findViewById(R.id.propertyImage);
            propertyType=itemView.findViewById(R.id.propertyType);
            propertyArea=itemView.findViewById(R.id.propertyArea);
            propertyFor=itemView.findViewById(R.id.propertyTitle);
            propertyLocation=itemView.findViewById(R.id.propertyLocation);

            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(v.getContext(), DetailActivity.class);
                v.getContext().startActivity(intent);
            });
        }
    }
}
