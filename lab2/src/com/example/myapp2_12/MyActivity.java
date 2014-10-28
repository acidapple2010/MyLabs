package com.example.myapp2_12;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyActivity extends Activity {

    String SHARED_PREFS_NAME = "MY_SHARED_PREF";
    ArrayList<String> myList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listview;
    Button addButton;
    EditText edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listview = (ListView) findViewById(R.id.listV);
        addButton = (Button) findViewById(R.id.addButton);

        myList = getArray();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = (EditText) findViewById(R.id.editText);
                myList.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        listview.setAdapter(adapter);
    }

    public boolean saveArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(myList);
        mEdit1.putStringSet("list", set);
        return mEdit1.commit();
    }

    public ArrayList<String> getArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        Set<String> set = sp.getStringSet("list", new HashSet<String>());
        return new ArrayList<String>(set);
    }

    public void onStop() {
        saveArray();
        super.onStop();
    }
}