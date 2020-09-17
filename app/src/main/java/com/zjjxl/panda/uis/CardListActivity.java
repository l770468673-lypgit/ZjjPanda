package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zjjxl.panda.R;
import com.zjjxl.panda.adapters.CardListAdapter;
import com.zjjxl.panda.beans.QueryBindCradbean;
import com.zjjxl.panda.beans.QueryCZCity;
import com.zjjxl.panda.https.HttpManager;
import com.zjjxl.panda.utils.Contants;
import com.zjjxl.panda.utils.ShareUtil;
import com.zjjxl.panda.utils.StatusBarUtil;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardListActivity extends AppCompatActivity {

    private RecyclerView mCardlist_recycle;
    private CardListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarLightMode(getWindow());
        StatusBarUtil.setDrawable(this, R.drawable.fragment_bennefit_boxframe);
        setContentView(R.layout.activity_card_list);

        initView();


    }

    private void initView() {

        mCardlist_recycle = findViewById(R.id.cardlist_recycle);
        mAdapter = new CardListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mCardlist_recycle.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mCardlist_recycle.setAdapter(mAdapter);
       String mLogin_phone = ShareUtil.getString(Contants.LOGIN_USER_PHONE);
        if (mLogin_phone!=null) {
            LoadDate();
        }

    }

    private void LoadDate() {
        Call<QueryBindCradbean> queryCZCityCall = HttpManager.getInstance().getHttpClient3().
                queryUserInfoByUserMid(ShareUtil.getString(Contants.LOGIN_USERMEMBERID));
        queryCZCityCall.enqueue(new Callback<QueryBindCradbean>() {
            @Override
            public void onResponse(Call<QueryBindCradbean> call, Response<QueryBindCradbean> response) {
                if (response.body() != null) {
                    QueryBindCradbean.CardInfoBean cardInfo = response.body().getCardInfo();
                    mAdapter.setdate(cardInfo);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<QueryBindCradbean> call, Throwable t) {

            }
        });
    }
}
