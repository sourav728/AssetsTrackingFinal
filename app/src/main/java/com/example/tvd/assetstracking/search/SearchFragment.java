package com.example.tvd.assetstracking.search;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.database.Database;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    Cursor c, c1;
    Button search;
    AutoCompleteTextView autosearch;
    Database database;
    ArrayAdapter<String> aa1;
    String product_id = "";
    ArrayList<String> a1;
    String serialid = "", itemname = "", productid = "", brand = "", date = "", details = "", category = "", company = "", price = "", qty = "", location = "", image = "";

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        database = ((MainActivity) getActivity()).getassetDatabase();
        autosearch = (AutoCompleteTextView) view.findViewById(R.id.Autocompete_search);
        search = (Button) view.findViewById(R.id.btn_search);
        a1 = new ArrayList<>();
        autosearch.setInputType(InputType.TYPE_CLASS_NUMBER);
        autosearch.requestFocus();
        c = database.searchbyid();
        aa1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, a1);
        autosearch.setAdapter(aa1);
        a1.clear();
        aa1.notifyDataSetChanged();
        while (c.moveToNext()) {
            product_id = c.getString(c.getColumnIndex("PRODUCT_ID"));
            a1.add(product_id);
        }
        aa1.notifyDataSetChanged();
        autosearch.setThreshold(1);
        autosearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String id = (String) arg0.getItemAtPosition(arg2);
                c1 = database.searAllDetails(id);
                while (c1.moveToNext()) {
                    serialid = c1.getString(c1.getColumnIndex("_id"));
                    itemname = c1.getString(c1.getColumnIndex("ITEM_NAME"));
                    productid = c1.getString(c1.getColumnIndex("PRODUCT_ID"));
                    brand = c1.getString(c1.getColumnIndex("BRAND"));
                    date = c1.getString(c1.getColumnIndex("DATE"));
                    details = c1.getString(c1.getColumnIndex("DETAILS"));
                    category = c1.getString(c1.getColumnIndex("CATEGORY"));
                    company = c1.getString(c1.getColumnIndex("COMPANY"));
                    price = c1.getString(c1.getColumnIndex("PRICE"));
                    qty = c1.getString(c1.getColumnIndex("QTY"));
                    location = c1.getString(c1.getColumnIndex("LOCATION"));
                    image = c1.getString(c1.getColumnIndex("IMAGE"));
                    Log.d("Debugg","Id"+serialid);
                    Log.d("Debugg","ItemName"+itemname);
                    Log.d("Debugg","ProductId"+productid);
                    Log.d("Debugg","Brand"+brand);
                    Log.d("Debugg","Date"+date);
                    Log.d("Debugg","Details"+details);
                    Log.d("Debugg","Category"+category);
                    Log.d("Debugg","Company"+company);
                    Log.d("Debugg","Price"+price);
                    Log.d("Debugg","Qty"+qty);
                    Log.d("Debugg","Location"+location);
                    Log.d("Debugg","Image"+image);
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searchsuccess searchsuccess = new Searchsuccess();
                Bundle args = new Bundle();
                args.putString("id",serialid);
                args.putString("ItemName",itemname);
                args.putString("ProductId",productid);
                args.putString("Brand",brand);
                args.putString("Date",date);
                args.putString("Details",details);
                args.putString("Category",category);
                args.putString("Company",company);
                args.putString("Price",price);
                args.putString("Qty",qty);
                args.putString("Location",location);
                args.putString("Image",image);
                searchsuccess.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,searchsuccess).addToBackStack(null).commit();
            }
        });
        return view;
    }

}
