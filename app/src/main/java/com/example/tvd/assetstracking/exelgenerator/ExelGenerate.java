package com.example.tvd.assetstracking.exelgenerator;


import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.database.Database;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExelGenerate extends Fragment {
    Database database;

    public ExelGenerate() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        database = ((MainActivity) getActivity()).getassetDatabase();
        File sd = Environment.getExternalStorageDirectory();
        String csvFile = "assets.xls";
        File directory = new File(sd.getAbsolutePath());

        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        try {
            //File path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
            //Exel sheet name. 0 present the first sheet
            WritableSheet sheet = workbook.createSheet("TVD", 0);

            //column and row
            sheet.addCell(new Label(0, 0, "ITEM NAME"));
            sheet.addCell(new Label(1, 0, "PRODUCT_ID"));
            sheet.addCell(new Label(2, 0, "BRAND"));
            sheet.addCell(new Label(3, 0, "DATE"));
            sheet.addCell(new Label(4, 0, "DETAILS"));
            sheet.addCell(new Label(5, 0, "CATEGORY"));
            sheet.addCell(new Label(6, 0, "COMPANY"));
            sheet.addCell(new Label(7, 0, "PRICE"));
            sheet.addCell(new Label(8, 0, "QTY"));
            sheet.addCell(new Label(9, 0, "LOCATION"));

            Cursor cursor = database.getAssetsTecord();
            while (cursor.moveToNext()) {
                String itemname = cursor.getString(cursor.getColumnIndex("ITEM_NAME"));
                String product_id = cursor.getString(cursor.getColumnIndex("PRODUCT_ID"));
                String brand = cursor.getString(cursor.getColumnIndex("BRAND"));
                String date = cursor.getString(cursor.getColumnIndex("DATE"));
                String details = cursor.getString(cursor.getColumnIndex("DETAILS"));
                String category = cursor.getString(cursor.getColumnIndex("CATEGORY"));
                String company = cursor.getString(cursor.getColumnIndex("COMPANY"));
                String price = cursor.getString(cursor.getColumnIndex("PRICE"));
                String qty = cursor.getString(cursor.getColumnIndex("QTY"));
                String location = cursor.getString(cursor.getColumnIndex("LOCATION"));

                int i = cursor.getPosition() + 1;
                sheet.addCell(new Label(0, i, itemname));
                sheet.addCell(new Label(1, i, product_id));
                sheet.addCell(new Label(2, i, brand));
                sheet.addCell(new Label(3, i, date));
                sheet.addCell(new Label(4, i, details));
                sheet.addCell(new Label(5, i, details));
                sheet.addCell(new Label(6, i, category));
                sheet.addCell(new Label(7, i, company));
                sheet.addCell(new Label(8, i, price));
                sheet.addCell(new Label(9, i, qty));
                sheet.addCell(new Label(10, i, location));
            }
            //closing cursor
            cursor.close();
            workbook.write();
            workbook.close();
            Toast.makeText(getActivity(), "Data Exported to Exel Sheet..", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return textView;
    }
}
