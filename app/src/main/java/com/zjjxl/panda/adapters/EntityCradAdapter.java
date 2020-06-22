package com.zjjxl.panda.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjjxl.panda.R;

public class EntityCradAdapter extends RecyclerView.Adapter<EntityCradAdapter.ViewHolder> {
    private int mdate;

    public EntityCradAdapterClick mAdapterItenClick;

    public void setAccessItenClick(EntityCradAdapterClick adapterItenClick) {
        mAdapterItenClick = adapterItenClick;
    }

    public interface EntityCradAdapterClick {
        void setClickOpen(String s);

        void setClickSend(int position);

    }
    @NonNull
    @Override
    public EntityCradAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entity_cardreclcleadapter_item, parent, false);
        return new EntityCradAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityCradAdapter.ViewHolder holder, int position) {
        holder.rntity_rely_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterItenClick.setClickOpen("");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdate != 0 ? mdate : 0;
    }

    public void setDate(int i) {
        this.mdate = i;


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button rntity_rely_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rntity_rely_btn = itemView.findViewById(R.id.rntityrely_btn);
        }
    }
}

