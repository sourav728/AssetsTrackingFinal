package com.example.tvd.assetstracking.database;

import android.content.Context;
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
           databasefile = functionCalls.filestorepath(databasefolder,database_name);
           databasepath = functionCalls.filepath(databasefolder) + File.separator + database_name;
           mh = new MyHelper(context, databasepath, null, 1);
       }
       catch (Exception e)
       {
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

    /*public String AppFolderName() {
        return "AssetsTracking";
    }

    public String filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), AppFolderName() + File.separator + value);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir.toString();
    }*/
}
