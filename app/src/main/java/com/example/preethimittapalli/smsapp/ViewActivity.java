package com.example.preethimittapalli.smsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ViewActivity extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView = (ListView) findViewById(R.id.listView);
        LoadContacts lca = new LoadContacts();
        lca.execute();
    }

    class LoadContacts extends AsyncTask<Void, Void, ArrayList<String>> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(ViewActivity.this, "Loading Contacts",
                    "Please Wait");
        }
        ArrayList<String> names = new ArrayList<String>();
       final ArrayList<String> contacts = new ArrayList<String>();

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // TODO Auto-generated method stub



            Cursor c = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {

                String name = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String contact = name  + "\n" + number;
                if(!contacts.contains(contact)) {
                    contacts.add(contact);
                    names.add(name);
                }


            }
            c.close();

            Collections.sort(contacts);
            Collections.sort(names);
            return contacts;
        }

        @Override
        protected void onPostExecute(final ArrayList<String> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);

            pd.cancel();


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getApplicationContext(), R.layout.label, names);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String val = listView.getSelectedItem().toString();
                    //String  itemValue    = (String) listView.getItemAtPosition(position);
                    String itemValue = contacts.get(position);
                    Intent i = new Intent(getApplicationContext(),ContactView.class);
                    StringTokenizer st = new StringTokenizer(itemValue,"\n");
                    while(st.hasMoreTokens()) {

                        i.putExtra("Name: " , st.nextToken());
                        i.putExtra("Number: ",st.nextToken());
                    }
                    startActivity(i);
                }
            });

        }
    }
}
