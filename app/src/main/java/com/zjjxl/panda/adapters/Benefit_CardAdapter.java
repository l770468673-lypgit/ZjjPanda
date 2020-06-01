package com.zjjxl.panda.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjjxl.panda.R;

public class Benefit_CardAdapter extends RecyclerView.Adapter<Benefit_CardAdapter.ViewHolder> {
    @NonNull
    @Override
    public Benefit_CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.benefit_reclcleadapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Benefit_CardAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button benefit_rely_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            benefit_rely_btn = itemView.findViewById(R.id.benefit_rely_btn);
        }
    }
}
