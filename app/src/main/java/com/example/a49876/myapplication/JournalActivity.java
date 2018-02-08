package com.example.a49876.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by 49876 on 2/8/2018.
 */

public class JournalActivity extends AppCompatActivity{

    private static final String TAG = "JournalActivity";

    private Button btnGoMain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_layout);
        btnGoMain = (Button) findViewById(R.id.button);



        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JournalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
