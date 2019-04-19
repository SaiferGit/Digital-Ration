package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DealerLoginActivity extends AppCompatActivity {

    private Button login;
    private EditText userEmail, userPass;
    private TextView needNewAccount;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_login);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.dealer_login_button);
        userEmail = (EditText) findViewById(R.id.dealer_login_mail);
        userPass = (EditText) findViewById(R.id.dealer_login_pass);
        //needNewAccount = (TextView) findViewById(R.id.dealer_login_edit_text);
        loadingBar = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowingUserToLogin();
            }


        });
    }

    private void AllowingUserToLogin() {
        String email = userEmail.getText().toString();
        String pass = userPass.getText().toString();

        if(TextUtils.isEmpty(email) ){
            userEmail.requestFocus();
            userEmail.setError("This Field is required to Sign in your account!");
            return;
        }

        else if(TextUtils.isEmpty(pass) ){
            userPass.requestFocus();
            userPass.setError("This Field is required to Sign in your account!");
            return;
        }

        else{

            loadingBar.setTitle("Logging into Account");
            loadingBar.setMessage("Please wait,while we are Logging into your new Account..");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);


            mAuth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                SendDealerToMainActivity();



                                Toast.makeText(DealerLoginActivity.this, "you are Loggedin Successfully!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }

                            else{
                                String messege = task.getException().toString();
                                Toast.makeText(DealerLoginActivity.this, "Attention: "+messege, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }
                        }
                    });
        }
    }

    private void SendDealerToMainActivity() {
        Intent mainIntent = new Intent(DealerLoginActivity.this, DealerMainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
