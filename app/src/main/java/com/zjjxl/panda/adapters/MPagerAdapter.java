package com.zjjxl.panda.adapters;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;

    private List<Fragment> fragments = null;

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

