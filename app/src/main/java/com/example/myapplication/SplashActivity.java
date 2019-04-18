package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        login = (Button) findViewById(R.id.splash_login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToChoiceActivity();
            }
        });
    }

    private void sendUserToChoiceActivity() {
        Intent choiceIntent = new Intent(SplashActivity.this, ChoiceActivity.class);
        startActivity(choiceIntent);
    }
}
