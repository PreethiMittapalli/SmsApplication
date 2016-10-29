package com.example.preethimittapalli.smsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ContactView extends AppCompatActivity {

    TextView name,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = (TextView) findViewById(R.id.textView2);
        number = (TextView) findViewById(R.id.textView4);

        String s = getIntent().getStringExtra("Name: ");
        String s1 = getIntent().getStringExtra("Number: ");

        name.setText(s);
        number.setText(s1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
