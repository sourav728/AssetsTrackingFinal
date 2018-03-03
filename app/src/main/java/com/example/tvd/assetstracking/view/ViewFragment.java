package com.example.tvd.assetstracking.view;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.adapters.ViewDetailsAdapter;
import com.example.tvd.assetstracking.database.Database;
import com.example.tvd.assetstracking.other.GetSetValues;

import java.util.ArrayList;

public class ViewFragment extends Fragment {
    RecyclerView recyclerView;
    GetSetValues getSetValues;
    ArrayList<GetSetValues> arrayList;
    Database database;
    private ViewDetailsAdapter viewDetailsAdapter;

    public ViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editasset, container, false);

        database = ((MainActivity) getActivity()).getassetDatabase();

        arrayList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.assets_recycler_view);


        viewDetailsAdapter = new ViewDetailsAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(viewDetailsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Cursor cursor = database.getAssetsTecord();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                GetSetValues getSetValues = new GetSetValues();
                getSetValues.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))));
                getSetValues.setItem_name(cursor.getString(cursor.getColumnIndex("ITEM_NAME")));
                getSetValues.setProduct_id(cursor.getString(cursor.getColumnIndex("PRODUCT_ID")));
                getSetValues.setBrand(cursor.getString(cursor.getColumnIndex("BRAND")));
                getSetValues.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
                getSetValues.setDetails(cursor.getString(cursor.getColumnIndex("DETAILS")));
                getSetValues.setCategory(cursor.getString(cursor.getColumnIndex("CATEGORY")));
                getSetValues.setCompany(cursor.getString(cursor.getColumnIndex("COMPANY")));
                getSetValues.setPrice(cursor.getString(cursor.getColumnIndex("PRICE")));
                getSetValues.setQty(cursor.getString(cursor.getColumnIndex("QTY")));
                getSetValues.setLocation(cursor.getString(cursor.getColumnIndex("LOCATION")));
                getSetValues.setImage(cursor.getString(cursor.getColumnIndex("IMAGE")));

                Log.d("Debugg", "ID" + cursor.getInt(cursor.getColumnIndex("_id")));
                Log.d("Debugg", "ITEM_NAME" + cursor.getString(cursor.getColumnIndex("ITEM_NAME")));
                Log.d("Debugg", "PRODUCT_ID" + cursor.getString(cursor.getColumnIndex("PRODUCT_ID")));
                Log.d("Debugg", "BRAND" + cursor.getString(cursor.getColumnIndex("DATE")));
                Log.d("Debugg", "DATE" + cursor.getString(cursor.getColumnIndex("DATE")));
                Log.d("Debugg", "DETAILS" + cursor.getString(cursor.getColumnIndex("DATE")));
                Log.d("Debugg", "CATEGORY" + cursor.getString(cursor.getColumnIndex("CATEGORY")));
                Log.d("Debugg", "COMPANY" + cursor.getString(cursor.getColumnIndex("COMPANY")));
                Log.d("Debugg", "PRICE" + cursor.getString(cursor.getColumnIndex("PRICE")));
                Log.d("Debugg", "QTY" + cursor.getString(cursor.getColumnIndex("QTY")));
                Log.d("Debugg", "LOCATION" + cursor.getString(cursor.getColumnIndex("LOCATION")));
                Log.d("Debugg", "IMAGE" + cursor.getString(cursor.getColumnIndex("IMAGE")));

                arrayList.add(getSetValues);
                viewDetailsAdapter.notifyDataSetChanged();
            }
        }

        return view;
    }

}
