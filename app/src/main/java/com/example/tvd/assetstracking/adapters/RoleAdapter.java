package com.example.tvd.assetstracking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.other.GetSetValues;

import java.util.ArrayList;

/**
 * Created by TVD on 2/26/2018.
 */

public class RoleAdapter extends BaseAdapter {
    private ArrayList<GetSetValues> arrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public RoleAdapter(ArrayList<GetSetValues> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_items, null);
        TextView tv_role = (TextView) convertView.findViewById(R.id.spinner_txt);
        tv_role.setText(arrayList.get(position).getRole());
        return convertView;
    }
}
