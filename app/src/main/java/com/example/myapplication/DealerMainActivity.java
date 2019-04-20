package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;

public class DealerMainActivity extends AppCompatActivity {

    private Button sellItem, stat;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private CircleImageView navProfileImage;
    private TextView navProfileUserName;

    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private FirebaseStorage firebaseStorage;

    private EditText UserName, FullName, Address, PhoneNo;
    private Button UpdateProfileButton;

    String CurrentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dealer Home");

        UserName = findViewById(R.id.profile_username);
        FullName = findViewById(R.id.profile_fullname);
        Address = findViewById(R.id.profile_address);
        PhoneNo = findViewById(R.id.profile_phone);


        //adding navigation view in drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(DealerMainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        navProfileImage = (CircleImageView) navView.findViewById(R.id.nav_profile_image);
        navProfileUserName = (TextView) navView.findViewById(R.id.nav_user_fullname);

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

        sellItem = (Button) findViewById(R.id.profile_sell_button);
        sellItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DealerMainActivity.this, DealerCardOptions.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
