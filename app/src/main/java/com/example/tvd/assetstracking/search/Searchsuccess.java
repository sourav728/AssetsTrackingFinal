package com.example.tvd.assetstracking.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvd.assetstracking.R;

public class Searchsuccess extends Fragment {
    TextView id, assetname, assetqty, assetprice, viewclick;
    String getid = "", getassetname = "", productid = "", brand = "", date = "", details = "", category = "", company = "", price = "", qty = "", location = "", image = "";

    public Searchsuccess() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchsuccess, container, false);
        id = (TextView) view.findViewById(R.id.txt_asset_id);
        assetname = (TextView) view.findViewById(R.id.txt_asses_name);
        assetqty = (TextView) view.findViewById(R.id.txt_asses_qty);
        assetprice = (TextView) view.findViewById(R.id.txt_asset_price);
        viewclick = (TextView) view.findViewById(R.id.txt_view);

        getid = getArguments().getString("id");
        getassetname = getArguments().getString("ItemName");
        productid = getArguments().getString("ProductId");
        brand = getArguments().getString("Brand");
        date = getArguments().getString("Date");
        details = getArguments().getString("Details");
        category = getArguments().getString("Category");
        company = getArguments().getString("Company");
        price = getArguments().getString("Price");
        qty = getArguments().getString("Qty");
        location = getArguments().getString("Location");
        image = getArguments().getString("Image");

        Log.d("Debugg","Searchid"+getid);
        Log.d("Debugg","SearchAssetname"+getassetname);
        Log.d("Debugg","SearchAssetProductid"+productid);
        Log.d("Debugg","SearchBrand"+brand);
        Log.d("Debugg","SearchDate"+date);
        Log.d("Debugg","SearchDetails"+details);
        Log.d("Debugg","SearchCategory"+category);
        Log.d("Debugg","SearchCompany"+company);
        Log.d("Debugg","SearchPrice"+price);
        Log.d("Debugg","SearchQty"+qty);
        Log.d("Debugg","SearchLocation"+location);
        Log.d("Debugg","SearchImage"+image);

        //setting the values
        id.setText(getid);
        assetname.setText(getassetname);
        assetqty.setText(qty);
        assetprice.setText(price);
        return view;
    }

}
