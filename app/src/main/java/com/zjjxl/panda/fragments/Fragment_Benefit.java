package com.zjjxl.panda.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjjxl.panda.R;
import com.zjjxl.panda.adapters.Benefit_CardAdapter;


public class Fragment_Benefit extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private RecyclerView mTrip_recycle_list;
    private RecyclerView mTrip_recycle_list2;
    private Benefit_CardAdapter mAdapte;

    public Fragment_Benefit() {
        // Required empty public constructor
    }

    public static Fragment_Benefit newInstance(String param1, String param2) {
        Fragment_Benefit fragment = new Fragment_Benefit();
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

        View inflate = inflater.inflate(R.layout.fragment__benefit, container, false);
        iniView(inflate);
        return inflate;
    }


    private void iniView(View inflate) {
        mTrip_recycle_list = inflate.findViewById(R.id.benefit_recycle_list);
        mTrip_recycle_list2 = inflate.findViewById(R.id.benefit_recycle_list2);
        mAdapte = new Benefit_CardAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        mTrip_recycle_list.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mTrip_recycle_list.setAdapter(mAdapte);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        mTrip_recycle_list2.setLayoutManager(layoutManager2);
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        mTrip_recycle_list2.setAdapter(mAdapte);
    }
}
