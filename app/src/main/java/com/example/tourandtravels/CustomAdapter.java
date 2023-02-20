package com.example.tourandtravels;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CustomAdapter extends FragmentStateAdapter
{
    int no;

    public CustomAdapter(FragmentManager fm, int no, Lifecycle lifecycle)
    {
        super(fm, lifecycle);
        this.no=no;
    }

    @NonNull
    @Override
    public Fragment createFragment(int pos) {

        Fragment fragment=null;

        if (pos==0)
        {
            fragment = new current_bookings();
        }
        else if (pos == 1)
        {
            fragment = new Booking_completed();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return no;
    }
}
