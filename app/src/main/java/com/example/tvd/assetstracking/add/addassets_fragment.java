package com.example.tvd.assetstracking.add;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.adapters.RoleAdapter;
import com.example.tvd.assetstracking.database.Database;
import com.example.tvd.assetstracking.other.GetSetValues;

import java.util.ArrayList;

public class addassets_fragment extends Fragment {
    View view;
    Database database;
    ArrayList<GetSetValues> roles_list;
    RoleAdapter roleAdapter;
    GetSetValues getSetValues;
    Spinner role_spiner;
    String main_role="";

    public addassets_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_addassets_fragment, container, false);
        //initialize();

        database = ((MainActivity) getActivity()).getassetDatabase();
        role_spiner = view.findViewById(R.id.spin_category);
        roles_list = new ArrayList<>();
        roleAdapter = new RoleAdapter(roles_list, getActivity());
        role_spiner.setAdapter(roleAdapter);

        for (int i = 0; i < getResources().getStringArray(R.array.category).length; i++) {
            getSetValues = new GetSetValues();
            getSetValues.setRole(getResources().getStringArray(R.array.category)[i]);
            roles_list.add(getSetValues);
            roleAdapter.notifyDataSetChanged();
        }
        //role_spiner.setSelection(0);

            TextView tvrole = (TextView) view.findViewById(R.id.spinner_txt);
            role_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    GetSetValues roledetails = roles_list.get(position);
                    main_role = roledetails.getRole();
                    Toast.makeText(getActivity(), "Role Selected"+main_role, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        return view;
    }
    public void initialize()
    {
        database = ((MainActivity) getActivity()).getassetDatabase();
        role_spiner = view.findViewById(R.id.spin_category);
        roles_list = new ArrayList<>();
        roleAdapter = new RoleAdapter(roles_list, getActivity());
        role_spiner.setAdapter(roleAdapter);
    }
}
