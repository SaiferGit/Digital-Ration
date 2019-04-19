package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class DealerDataAddingActivity extends AppCompatActivity {

    private Button add, insert;
    ImageView imgView;

    private EditText UserName, FullName, Address, PhoneNo;
    private Button UpdateProfileButton;

    private EditText UserName1, FullName1, Address1, PhoneNo1;
    private Button UpdateSellButton;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private FirebaseStorage firebaseStorage;

    String CurrentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_data_adding);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Adding");


        UserName = findViewById(R.id.profile_username);
        FullName = findViewById(R.id.profile_fullname);
        Address = findViewById(R.id.profile_address);
        PhoneNo = findViewById(R.id.profile_phone);
        UpdateSellButton = findViewById(R.id.profile_sell_button);


        UserName1 = findViewById(R.id.profile_username1);
        FullName1 = findViewById(R.id.profile_fullname1);
        Address1 = findViewById(R.id.profile_address1);
        PhoneNo1 = findViewById(R.id.profile_phone1);
        UpdateProfileButton = findViewById(R.id.profile_update_button);

        UserName1.setVisibility(View.GONE);
        FullName1.setVisibility(View.GONE);
        Address1.setVisibility(View.GONE);
        PhoneNo1.setVisibility(View.GONE);
        UpdateProfileButton.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        CurrentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.child(CurrentUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("Rice")) {
                                String retrieveUserName = (String) dataSnapshot.child("Rice").getValue();
                                int temp = Integer.parseInt(retrieveUserName);
                                //int temp1 = Integer.parseInt(UserName1.getText().toString());
                                UserName.setText(retrieveUserName);

                            }
                            if (dataSnapshot.hasChild("Oil")) {
                                String retrieveFullName = (String) dataSnapshot.child("Oil").getValue();
                                FullName.setText(retrieveFullName);

                            }
                            if ((dataSnapshot.hasChild("Suger"))) {
                                String retrieveAddress = (String) dataSnapshot.child("Suger").getValue();
                                Address.setText(retrieveAddress);

                            }
                            if ((dataSnapshot.hasChild("Salt"))) {
                                String retrievePhoneNo = (String) dataSnapshot.child("Salt").getValue();
                                PhoneNo.setText(retrievePhoneNo);

                            }
                        }

                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("Rice")) && (dataSnapshot.hasChild("Oil")) && (dataSnapshot.hasChild("Suger")) && (dataSnapshot.hasChild("Salt")) ) {
                            String retrieveUserName = (String) dataSnapshot.child("Rice").getValue();
                            String retrieveFullName = (String) dataSnapshot.child("Oil").getValue();
                            String retrieveAddress = (String) dataSnapshot.child("Suger").getValue();
                            String retrievePhoneNo = (String) dataSnapshot.child("Salt").getValue();

                            UserName.setText(retrieveUserName);
                            FullName.setText(retrieveFullName);
                            Address.setText(retrieveAddress);
                            PhoneNo.setText(retrievePhoneNo);
                        } else if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("Rice")) && (dataSnapshot.hasChild("Oil")) && (dataSnapshot.hasChild("Suger")) && (dataSnapshot.hasChild("Salt"))) {
                            String retrieveUserName = (String) dataSnapshot.child("Rice").getValue();
                            String retrieveFullName = (String) dataSnapshot.child("Oil").getValue();
                            String retrieveAddress = (String) dataSnapshot.child("Suger").getValue();
                            String retrievePhoneNo = (String) dataSnapshot.child("Salt").getValue();

                            UserName.setText(retrieveUserName);
                            FullName.setText(retrieveFullName);
                            Address.setText(retrieveAddress);
                            PhoneNo.setText(retrievePhoneNo);
                        } else {
                            //Toast.makeText(HomeFragment.this,"abc",Toast.LENGTH_LONG).show();
                            //Toast.makeText(HomeFragment.this, "Please set & update your profile information...", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        UpdateSellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserName1.setVisibility(View.VISIBLE);
                FullName1.setVisibility(View.VISIBLE);
                Address1.setVisibility(View.VISIBLE);
                PhoneNo1.setVisibility(View.VISIBLE);
                UpdateProfileButton.setVisibility(View.VISIBLE);


            }
        });

        UpdateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {

        String uname = UserName.getText().toString();
        String fname = FullName.getText().toString();
        String phone = PhoneNo.getText().toString();
        String address = Address.getText().toString();

        String uname1 = UserName1.getText().toString();
        String fname1 = FullName1.getText().toString();
        String phone1 = PhoneNo1.getText().toString();
        String address1 = Address1.getText().toString();

        int rice_firebase = Integer.parseInt(uname);
        int rice_sold = Integer.parseInt(uname1);

        int oil_firebase = Integer.parseInt(uname);
        int oil_sold = Integer.parseInt(uname1);

        int suger_firebase = Integer.parseInt(uname);
        int suger_sold = Integer.parseInt(uname1);

        int salt_firebase = Integer.parseInt(uname);
        int salt_sold = Integer.parseInt(uname1);

        //inserting info into Fdb
        HashMap userMap = new HashMap();
        userMap.put("Rice",Integer.toString(rice_firebase - rice_sold));
        userMap.put("Oil",Integer.toString(oil_firebase - oil_sold));
        userMap.put("Suger",Integer.toString(suger_firebase - suger_sold));
        userMap.put("Salt",Integer.toString(salt_firebase - salt_sold));



        RootRef.child(CurrentUserID).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(DealerDataAddingActivity.this,"Your List has been successfully updated!", Toast.LENGTH_LONG).show();
                }
                else{
                    String messege = task.getException().getMessage();
                    Toast.makeText(DealerDataAddingActivity.this,"Attention: "+messege, Toast.LENGTH_LONG).show();


                }
            }
        });

        //showing into profile
        RootRef.child(CurrentUserID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("Rice")) {
                                String retrieveUserName = (String) dataSnapshot.child("Rice").getValue();
                                UserName.setText(retrieveUserName);

                            }
                            if (dataSnapshot.hasChild("Oil")) {
                                String retrieveFullName = (String) dataSnapshot.child("Oil").getValue();
                                FullName.setText(retrieveFullName);

                            }
                            if ((dataSnapshot.hasChild("Suger"))) {
                                String retrieveAddress = (String) dataSnapshot.child("Suger").getValue();
                                Address.setText(retrieveAddress);

                            }
                            if ((dataSnapshot.hasChild("Salt"))) {
                                String retrievePhoneNo = (String) dataSnapshot.child("Salt").getValue();
                                PhoneNo.setText(retrievePhoneNo);

                            }
                        }

                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("Rice")) && (dataSnapshot.hasChild("Oil")) && (dataSnapshot.hasChild("Suger")) && (dataSnapshot.hasChild("Salt")) ) {
                            String retrieveUserName = (String) dataSnapshot.child("Rice").getValue();
                            String retrieveFullName = (String) dataSnapshot.child("Oil").getValue();
                            String retrieveAddress = (String) dataSnapshot.child("Suger").getValue();
                            String retrievePhoneNo = (String) dataSnapshot.child("Salt").getValue();

                            UserName.setText(retrieveUserName);
                            FullName.setText(retrieveFullName);
                            Address.setText(retrieveAddress);
                            PhoneNo.setText(retrievePhoneNo);
                        } else if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("Rice")) && (dataSnapshot.hasChild("Oil")) && (dataSnapshot.hasChild("Suger")) && (dataSnapshot.hasChild("Salt"))) {
                            String retrieveUserName = (String) dataSnapshot.child("Rice").getValue();
                            String retrieveFullName = (String) dataSnapshot.child("Oil").getValue();
                            String retrieveAddress = (String) dataSnapshot.child("Suger").getValue();
                            String retrievePhoneNo = (String) dataSnapshot.child("Salt").getValue();

                            UserName.setText(retrieveUserName);
                            FullName.setText(retrieveFullName);
                            Address.setText(retrieveAddress);
                            PhoneNo.setText(retrievePhoneNo);
                        } else {
                            //Toast.makeText(HomeFragment.this,"abc",Toast.LENGTH_LONG).show();
                            //Toast.makeText(HomeFragment.this, "Please set & update your profile information...", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        UserName1.setText(null);
        FullName1.setText(null);
        PhoneNo1.setText(null);
        Address1.setText(null);

    }
}
