package com.example.myapplication;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DealerStatistics extends ListActivity {
    boolean isListedBefore = false;
    String rice=null, oil= null, sugar=null, salt= null;
    int a,b,c;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_dealer_statistics);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        Bundle bundle = getIntent().getExtras();

        rice = bundle.getString("Rice");
        oil = bundle.getString("Oil");
        sugar = bundle.getString("Sugar");
        salt = bundle.getString("Salt");


    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        if(isListedBefore == false) {
            listItems.add("NID      Rice  Oil  Sugar  Salt");
            listItems.add("123456  " + rice + " Kg   " + oil + " Litre  " + sugar + " Kg   " + salt + " Kg");
            isListedBefore = true;
        }
        else {
            listItems.add("123456  " + rice + " Kg   " + oil + " Litre  " + sugar + " Kg   " + salt + " Kg");
        }
        adapter.notifyDataSetChanged();
    }
}
