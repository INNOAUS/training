package com.innoaus.susanlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MemoActivity extends AppCompatActivity {
    EditText editTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        editTitle = (EditText) findViewById(R.id.edit_title);
        editTitle.requestFocus();

        Button btnDone = (Button) findViewById(R.id.button_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title", editTitle.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
