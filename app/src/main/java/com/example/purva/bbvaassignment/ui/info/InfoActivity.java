package com.example.purva.bbvaassignment.ui.info;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.purva.bbvaassignment.R;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity implements IViewInfo {

    TextView nameTv, addrTv, longitudeTv, latitudeTv, idTv, ratingTv;
    ImageView imageView;
    Button button;
    IPresenterInfo iPresenterInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        nameTv = findViewById(R.id.textViewName);
        addrTv = findViewById(R.id.textViewAddr);
        longitudeTv = findViewById(R.id.textViewLong);
        latitudeTv = findViewById(R.id.textViewLat);
        idTv = findViewById(R.id.textViewid);
        ratingTv = findViewById(R.id.textViewRating);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);


        final String name = getIntent().getExtras().getString("name");
        final String addr = getIntent().getExtras().getString("addr");
        final String longitude = getIntent().getExtras().getString("longitude");
        final String latitude = getIntent().getExtras().getString("latitude");
        final String id = getIntent().getExtras().getString("id");
        final String rating = getIntent().getExtras().getString("rating");
        final String icon = getIntent().getExtras().getString("icon");

        iPresenterInfo = new PresenterInfo((IViewInfo) this, this,latitude,longitude,name,id,addr,rating,icon);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenterInfo.onButtonClicked(latitude, longitude);
            }
        });

    }


    @Override
    public void showData(String name, String addr, String lat, String lng, String id, String rating, String icon) {
        nameTv.setText("Name: "+name);
        addrTv.setText("Address: "+addr);
        longitudeTv.setText("Longitude: "+lng);
        latitudeTv.setText("Latitude: "+lat);
        idTv.setText("ID: "+id);
        ratingTv.setText("Rating: "+rating);
        Picasso.with(this).load(icon).into(imageView);
    }
}
