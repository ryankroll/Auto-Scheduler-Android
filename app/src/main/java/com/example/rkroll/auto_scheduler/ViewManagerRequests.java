package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class ViewManagerRequests extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView requestRV;
    private RecyclerView availRV;
    LinearLayoutManager llmRequest = new LinearLayoutManager(this);
    LinearLayoutManager llmAvail = new LinearLayoutManager(this);
    private UserReaderDbHelper userDb;
    private List<ParseUser> currentEmployees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manager_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        // Getting the two different references for the Recycler Views
        requestRV = (RecyclerView) findViewById(R.id.requestRecyclerView);
        availRV = (RecyclerView) findViewById(R.id.availRecyclerView);


        requestRV.setLayoutManager(llmRequest);
//        adapter = new EmployeeAdapter(b, itemClickListener, itemLongClickListener);
        requestRV.setAdapter(adapter);


        availRV.setLayoutManager(llmAvail);
//        adapter = new EmployeeAdapter(b, itemClickListener, itemLongClickListener);
        availRV.setAdapter(adapter);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private final View.OnClickListener itemClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            };

    private final View.OnLongClickListener itemLongClickListener =
            new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // get the tag that the user long touched
                    final String tag = ((TextView) view).getText().toString();

                    return true;
                }
            };
}
