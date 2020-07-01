package com.zjjxl.panda.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.QueryBindCradbean;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {

    private QueryBindCradbean.CardInfoBean mBean;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardlist_item, parent, false);
        return new CardListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (mBean != null) {
            holder.mAcardlist_num.setText(mBean.getCardNum());
            holder.mAcardlist_fullName.setText(mBean.getFullName());
        }



    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setdate(QueryBindCradbean.CardInfoBean cardInfo) {
        this.mBean = cardInfo;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAcardlist_num;

        private TextView mAcardlist_fullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAcardlist_num = itemView.findViewById(R.id.cardlist_num);
            mAcardlist_fullName = itemView.findViewById(R.id.cardlist_fullName);

        }
    }
}
