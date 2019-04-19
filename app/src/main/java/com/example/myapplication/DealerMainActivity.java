package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DealerMainActivity extends AppCompatActivity {

    private Button card_punch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_main);

        card_punch = (Button) findViewById(R.id.dealer_main_punchButton);
        card_punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealerMainActivity.this, DealerCardOptions.class);
                startActivity(intent);
            }
        });
    }
}
