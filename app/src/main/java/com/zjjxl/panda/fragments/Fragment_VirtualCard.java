package com.zjjxl.panda.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjjxl.panda.R;
import com.zjjxl.panda.adapters.EntityCradAdapter;
import com.zjjxl.panda.uis.eIDActivity;
import com.zjjxl.panda.utils.StatusBarUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_VirtualCard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_VirtualCard extends Fragment implements EntityCradAdapter.EntityCradAdapterClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private RecyclerView mEntity_recycle;
    private EntityCradAdapter mAdapte;

    public Fragment_VirtualCard() {
        // Required empty public constructor
    }


    public static Fragment_VirtualCard newInstance(String param1, String param2) {
        Fragment_VirtualCard fragment = new Fragment_VirtualCard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //    、、fragment__virtual_card
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
        View inflate = inflater.inflate(R.layout.fragment__entity_crad, container, false);
        initView(inflate);
        return inflate;


    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtil.setDrawable(getActivity(), R.drawable.mine_title_color);
        StatusBarUtil.setStatusBarLightMode(getActivity().getWindow());
    }

    private void initView(View inflate) {
        mEntity_recycle = inflate.findViewById(R.id.entity_recycle);
        mAdapte = new EntityCradAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        mEntity_recycle.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mEntity_recycle.setAdapter(mAdapte);
        mAdapte.setDate(1);

        mAdapte.setAccessItenClick(this);
    }

    @Override
    public void setClickOpen(String s) {
        Intent in = new Intent(getActivity(), eIDActivity.class);
        startActivity(in);
    }

    @Override
    public void setClickSend(int position) {

    }
}
