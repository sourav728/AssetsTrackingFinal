package com.example.tvd.assetstracking.other;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class FunctionCalls {
    public void logStatus(String msg) {
        Log.d("debug", msg);
    }

    public void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public File filestorepath(String value, String file) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, File.separator + file);
    }
    private String Appfoldername() {
        return "Assets_Tracking";
    }
    public String filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.toString();
    }
}
