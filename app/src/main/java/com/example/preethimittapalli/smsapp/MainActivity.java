package com.example.preethimittapalli.smsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button send,viewContacts,view;
    EditText num,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        send = (Button) findViewById(R.id.button);
        num = (EditText) findViewById(R.id.editText);
        msg = (EditText) findViewById(R.id.textView2);
        viewContacts = (Button) findViewById(R.id.button2);
        view = (Button) findViewById(R.id.button3);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });

        viewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewActivity.class);
                startActivity(i);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendSms() {
        String phn = num.getText().toString();
        String message = msg.getText().toString();
        try {
            SmsManager sms = SmsManager.getDefault();
            if(phn.length() == 10 && (phn.charAt(0) == '9' || phn.charAt(0) == '8'|| phn.charAt(0) == '7') && !phn.equals("null") &&
                    !message.equals("null")) {
                sms.sendTextMessage(phn,null,message,null,null);
                Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_SHORT).show();
            } else if(phn.length() < 10 || phn.length() > 10){
                Toast.makeText(MainActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
            } else if(phn.equals("null") || message.equals("null")) {
                Toast.makeText(MainActivity.this, "The fields should not be empty", Toast.LENGTH_SHORT).show();
            }

        } catch(Exception e) {
            Toast.makeText(MainActivity.this, "Sending Sms Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
