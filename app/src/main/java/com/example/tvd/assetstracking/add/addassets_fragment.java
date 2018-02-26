package com.example.tvd.assetstracking.add;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvd.assetstracking.R;

public class addassets_fragment extends Fragment {


    public addassets_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addassets_fragment, container, false);
    }

}
