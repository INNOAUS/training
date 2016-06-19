package com.innoaus.susanlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemoActivity extends AppCompatActivity {
    EditText editTitle;
    EditText editBody;
    NotesDbAdapter dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        dbHelper = new NotesDbAdapter(this);
        dbHelper.open();

        editTitle = (EditText) findViewById(R.id.edit_title);
        editTitle.requestFocus();
        editBody = (EditText) findViewById(R.id.edit_body);

        Button btnDone = (Button) findViewById(R.id.button_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String body = editBody.getText().toString();
                String date = now();
                dbHelper.createNote(title, body, date);

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private String now()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss a");
        return sdf.format(c.getTime());
    }
}
