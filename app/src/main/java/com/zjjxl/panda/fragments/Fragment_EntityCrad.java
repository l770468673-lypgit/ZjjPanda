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
import com.zjjxl.panda.adapters.Benefit_CardAdapter;
import com.zjjxl.panda.adapters.EntityCradAdapter;
import com.zjjxl.panda.uis.eIDActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_EntityCrad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_EntityCrad extends Fragment implements EntityCradAdapter.EntityCradAdapterClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mEntity_recycle;
    private EntityCradAdapter mAdapte;

    public Fragment_EntityCrad() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_EntityCrad newInstance(String param1, String param2) {
        Fragment_EntityCrad fragment = new Fragment_EntityCrad();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment__entity_crad, container, false);
        initView(inflate);
        return inflate;


    }

    private void initView(View inflate) {
        mEntity_recycle = inflate.findViewById(R.id.entity_recycle);
        mAdapte = new EntityCradAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        mEntity_recycle.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mEntity_recycle.setAdapter(mAdapte);
        mAdapte.setDate(4);

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
