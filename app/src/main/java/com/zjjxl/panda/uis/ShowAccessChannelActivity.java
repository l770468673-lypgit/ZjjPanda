package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zjjxl.panda.R;
import com.zjjxl.panda.adapters.AccessChanneAdapter;
import com.zjjxl.panda.apps.XLBaseActivity;
import com.zjjxl.panda.beans.QueryCZCity;
import com.zjjxl.panda.https.HttpCallback;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.LUtils;
import com.zjjxl.panda.utils.StatusBarUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAccessChannelActivity extends XLBaseActivity implements AccessChanneAdapter.AccessItenClick, View.OnClickListener {

    private RecyclerView mAccess_recycle;
    private AccessChanneAdapter mAdapter;
    private List<QueryCZCity.ExtraBean> mExtra;
    private String TAG = "ShowAccessChannelActivity";
    private TextView mShowaccess_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setDrawable(this, R.drawable.trip_fragmentsearch_color);
        StatusBarUtil.setStatusBarLightMode(this.getWindow());
        setContentView(R.layout.activity_show_access_channel);
        initView();

    }

    private void getdate() {
        Call<QueryCZCity> queryCZCityCall =
                HttpManager.getInstance().getHttpClient3().queryAccessChannelList();
        queryCZCityCall.enqueue(new Callback<QueryCZCity>() {


            @Override
            public void onResponse(Call<QueryCZCity> call, Response<QueryCZCity> response) {
                if (response.body() != null) {
                    boolean status = response.body().isStatus();
                    if (status) {
                        mExtra = response.body().getExtra();
                        LUtils.d(TAG, "mExtra==" + mExtra.toString());
                        mAdapter.setDate(mExtra);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<QueryCZCity> call, Throwable t) {

            }
        });
    }

    private void initView() {
        mAccess_recycle = findViewById(R.id.access_recycle);
        mShowaccess_back = findViewById(R.id.showaccess_back);
        mAdapter = new AccessChanneAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mAccess_recycle.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mAccess_recycle.setAdapter(mAdapter);

        mShowaccess_back.setOnClickListener(this);
        mAdapter.setAccessItenClick(this);
        getdate();

    }

    @Override
    public void setClickSave(String s) {

//        Bundle bundle = new Bundle();
//        bundle.putString(Contants.ACCRESS_CHANNEL, s);
//        Intent intent = new Intent(this, NFCPandaActivity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);//

        Intent intent = new Intent(this, ShuangYSaveMoneyActivity.class);
        startActivity(intent);
    }

    @Override
    public void setClickSend(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showaccess_back:
                finish();
                break;
        }
    }
}
