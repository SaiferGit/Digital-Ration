package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChoiceActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button enter, delReg;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private boolean checked=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        //adding toolbar
        //mToolbar =(Toolbar) findViewById(R.id.choice_toolbar);

        enter = (Button) findViewById(R.id.choice_enter_button);
        delReg = (Button) findViewById(R.id.choice_register_button);
        radioGroup = (RadioGroup) findViewById(R.id.choice_radio_group);


        delReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToDealerRegisterActivity();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checked){
                    sendUserToAdminLoginActivity();
                }
                else {
                    sendUserToDealerLoginActivity();
                }
            }
        });
    }

    public void adminClicked(View v){
        checked=true;
    }

    public void dealerClicked(View v){
        checked=false;
    }


    private void sendUserToDealerLoginActivity() {
        Intent intent = new Intent(ChoiceActivity.this,DealerLoginActivity.class);
        startActivity(intent);
    }


    private void sendUserToAdminLoginActivity() {

        Intent intent = new Intent(ChoiceActivity.this,AdminLoginActivity.class);
        startActivity(intent);
    }

    private void sendUserToDealerRegisterActivity() {
        Intent dealerIntent = new Intent(ChoiceActivity.this, DealerRegisterActivity.class);
        startActivity(dealerIntent);
    }
}
