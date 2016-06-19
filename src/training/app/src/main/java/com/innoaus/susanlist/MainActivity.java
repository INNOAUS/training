package com.innoaus.susanlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    ListView listMemo;
    Button btnAdd;
    ArrayList<String> datalist;
    NotesDbAdapter dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new NotesDbAdapter (this);
        dbHelper.open();

        btnAdd = (Button) findViewById(R.id.button_add);
        btnAdd.setOnClickListener(this);

        datalist = new ArrayList<String>();
        for(int i = 0; i<5; i++)
        {
            datalist.add("item " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datalist);
        adapter.setNotifyOnChange(true);
        listMemo = (ListView) findViewById(R.id.list_memo);
        listMemo.setAdapter(adapter);

        refreshList();
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.button_add) {
            Intent intent = new Intent(this, MemoActivity.class);
            startActivityForResult(intent, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
                refreshList();
        }
    }

    private void refreshList()
    {
        datalist.clear();
        datalist.addAll(dbHelper.getAllNotes());
    }
}
