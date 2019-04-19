package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DealerCardOptions extends AppCompatActivity {

    private Button existance;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_card_options);


        existance = (Button) findViewById(R.id.dealer_checkEligiblity_button);
        text = (TextView) findViewById(R.id.dealer_card_options_textView);
        text.setVisibility(View.GONE);


        existance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setVisibility(View.VISIBLE);
                text.setText("Congrats, you are eligible for taking ration");
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealerCardOptions.this, DealerDataAddingActivity.class);
                startActivity(intent);
            }
        });

    }
}
