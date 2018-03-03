package com.example.tvd.assetstracking.edit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.example.tvd.assetstracking.HomeFragment;
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

public class editassetdata extends Fragment {
    private static final int CAPTURE_IMAGE_CLICK_CODE = 10;
    EditText itemname, productid, brand, date, details, company, price, qty, location;
    String main_role = "";
    String id = "";
    String item_name = "";
    String product_id = "";
    String product_brand = "";
    String product_details = "";
    String product_date = "";
    String product_category = "";
    String product_company = "";
    String product_price = "";
    String product_qty = "";
    String product_location = "";
    View product_photo;
    String product_image = "";
    Database database;
    ArrayList<GetSetValues> roles_list;
    RoleAdapter roleAdapter;
    GetSetValues getSetValues;
    Spinner role_spiner;
    Button save;
    ImageView show;
    Uri fileUri;
    String dd, date1;
    FunctionCalls functionCalls;
    private int day, month, year;
    private static File mediaStorageDir;
    private static String timeStamp = "";
    static File mediaFile;
    private static String pic_name = "";

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
        product_photo = view.findViewById(R.id.edit_photo);
        product_photo.setFocusable(false);

        save = (Button) view.findViewById(R.id.btn_save);
        show = (ImageView) view.findViewById(R.id.img_show_image);
        functionCalls = new FunctionCalls();
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
        product_image = getArguments().getString("IMAGE");
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

        try {
            File mediaStorageDir = new File(android.os.Environment.getExternalStorageDirectory(), "Assets_Tracking_Images" + File.separator + product_image);
            product_image = mediaStorageDir.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeFile(product_image);
        show.setImageBitmap(bitmap);

        for (int i = 1; i < getResources().getStringArray(R.array.category).length; i++) {
            if (product_category.equals(getResources().getStringArray(R.array.category)[1])) {
                role_spiner.setSelection(1);
            } else if (product_category.equals(getResources().getStringArray(R.array.category)[2])) {
                role_spiner.setSelection(2);
            } else {
                role_spiner.setSelection(3);
            }

        }

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });

        product_photo.setOnClickListener(new View.OnClickListener() {
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_name = itemname.getText().toString();
                String edit_id = productid.getText().toString();
                String edit_brand = brand.getText().toString();
                String edit_date = date.getText().toString();
                String edit_details = details.getText().toString();
                String spi_category = main_role;
                String edit_company = company.getText().toString();
                String edit_price = price.getText().toString();
                String edit_qty = qty.getText().toString();
                String edit_location = location.getText().toString();
                database.updateasset_details(edit_name, edit_id, edit_brand, edit_date, edit_details, spi_category, edit_company, edit_price, edit_qty, edit_location, pic_name);
                Toast.makeText(getActivity(), "Records Updated Succeddfully..", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
            }
        });


        return view;
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
            pic_name = "IMG_" + timeStamp + ".jpg";
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(getActivity(), "Image Captured Successfull..", Toast.LENGTH_SHORT).show();
            previewcapturedimage();
        } else if (requestCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "User Canceled image capture..", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Sorry Failed to captured image!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void previewcapturedimage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            show.setImageBitmap(bitmap);
        } catch (OutOfMemoryError outOfMemoryError) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            show.setImageBitmap(bitmap);
        }
    }

    public void DateDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dd = (year + "-" + (month + 1) + "-" + dayOfMonth);
                date1 = functionCalls.Parse_Date2(dd);
                date.setText(date1);
            }
        };
        DatePickerDialog dpdialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        dpdialog.show();
    }

}
