package com.example.tourandtravels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profile extends Fragment implements View.OnClickListener {
    TextView show;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        show = v.findViewById(R.id.textView8);
        show.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == show){


        }

    }
}