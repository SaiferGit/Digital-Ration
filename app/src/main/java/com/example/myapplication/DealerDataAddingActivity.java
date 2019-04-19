package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DealerDataAddingActivity extends AppCompatActivity {

    private Button add, insert;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_data_adding);

        add = (Button) findViewById(R.id.dealer_data_adding_button);
        insert = (Button) findViewById(R.id.dealer_data_insert_button);
        imgView = (ImageView) findViewById(R.id.dealer_data_adding_image);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Adding");

        imgView.setVisibility(View.GONE);
        insert.setVisibility(View.GONE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setVisibility(View.VISIBLE);
                insert.setVisibility(View.VISIBLE);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealerDataAddingActivity.this, DealerMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
