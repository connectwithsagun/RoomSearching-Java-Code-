package com.example.room.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.Model.PopularAreasModel;
import com.example.room.R;
import com.example.room.SelectedAreaActivity;

import java.util.List;

public class PopularAreasAdapter extends RecyclerView.Adapter<PopularAreasAdapter.ViewHolder> {
    List<PopularAreasModel> popularAreasModelList;
    private View.OnClickListener mOnItemClickListener1;
    Context context;

    public PopularAreasAdapter(Context context,List<PopularAreasModel> popularAreasModelList) {
        this.popularAreasModelList = popularAreasModelList;
        this.context=context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPopularAreasModelList(List<PopularAreasModel> propertyList) {
        this.popularAreasModelList = propertyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.popularareas,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(popularAreasModelList.get(position).getImgId());
        holder.textView.setText(popularAreasModelList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if(popularAreasModelList != null){
            return popularAreasModelList.size();
        }
        return 0;
    }
    public void setOnItemClickListener1(View.OnClickListener itemClickListener) {
        mOnItemClickListener1 = itemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.ivLocationImage);
            this.textView=itemView.findViewById(R.id.tvLocationName);
            itemView.setTag(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
                    int position = viewHolder.getAdapterPosition();
                    PopularAreasModel thisItem = popularAreasModelList.get(position);
                   context.startActivity(new Intent(v.getContext(),SelectedAreaActivity.class).putExtra("data",popularAreasModelList.get(position)));
                }
            });
        }
    }
}
