package com.example.tvd.assetstracking.add;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tvd.assetstracking.InsertSuccessfullFragment;
import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.adapters.RoleAdapter;
import com.example.tvd.assetstracking.database.Database;
import com.example.tvd.assetstracking.other.GetSetValues;

import java.util.ArrayList;

public class addassets_fragment extends Fragment {
    ProgressDialog pdialog;
    View view;
    EditText itemname, productid, brand, date, details, company, price, qty, location;
    Button save;
    Database database;
    ArrayList<GetSetValues> roles_list;
    RoleAdapter roleAdapter;
    GetSetValues getSetValues;
    Spinner role_spiner;
    String main_role = "", getitem = "", getproduct = "", getbrand = "", getdate = "", getdetails = "", getcategory = "", getcompany = "", getprice = "", getqty = "", getlocation = "";

    public addassets_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addassets_fragment, container, false);

        database = ((MainActivity) getActivity()).getassetDatabase();
        role_spiner = (Spinner)view.findViewById(R.id.spin_category);
        roles_list = new ArrayList<>();
        roleAdapter = new RoleAdapter(roles_list, getActivity());
        role_spiner.setAdapter(roleAdapter);

        itemname = (EditText)view.findViewById(R.id.edit_itemname);
        productid = (EditText)view.findViewById(R.id.edit_productid);
        brand = (EditText)view.findViewById(R.id.edit_brand);
        date = (EditText)view.findViewById(R.id.edit_date);
        details = (EditText)view.findViewById(R.id.edit_details);
        company = (EditText)view.findViewById(R.id.edit_company);
        price = (EditText)view.findViewById(R.id.edit_price);
        qty = (EditText)view.findViewById(R.id.edit_qty);
        location = (EditText)view.findViewById(R.id.edit_location);
        save = (Button)view.findViewById(R.id.btn_save);

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getitem = itemname.getText().toString();
                getproduct = productid.getText().toString();
                getbrand = brand.getText().toString();
                getdate = date.getText().toString();
                getdetails = details.getText().toString();
                getcategory = main_role;
                getcompany = company.getText().toString();
                getprice = price.getText().toString();
                getqty = qty.getText().toString();
                getlocation = location.getText().toString();

                Insert insert = new Insert();
                insert.execute();
            }
        });
        return view;
    }

    public class Insert extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new ProgressDialog(getActivity());
            pdialog.setTitle("Saving Values");
            pdialog.setMessage("Wait for a second..");
            pdialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            ContentValues cv = new ContentValues();
            cv.put("ITEM_NAME", getitem);
            cv.put("PRODUCT_ID", getproduct);
            cv.put("BRAND", getbrand);
            cv.put("DATE", getdate);
            cv.put("DETAILS", getdetails);
            cv.put("CATEGORY", getcategory);
            cv.put("COMPANY", getcompany);
            cv.put("PRICE", getprice);
            cv.put("QTY", getqty);
            cv.put("LOCATION", getlocation);

            database.insertasset_details(cv);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pdialog.dismiss();
            Toast.makeText(getActivity(), "Inserted Successfully..", Toast.LENGTH_SHORT).show();

            InsertSuccessfullFragment insertsuccessfullfragment = new InsertSuccessfullFragment();
            Bundle args = new Bundle();
            args.putString("ITEM_NAME", getitem);
            args.putString("PRODUCT_ID", getproduct);
            args.putString("BRAND", getbrand);
            args.putString("DATE", getdate);
            args.putString("DETAILS", getdetails);
            args.putString("CATEGORY", getcategory);
            args.putString("COMPANY", getcompany);
            args.putString("PRICE", getprice);
            args.putString("QTY", getqty);
            args.putString("LOCATION", getlocation);
            insertsuccessfullfragment.setArguments(args);
            getFragmentManager().beginTransaction().replace(R.id.content_frame, insertsuccessfullfragment, null).commit();
        }
    }
}
