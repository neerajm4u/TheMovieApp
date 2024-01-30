package com.mastercoding.viewpagerapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentStateAdapter {

   private ArrayList<Fragment> fragmentList = new ArrayList<>();


    public MyViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // responsible for creating and returning a
        // fragment for a specific position within ViewPager2

        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }


    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}
