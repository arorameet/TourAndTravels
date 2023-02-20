package com.example.tourandtravels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class fragment_bookings extends Fragment implements TabLayout.OnTabSelectedListener {

    ViewPager2 vp;
    TabLayout tl;
    CustomAdapter ca;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_bookings, container, false);
        tl = v.findViewById(R.id.tab_layout);
        vp = v.findViewById(R.id.view_pager);

        ca=new CustomAdapter(getActivity().getSupportFragmentManager(), tl.getTabCount(), getActivity().getLifecycle());
        vp.setAdapter(ca);

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                tl.selectTab(tl.getTabAt(position));
            }
        });

        tl.addOnTabSelectedListener(this);

        return v;

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        vp.setCurrentItem(tab.getPosition());

    }



    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}