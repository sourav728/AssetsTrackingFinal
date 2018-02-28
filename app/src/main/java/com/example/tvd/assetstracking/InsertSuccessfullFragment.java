package com.example.tvd.assetstracking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InsertSuccessfullFragment extends Fragment {
    TextView name1,name2,id,brand,date,category,price,quantity,location;
    String getname="",getproductid="",getbrand="",getdate="",getdetails="",getcategory="",getcompany="",getprice="",getqty="",getlocation="";
    public InsertSuccessfullFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_successfull, container, false);

        name1 = (TextView)view.findViewById(R.id.txt_Product_name);
        name2 = (TextView)view.findViewById(R.id.txt_Product_name2);
        id = (TextView)view.findViewById(R.id.txt_Product_id);
        brand = (TextView)view.findViewById(R.id.txt_Product_brand);
        date = (TextView)view.findViewById(R.id.txt_Date);
        category = (TextView)view.findViewById(R.id.txt_category);
        price = (TextView)view.findViewById(R.id.txt_Price);
        quantity = (TextView)view.findViewById(R.id.txt_Quantity);
        location = (TextView)view.findViewById(R.id.txt_Location);

        getname = getArguments().getString("ITEM_NAME");
        getproductid = getArguments().getString("PRODUCT_ID");
        getbrand = getArguments().getString("BRAND");
        getdate = getArguments().getString("DATE");
        getdetails= getArguments().getString("DETAILS");
        getcategory = getArguments().getString("CATEGORY");
        getcompany = getArguments().getString("COMPANY");
        getprice = getArguments().getString("PRICE");
        getqty = getArguments().getString("QTY");
        getlocation = getArguments().getString("LOCATION");
        //Setting values to textviews
        name1.setText(getname);
        name2.setText(getname);
        id.setText(getproductid);
        brand.setText(getbrand);
        date.setText(getdate);
        category.setText(getcategory);
        price.setText(getprice);
        quantity.setText(getqty);
        location.setText(getlocation);
        return view;
    }
}
