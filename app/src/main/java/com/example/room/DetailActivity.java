package com.example.room;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.room.Adapter.ImageAdapter;
import com.example.room.Constants.Constant;
import com.example.room.Model.PropertyModel;


public class DetailActivity extends AppCompatActivity {
    Button type,inquiry;
    TextView propLocation,propArea,propBathNo,propBedNo,ownerName,postedDate,propAmount,propType,fType,fullAddress;
    PropertyModel propertyModel;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Detail");
        toolbar.setNavigationIcon(R.drawable.back);

        inquiry=findViewById(R.id.btnInquiry);
        type=findViewById(R.id.pType);
        propBathNo=findViewById(R.id.propBathNo);
        propAmount=findViewById(R.id.pAmount);
        propArea=findViewById(R.id.propArea);
        propLocation=findViewById(R.id.pLocation);
        propBedNo=findViewById(R.id.propBedNo);
        propType=findViewById(R.id.propType);
        ownerName=findViewById(R.id.ownerName);
        postedDate=findViewById(R.id.postedDate);
        fullAddress=findViewById(R.id.fullLocation);
        fType=findViewById(R.id.propFurniture);
        image=findViewById(R.id.vpImage);


//        ViewPager mViewPager = findViewById(R.id.vpImage);
//        ImageAdapter adapterView = new ImageAdapter(this);
//        mViewPager.setAdapter(adapterView);

        inquiry.setOnClickListener(v -> {
               callCustomDialogBox();
        });
        Intent intent=getIntent();
        if(intent.getExtras()!=null) {
            propertyModel = (PropertyModel) intent.getSerializableExtra("data");
            String msg = "Name : " + propertyModel.getId();
            Log.e("data", msg);
            String name=propertyModel.getOwnerName();
            name=name.replace("\"","");
            ownerName.setText(name);
            String property=propertyModel.getPropertyType();
            property=property.replace("\"","");
            propType.setText(property);
            String type=propertyModel.getPropertyType();
            type=type.replace("\"","");
            propType.setText(type);
            String Location=propertyModel.getPropertyLocation();
            Location=Location.replace("\"","");
            propLocation.setText(property+" on Rent at "+Location);
            fullAddress.setText(Location);
            String val=propertyModel.getPropertyRent();
            val=val.replace("\"","");
            propAmount.setText(val+"/month");

            String area=propertyModel.getPropertySize();
            area=area.replace("\"","");
            propArea.setText(area);

            String postDate = propertyModel.getPostedDate();
            postDate=postDate.replace("\"","");
            postedDate.setText("Posted on "+postDate);

            String bedNo = String.valueOf(propertyModel.getBedrooms());
            bedNo=bedNo.replace("\"","");
            propBedNo.setText(bedNo);
            String bathNo = String.valueOf(propertyModel.getBathrooms());
            bathNo=bathNo.replace("\"","");
            propBathNo.setText(bathNo);
            String furniture = String.valueOf(propertyModel.getFurnitureDetail());
            furniture=furniture.replace("\"","");
            fType.setText(furniture);

            String propertyImage = propertyModel.getPropertyImage();
            //String img = "http://room.oxfordcollege.edu.np/storage";
            //get index of c from public of public/documents/sXyBPygG0YNIfRraVGeUcFQyURcwoKVE928sw7kW.jpg
            int index = propertyImage.indexOf("c");
            //remove string before index of /
            String result = propertyImage.substring(index+1);
            //create new image link joining server url
            String newImageUrl = Constant.imagerl.concat(result);
            Glide.with(this).load(newImageUrl).into(image);

          //  image.setImageResource(Integer.parseInt(propertyModel.getPropertyImage()));
        }

    }

    private void callCustomDialogBox() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button ok=dialogView.findViewById(R.id.buttonOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


    }
}