package com.example.room;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.room.Remote.ApiInterface;
import com.example.room.Remote.RetrofitClient;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPropertyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPropertyFragment extends Fragment {
    AutoCompleteTextView textInputEditText;
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> propertyType;
    ArrayAdapter<String> adapter;
    TextInputLayout textInputLayout2;
    TextInputEditText textInputEditText1,name,location,size,amount,bathroom,bedroom;
   // CheckBox wifi,cctv,parking,garden;
    Button addProperty,uploadImage;
    private int REQUEST_CODE=21;
    private int PIC_IMAGE=1;
    Bitmap bitmap;
    ImageView image;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPropertyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPropertyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPropertyFragment newInstance(String param1, String param2) {
        AddPropertyFragment fragment = new AddPropertyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_property, container, false);
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("login",MODE_PRIVATE);
        Boolean b1=sharedPreferences.getBoolean("Login",true);
        if(b1){
             
        }else{
            AddPropertyFragment fragment1 = new AddPropertyFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.content, fragment1);
            fragmentTransaction.commit();
        }
        addProperty=view.findViewById(R.id.addProp);
        name=view.findViewById(R.id.uName);
        location=view.findViewById(R.id.location);
        size=view.findViewById(R.id.size);
        amount=view.findViewById(R.id.amount);
        bathroom=view.findViewById(R.id.bathroom);
        bedroom=view.findViewById(R.id.bedroom);
        uploadImage=view.findViewById(R.id.setImage);
        image=view.findViewById(R.id.img);

        //for Property Type dropDown
        textInputLayout=view.findViewById(R.id.ilPropType);
        autoCompleteTextView=view.findViewById(R.id.tvPropType);
        propertyType=new ArrayList<>();
        propertyType.add("Room");
        propertyType.add("Home");
        propertyType.add("Flat");

        adapter= new ArrayAdapter<>(
                view.getContext().getApplicationContext(),
                R.layout.dropdown_item,
                R.id.tv,
                propertyType);
        autoCompleteTextView.setAdapter(adapter);

        //for furniture type dropDown
       // TextInputLayout textInputLayout1=view.findViewById(R.id.ilFurniture);
       textInputEditText=view.findViewById(R.id.tvFurniture);
        ArrayList<String> list=new ArrayList<>();
        list.add("Well Furnished");
        list.add("Semi Furnished");
        list.add("UnFurnished");

        ArrayAdapter<String> adapter1=new ArrayAdapter<>(
                view.getContext().getApplicationContext(),
                R.layout.dropdown_item,
                R.id.tv,
                list);
        textInputEditText.setAdapter(adapter1);

        //Available date picker
        textInputLayout2=view.findViewById(R.id.ilAvailableDate);
        textInputEditText1=view.findViewById(R.id.etAvailableDate);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();


        textInputLayout2.setEndIconOnClickListener(v -> materialDatePicker.show(getParentFragmentManager(),"Select Date"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> textInputEditText1.setText(materialDatePicker.getHeaderText()));

        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });
        uploadImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(intent,"Select Picture"),PIC_IMAGE);
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PIC_IMAGE && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            Uri uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(),uri);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private byte[] convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return imgByte;
    }

    private void AddData() {
        String image= Arrays.toString(convertToString());
        String Name= name.getText().toString();
        String propType=autoCompleteTextView.getText().toString();
        String propLocation= location.getText().toString();
        String propSize=size.getText().toString();
        String propRent=amount.getText().toString();
        String propDate=textInputEditText1.getText().toString();
        String furnitureType=textInputEditText.getText().toString();
        String bathrooms=bathroom.getText().toString();
        String bedrooms=bedroom.getText().toString();
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("Login", MODE_PRIVATE);

        String token=sharedPreferences.getString("token"," ");
        Log.e("code", token);

        Retrofit retrofit= RetrofitClient.getRetrofitInstance();
        final ApiInterface api=retrofit.create(ApiInterface.class);
        Call<ResponseBody> call=api.propertyAdd("Bearer "+token,Name,propType,propLocation,propSize,propRent,propDate,furnitureType,image,bathrooms,bedrooms);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("rr", ("Dxgvefscdz"));
                Log.e("rr", String.valueOf(response.code()));
                try {
                    Log.v("rr",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Log.v("rr",response.body().toString());
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Failed  "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}