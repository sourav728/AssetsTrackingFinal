package com.example.tvd.assetstracking.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.adapters.RoleAdapter;
import com.example.tvd.assetstracking.database.Database;
import com.example.tvd.assetstracking.other.GetSetValues;

import java.util.ArrayList;

public class editassetdata extends Fragment {
    EditText itemname, productid, brand, date, details, company, price, qty, location;
    String main_role = "", id = "", item_name = "", product_id = "", product_brand = "", product_details = "", product_date = "", product_category = "", product_company = "", product_price = "", product_qty = "", product_location = "";
    Database database;
    ArrayList<GetSetValues> roles_list;
    RoleAdapter roleAdapter;
    GetSetValues getSetValues;
    Spinner role_spiner;

    public editassetdata() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editassetdata, container, false);
        database = ((MainActivity) getActivity()).getassetDatabase();
        role_spiner = (Spinner) view.findViewById(R.id.spin_category);
        roles_list = new ArrayList<>();
        roleAdapter = new RoleAdapter(roles_list, getActivity());
        role_spiner.setAdapter(roleAdapter);
        itemname = (EditText) view.findViewById(R.id.edit_itemname);
        productid = (EditText) view.findViewById(R.id.edit_productid);
        brand = (EditText) view.findViewById(R.id.edit_brand);
        date = (EditText) view.findViewById(R.id.edit_date);
        details = (EditText) view.findViewById(R.id.edit_details);
        company = (EditText) view.findViewById(R.id.edit_company);
        price = (EditText) view.findViewById(R.id.edit_price);
        qty = (EditText) view.findViewById(R.id.edit_qty);
        location = (EditText) view.findViewById(R.id.edit_location);

        for (int i = 0; i < getResources().getStringArray(R.array.category).length; i++) {
            getSetValues = new GetSetValues();
            getSetValues.setRole(getResources().getStringArray(R.array.category)[i]);
            roles_list.add(getSetValues);
            roleAdapter.notifyDataSetChanged();
        }
        //role_spiner.setSelection(0);

        // final TextView tvrole = (TextView) view.findViewById(R.id.spinner_txt);
        role_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                GetSetValues roledetails = roles_list.get(position);
                main_role = roledetails.getRole();
                //tvrole.setText(main_role);
                Toast.makeText(getActivity(), "Role Selected-" + main_role, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //getting values from adapter
        id = getArguments().getString("ITEM_ID");
        item_name = getArguments().getString("ITEM_NAME");
        product_id = getArguments().getString("PRODUCT_ID");
        product_brand = getArguments().getString("BRAND");
        product_date = getArguments().getString("DATE");
        product_details = getArguments().getString("DETAILS");
        product_category = getArguments().getString("CATEGORY");
        product_company = getArguments().getString("COMPANY");
        product_price = getArguments().getString("PRICE");
        product_qty = getArguments().getString("QTY");
        product_location = getArguments().getString("LOCATION");
        //setting values to edittext
        itemname.setText(item_name);
        productid.setText(product_id);
        brand.setText(product_brand);
        date.setText(product_date);
        details.setText(product_details);
        company.setText(product_company);
        price.setText(product_price);
        qty.setText(product_qty);
        location.setText(product_location);

        for (int i = 1; i < getResources().getStringArray(R.array.category).length; i++) {
            if (product_category.equals(getResources().getStringArray(R.array.category)[1])) {
                role_spiner.setSelection(1);
            } else if (product_category.equals(getResources().getStringArray(R.array.category)[2])) {
                role_spiner.setSelection(2);
            } else {
                role_spiner.setSelection(3);
            }

        }
        return view;
    }

}
