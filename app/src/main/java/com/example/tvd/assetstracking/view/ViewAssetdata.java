package com.example.tvd.assetstracking.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvd.assetstracking.R;

import java.io.File;


public class ViewAssetdata extends Fragment {
    TextView name1, name2, id, brand, date, category, price, quantity, location;
    ImageView showimage;
    String ITEM_NAME = "", PRODUCT_ID = "", BRAND = "", DATE = "", DETAILS = "", CATEGORY = "", COMPANY = "", PRICE = "", QTY = "", LOCATION = "", IMAGE = "";

    public ViewAssetdata() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_assetdata, container, false);
        showimage = (ImageView) view.findViewById(R.id.img_showimage);
        name1 = (TextView) view.findViewById(R.id.txt_Product_name);
        name2 = (TextView) view.findViewById(R.id.txt_Product_name2);
        id = (TextView) view.findViewById(R.id.txt_Product_id);
        brand = (TextView) view.findViewById(R.id.txt_Product_brand);
        date = (TextView) view.findViewById(R.id.txt_Date);
        category = (TextView) view.findViewById(R.id.txt_category);
        price = (TextView) view.findViewById(R.id.txt_Price);
        quantity = (TextView) view.findViewById(R.id.txt_Quantity);
        location = (TextView) view.findViewById(R.id.txt_Location);

        ITEM_NAME = getArguments().getString("ITEM_NAME");
        PRODUCT_ID = getArguments().getString("PRODUCT_ID");
        BRAND = getArguments().getString("BRAND");
        DATE = getArguments().getString("DATE");
        DETAILS = getArguments().getString("DETAILS");
        CATEGORY = getArguments().getString("CATEGORY");
        COMPANY = getArguments().getString("COMPANY");
        PRICE = getArguments().getString("PRICE");
        QTY = getArguments().getString("QTY");
        LOCATION = getArguments().getString("LOCATION");
        IMAGE = getArguments().getString("IMAGE");

        try {
            File mediaStorageDir = new File(android.os.Environment.getExternalStorageDirectory(), "Assets_Tracking_Images" + File.separator + IMAGE);
            IMAGE = mediaStorageDir.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeFile(IMAGE);
        showimage.setImageBitmap(bitmap);

        //setting values
        name1.setText(ITEM_NAME);
        name2.setText(ITEM_NAME);
        id.setText(PRODUCT_ID);
        brand.setText(BRAND);
        date.setText(DATE);
        category.setText(CATEGORY);
        price.setText(PRICE);
        quantity.setText(QTY);
        location.setText(LOCATION);
        return view;
    }

}
