package com.example.tvd.assetstracking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tvd.assetstracking.other.FunctionCalls;

import java.io.File;

public class Database {
    private MyHelper mh;
    private SQLiteDatabase sdb;
    private String databasepath = "";
    private String databasefolder = "database";
    private String database_name = "assets.db";
    private File databasefile = null;
    private FunctionCalls functionCalls = new FunctionCalls();

    public Database(Context context) {
        try {
            databasefile = functionCalls.filestorepath(databasefolder, database_name);
            databasepath = functionCalls.filepath(databasefolder) + File.separator + database_name;
            mh = new MyHelper(context, databasepath, null, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        sdb = mh.getWritableDatabase();
    }

    public void close() {
        sdb.close();
    }

    public class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table ASSET_DETAIL(_id integer primary key, ITEM_NAME TEXT, PRODUCT_ID TEXT, " +
                    "BRAND TEXT, DATE TEXT, DETAILS TEXT, CATEGORY TEXT, COMPANY TEXT, PRICE TEXT, " +
                    "QTY TEXT, LOCATION TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public void insertasset_details(ContentValues cv) {
        sdb.insert("ASSET_DETAIL", null, cv);
    }

    public Cursor getAssetsTecord() {
        Cursor data = null;
        data = sdb.rawQuery("SELECT * FROM ASSET_DETAIL", null);
        return data;
    }

    public void updateasset_details(String item_name, String product_id, String product_brand, String product_date, String product_details, String product_category, String product_company, String product_price, String product_qty, String product_location) {
        /*Cursor data = null;
        data = sdb.rawQuery("UPDATE ASSET_DETAIL SET ITEM_NAME='" + item_name + "' , PRODUCT_ID='" + product_id + "'" +
                "BRAND='" + product_brand + "' , DATE='" + product_date + "', DETAILS='" + product_details + "'" +
                "and CATEGORY ='" + product_category + "' and COMPANY ='" + product_company + "' and PRICE = '" + product_price + "'" +
                "and QTY = '" + product_qty + "' and LOCATION = '" + product_location + "' WHERE _id = '" + id + "'", null);
        data.moveToNext();
        data.close();*/
        ContentValues cv = new ContentValues();
        cv.put("ITEM_NAME",item_name);
        cv.put("PRODUCT_ID",product_id);
        cv.put("BRAND",product_brand);
        cv.put("DATE",product_date);
        cv.put("DETAILS",product_details);
        cv.put("CATEGORY",product_category);
        cv.put("COMPANY",product_company);
        cv.put("PRICE",product_price);
        cv.put("QTY",product_qty);
        cv.put("LOCATION",product_location);
        sdb.update("ASSET_DETAIL",cv,"PRODUCT_ID="+product_id,null);
    }

}
