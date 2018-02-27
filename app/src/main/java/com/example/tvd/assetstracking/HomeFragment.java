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

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageView addassets, edit;
    Database database;
    ArrayList<GetSetValues> arrayList = new ArrayList<>();

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        database = ((MainActivity) getActivity()).getassetDatabase();
        addassets = (ImageView) view.findViewById(R.id.img_add_asset);
        edit = (ImageView) view.findViewById(R.id.img_edit_asset);
        addassets.setOnClickListener(this);
        edit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_add_asset:
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new addassets_fragment()).commit();
                break;
            case R.id.img_edit_asset:
               // fetchallrecord();
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new editasset()).commit();
                break;

        }
    }

   /* public void fetchallrecord() {
        Cursor cursor = database.getAssetsTecord();
        if (cursor.getCount()>0) {
            while (cursor.moveToNext())
            {
                GetSetValues getSetValues = new GetSetValues();
                getSetValues.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))));
                getSetValues.setProduct_id(cursor.getString(cursor.getColumnIndex("ITEM_NAME")));
                getSetValues.setItem_name(cursor.getString(cursor.getColumnIndex("PRODUCT_ID")));
                getSetValues.setBrand(cursor.getString(cursor.getColumnIndex("BRAND")));
                getSetValues.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
                getSetValues.setDetails(cursor.getString(cursor.getColumnIndex("DETAILS")));
                getSetValues.setCategory(cursor.getString(cursor.getColumnIndex("CATEGORY")));
                getSetValues.setCompany(cursor.getString(cursor.getColumnIndex("COMPANY")));
                getSetValues.setPrice(cursor.getString(cursor.getColumnIndex("PRICE")));
                getSetValues.setQty(cursor.getString(cursor.getColumnIndex("QTY")));
                getSetValues.setLocation(cursor.getString(cursor.getColumnIndex("LOCATION")));

                Log.d("Debugg","ID"+cursor.getInt(cursor.getColumnIndex("_id")));
                Log.d("Debugg","ITEM_NAME"+cursor.getString(cursor.getColumnIndex("ITEM_NAME")));
                Log.d("Debugg","PRODUCT_ID"+cursor.getString(cursor.getColumnIndex("PRODUCT_ID")));
                Log.d("Debugg","BRAND"+cursor.getString(cursor.getColumnIndex("DATE")));
                Log.d("Debugg","DATE"+cursor.getString(cursor.getColumnIndex("DATE")));
                Log.d("Debugg","DETAILS"+cursor.getString(cursor.getColumnIndex("DATE")));
                Log.d("Debugg","CATEGORY"+cursor.getString(cursor.getColumnIndex("CATEGORY")));
                Log.d("Debugg","COMPANY"+cursor.getString(cursor.getColumnIndex("COMPANY")));
                Log.d("Debugg","PRICE"+cursor.getString(cursor.getColumnIndex("PRICE")));
                Log.d("Debugg","QTY"+cursor.getString(cursor.getColumnIndex("QTY")));
                Log.d("Debugg","LOCATION"+cursor.getString(cursor.getColumnIndex("LOCATION")));
                arrayList.add(getSetValues);
            }
        }
    }*/
}
