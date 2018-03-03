package com.example.tvd.assetstracking.add;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tvd.assetstracking.InsertSuccessfullFragment;
import com.example.tvd.assetstracking.MainActivity;
import com.example.tvd.assetstracking.R;
import com.example.tvd.assetstracking.adapters.RoleAdapter;
import com.example.tvd.assetstracking.database.Database;
import com.example.tvd.assetstracking.other.FunctionCalls;
import com.example.tvd.assetstracking.other.GetSetValues;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class addassets_fragment extends Fragment {
    ProgressDialog pdialog;
    View view;
    EditText itemname, productid, brand, date, details, company, price, qty, location, picture;
    Button save;
    Database database;
    ArrayList<GetSetValues> roles_list;
    RoleAdapter roleAdapter;
    GetSetValues getSetValues;
    Spinner role_spiner;
    String main_role = "", getitem = "", getproduct = "", getbrand = "", getdate = "", getdetails = "", getcategory = "", getcompany = "", getprice = "", getqty = "", getlocation = "";
    Uri fileUri;
    static File mediaFile;
    private static String pic_name = "";
    ImageView image;
    final int CAMERA_CAPTURE = 1;
    private static final int CAPTURE_IMAGE_CLICK_CODE = 10;
    private static File mediaStorageDir;
    private static String timeStamp = "";
    String dd, date1;
    FunctionCalls functioncall;
    private int day, month, year;
    public addassets_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addassets_fragment, container, false);
        functioncall = new FunctionCalls();
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
        save = (Button) view.findViewById(R.id.btn_save);
        picture = (EditText) view.findViewById(R.id.edit_photo);
        picture.setFocusable(false);
        image = (ImageView) view.findViewById(R.id.img_show_image);

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

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
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

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked..", Toast.LENGTH_SHORT).show();

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, CAPTURE_IMAGE_CLICK_CODE);
            }
        });
        return view;
    }

    public void DateDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dd = (year + "-" + (month + 1) + "-"  + dayOfMonth);
                date1 = functioncall.Parse_Date2(dd);
                date.setText(date1);
            }
        };
        DatePickerDialog dpdialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        dpdialog.show();
    }

    private Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Assets_Tracking_Images");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
            pic_name = "IMG_"+ timeStamp + ".jpg";
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(getActivity(), "Successfully captured image..", Toast.LENGTH_SHORT).show();
            previewCapturedImage();
        } else if (requestCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "User Canceled image capture", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
        }
    }

    private void previewCapturedImage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            image.setImageBitmap(bitmap);
        } catch (OutOfMemoryError outOfMemoryError) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            image.setImageBitmap(bitmap);
        }
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
            cv.put("IMAGE",pic_name);
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
            args.putString("IMAGE",pic_name);
            insertsuccessfullfragment.setArguments(args);
            getFragmentManager().beginTransaction().replace(R.id.content_frame, insertsuccessfullfragment, null).commit();
        }
    }
}
