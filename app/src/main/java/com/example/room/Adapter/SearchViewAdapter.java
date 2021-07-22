//package com.example.room.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.room.Model.PropertyModel;
//import com.example.room.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.mViewHolder> implements Filterable {
//    private ArrayList<PropertyModel> mArrayList;
//    private ArrayList<PropertyModel> mFilteredList;
//
//    public SearchViewAdapter(ArrayList<PropertyModel> arrayList) {
//        mArrayList = arrayList;
//        mFilteredList = arrayList;
//    }
//
//    @NonNull
//    @Override
//    public SearchViewAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
//        return new mViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SearchViewAdapter.mViewHolder holder, int position) {
//        holder.title.setText(mFilteredList.get(position).getPropertyLocation());
//    }
//
//    @Override
//    public int getItemCount() {
//        if(mArrayList != null){
//            return mArrayList.size();
//        }
//        return 0;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String charString = constraint.toString();
//
//                if (charString.isEmpty()) {
//
//                    mFilteredList = mArrayList;
//                } else {
//
//                    ArrayList<PropertyModel> filteredList = new ArrayList<>();
//
//                    for (PropertyModel androidVersion : mArrayList) {
//
//                        if (androidVersion.getPropertyLocation().toLowerCase().contains(charString) || androidVersion.getOwnerName().toLowerCase().contains(charString) || androidVersion.getPropertyType().toLowerCase().contains(charString)) {
//
//                            filteredList.add(androidVersion);
//                        }
//                    }
//
//                    mFilteredList = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mFilteredList;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                mFilteredList = (ArrayList<PropertyModel>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//
//    }
//
//    public class mViewHolder extends RecyclerView.ViewHolder {
//        TextView title;
//        public mViewHolder(@NonNull View itemView) {
//            super(itemView);
//            title=itemView.findViewById(R.id.searchTitle);
//        }
//    }
//}
