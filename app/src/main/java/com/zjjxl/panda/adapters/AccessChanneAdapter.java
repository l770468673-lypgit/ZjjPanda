package com.zjjxl.panda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.beans.QueryCZCity;
import com.zjjxl.panda.utils.LUtils;

import java.util.List;

public class AccessChanneAdapter extends RecyclerView.Adapter<AccessChanneAdapter.ViewHolder> {

    private Context mContext;
    private List<QueryCZCity.ExtraBean> Mextra;
    private String TAG = "AccessChanneAdapter";

    public AccessChanneAdapter(Context context) {
        mContext = context;
    }

    public AccessItenClick mAdapterItenClick;

    public void setAccessItenClick(AccessItenClick adapterItenClick) {
        mAdapterItenClick = adapterItenClick;
    }

    public interface AccessItenClick {
        void setClickSave(String s);

        void setClickSend(int position);

    }

    @NonNull
    @Override
    public AccessChanneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accesschanneadapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccessChanneAdapter.ViewHolder holder, int position) {

        QueryCZCity.ExtraBean extraBean = Mextra.get(position);
        final String accessChannel = extraBean.getAccessChannel();
        String cityName = extraBean.getCityName();
        LUtils.d(TAG, "cityName===" + cityName);
        LUtils.d(TAG, "accessChannel===" + accessChannel);
        holder.mAccess_card_title.setText(cityName + "一卡通");
        if (cityName.equals("双阳")) {
            holder.mAccess_bgimg.setBackgroundResource(R.mipmap.chongzhi_img1);
        } else {
            holder.mAccess_bgimg.setBackgroundResource(R.mipmap.chongzhi_img2);
        }
        holder.mAccess_rely_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterItenClick.setClickSave(accessChannel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mextra != null ? Mextra.size() : 0;
    }

    public void setDate(List<QueryCZCity.ExtraBean> extra) {
        this.Mextra = extra;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mAccess_card_title;
        private final Button mAccess_rely_btn;
        private final ImageView mAccess_bgimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAccess_bgimg = itemView.findViewById(R.id.access_bgimg);
            mAccess_card_title = itemView.findViewById(R.id.access_card_title);
            mAccess_rely_btn = itemView.findViewById(R.id.access_rely_btn);
        }
    }
}
