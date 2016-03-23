package com.example.rkroll.auto_scheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class EditStoreHours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store_hours);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    public void saveData(View view){
//        Intent intent = new Intent(this,CompanyInformation.class);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayClose);
//        EditText editText = (EditText) findViewById(R.id.mondayClose);
//        EditText editText = (EditText) findViewById(R.id.mondayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//        EditText editText = (EditText) findViewById(R.id.sundayOpen);
//
//    }

}
