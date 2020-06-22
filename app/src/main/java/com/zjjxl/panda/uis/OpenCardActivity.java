package com.zjjxl.panda.uis;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zjjxl.panda.R;
import com.zjjxl.panda.adapters.MPagerAdapter;
import com.zjjxl.panda.apps.XLBaseActivity;
import com.zjjxl.panda.fragments.Fragment_EntityCrad;
import com.zjjxl.panda.fragments.Fragment_VirtualCard;

import java.util.ArrayList;
import java.util.List;

public class OpenCardActivity extends XLBaseActivity implements View.OnClickListener {

    private String[] titles = {"实体卡", "云卡"};
    private List<Fragment> fragments;
    private TabLayout mOpencard_tablay;
    private ViewPager mOpencard_vvp;
    private MPagerAdapter mPagerAdapter;
    private TextView mOpencard_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        android:background="@drawable/trip_fragment_color"/
        setContentView(R.layout.activity_open_card);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        initView();
    }

    private void initView() {
        mOpencard_tablay = findViewById(R.id.opencard_tablay);
        mOpencard_vvp = findViewById(R.id.opencard_vvp);
        mOpencard_back = findViewById(R.id.opencard_back);
        mOpencard_back.setOnClickListener(this);
        initViewPagerFragment();
    }

    private void initViewPagerFragment() {

        mPagerAdapter = new MPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        Fragment_EntityCrad fragment_entityCrad = new Fragment_EntityCrad();
        Fragment_VirtualCard fragment_virtualCard = new Fragment_VirtualCard();
        fragments.add(fragment_entityCrad);
        fragments.add(fragment_virtualCard);
        mPagerAdapter.setTitles(titles);
        mPagerAdapter.setFragments(fragments);
        mOpencard_vvp.setAdapter(mPagerAdapter);
        //将TabLayout和ViewPager绑定
        mOpencard_tablay.setupWithViewPager(mOpencard_vvp);
        mOpencard_tablay.setTabMode(TabLayout.MODE_FIXED);
        //mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opencard_back:
                finish();
                break;
        }
    }
}
