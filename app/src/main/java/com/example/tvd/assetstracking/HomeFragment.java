package com.example.tvd.assetstracking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tvd.assetstracking.add.addassets_fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageView addassets;
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addassets = view.findViewById(R.id.img_add_asset);
        addassets.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_add_asset:
               getFragmentManager().beginTransaction().replace(R.id.content_frame, new addassets_fragment()).commit();
                break;
        }
    }
}
