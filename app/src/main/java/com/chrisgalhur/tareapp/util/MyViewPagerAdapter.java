package com.chrisgalhur.tareapp.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    //region VARIABLES
    private final List<Fragment> fragments;
    //endregion VARIABLES

    //region CONSTRUCTOR
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }
    //endregion CONSTRUCTOR

    //region GET_ITEM_COUNT
    @Override
    public int getItemCount() {
        return fragments.size();
    }
    //endregion GET_ITEM_COUNT

    //region CREATE_FRAGMENT
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
    //endregion CREATE_FRAGMENT
}
