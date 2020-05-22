package com.zjjxl.panda.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjjxl.panda.R;

public class Trip_CardAdapter extends RecyclerView.Adapter<Trip_CardAdapter.ViewHolder> {
    @NonNull
    @Override
    public Trip_CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_reclcleadapter_item, parent, false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trip_CardAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
