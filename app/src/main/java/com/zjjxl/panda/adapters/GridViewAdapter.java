package com.zjjxl.panda.adapters;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


import com.zjjxl.panda.R;
import com.zjjxl.panda.uis.NFCPandaActivity;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter {
    private static String TAG = "GridViewAdapter";


    private NFCPandaActivity mActivity;
    private int location=100;

    private List<Map<String, String>> mData_list;

    public GridViewAdapter(NFCPandaActivity nfcActivity) {
        this.mActivity = nfcActivity;
    }

    public void setSeclection(int position) {
        location = position;
    }

    public void setData_list(List<Map<String, String>> data_list) {
        if (data_list.size() > 0) {
            this.mData_list = data_list;
            this.notifyDataSetChanged();

        }
    }

    @Override
    public int getCount() {

        return mData_list != null ? mData_list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.nfcact_gridviewadapteritem_monry, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.realmoney.setText(mData_list.get(position).get("truemoney") + " 元");

        if (position == location) {
            holder.gradviewid.setBackground(mActivity.getDrawable(R.drawable.nfcact_grid_itemclock_boxframe));
            holder.realmoney.setTextColor(Color.parseColor("#29A2F5"));

        } else {

            holder.realmoney.setTextColor(Color.parseColor("#333333"));
            holder.gradviewid.setBackground(mActivity.getDrawable(R.drawable.nfcact_grid_item_boxframe2));
        }


        return convertView;
    }

    class ViewHolder {
        TextView realmoney;
        TextView salemoney;
        LinearLayout gradviewid;

        public ViewHolder(View convertView) {
            gradviewid = convertView.findViewById(R.id.gradviewid);
            realmoney = convertView.findViewById(R.id.resalmoney);
//            salemoney = (TextView) convertView.findViewById(R.id.salemoney);

        }
    }


}
