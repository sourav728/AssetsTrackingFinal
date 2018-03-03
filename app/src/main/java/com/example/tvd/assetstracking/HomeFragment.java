package com.example.tvd.assetstracking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tvd.assetstracking.add.addassets_fragment;
import com.example.tvd.assetstracking.database.Database;
import com.example.tvd.assetstracking.edit.editasset;
import com.example.tvd.assetstracking.other.GetSetValues;
import com.example.tvd.assetstracking.search.SearchFragment;
import com.example.tvd.assetstracking.view.ViewFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageView addassets,edit,records_view,search;
    Database database;
    ArrayList<GetSetValues> arrayList = new ArrayList<>();

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.demo, container, false);

        database = ((MainActivity) getActivity()).getassetDatabase();
        addassets = (ImageView) view.findViewById(R.id.img_add_asset);
        edit = (ImageView) view.findViewById(R.id.img_edit_asset);
        search = (ImageView) view.findViewById(R.id.img_search);
        records_view = (ImageView) view.findViewById(R.id.img_view);
        addassets.setOnClickListener(this);
        edit.setOnClickListener(this);
        records_view.setOnClickListener(this);
        search.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_add_asset:
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new addassets_fragment()).addToBackStack(null).commit();
                break;
            case R.id.img_edit_asset:
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new editasset()).addToBackStack(null).commit();
                break;
            case R.id.img_view:
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new ViewFragment()).addToBackStack(null).commit();
                break;
            case R.id.img_search:
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new SearchFragment()).addToBackStack(null).commit();

        }
    }
}
