package com.example.tvd.assetstracking.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.other.GetSetValues;
import com.example.tvd.assetstracking.view.ViewAssetdata;

import java.util.ArrayList;

public class ViewDetailsAdapter extends RecyclerView.Adapter<ViewDetailsAdapter.ViewHolder> {
    private ArrayList<GetSetValues> arrayList = new ArrayList<>();
    private Context context;
    public ViewDetailsAdapter(Context context, ArrayList<GetSetValues>arraylist)
    {
        this.arrayList = arraylist;
        this.context = context;
    }
    @Override
    public ViewDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_list, null);
        return new ViewDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewDetailsAdapter.ViewHolder holder, int position) {
        final GetSetValues getSetValues = arrayList.get(position);
        holder.id.setText(getSetValues.getId());
        holder.name.setText(getSetValues.getItem_name());
        holder.qty.setText(getSetValues.getQty());
        holder.price.setText(getSetValues.getPrice());
        holder.detail.setText(getSetValues.getDetails());
        holder.detail.setText("View");
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAssetdata view = new ViewAssetdata();
                Bundle args = new Bundle();
                args.putString("ITEM_ID", getSetValues.getId());
                args.putString("ITEM_NAME", getSetValues.getItem_name());
                args.putString("PRODUCT_ID", getSetValues.getProduct_id());
                args.putString("BRAND", getSetValues.getBrand());
                args.putString("DATE", getSetValues.getDate());
                args.putString("DETAILS", getSetValues.getDetails());
                args.putString("CATEGORY", getSetValues.getCategory());
                args.putString("COMPANY", getSetValues.getCompany());
                args.putString("PRICE", getSetValues.getPrice());
                args.putString("QTY", getSetValues.getQty());
                args.putString("LOCATION", getSetValues.getLocation());
                args.putString("IMAGE", getSetValues.getImage());
                view.setArguments(args);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, view).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id, name, qty, price, detail, edit;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            id = (TextView) itemView.findViewById(R.id.txt_asset_id);
            name = (TextView) itemView.findViewById(R.id.txt_asses_name);
            qty = (TextView) itemView.findViewById(R.id.txt_asses_qty);
            price = (TextView) itemView.findViewById(R.id.txt_asset_price);
            detail = (TextView) itemView.findViewById(R.id.txt_edit);
            edit = (TextView) itemView.findViewById(R.id.txt_edit);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            GetSetValues getsetvalues = arrayList.get(pos);
            int id = v.getId();
            switch (id) {
                case R.id.txt_edit:

            }
        }
    }
}
