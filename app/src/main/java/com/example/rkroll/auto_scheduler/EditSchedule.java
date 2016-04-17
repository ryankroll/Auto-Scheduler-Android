package com.example.rkroll.auto_scheduler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class EditSchedule extends AppCompatActivity {
    private ListView sunListView, monListView, tuesListView, wedListView, thurListView, friListView, satListView;
    private ArrayAdapter<String> sunAdapter, monAdapter, tuesAdapter, wedAdapter, thurAdapter, friAdapter, satAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sunListView = (ListView) findViewById(R.id.sunListView);
        monListView = (ListView) findViewById(R.id.monListView);
        tuesListView = (ListView) findViewById(R.id.tuesListView);
        wedListView = (ListView) findViewById(R.id.wedListView);
        thurListView = (ListView) findViewById(R.id.thurListView);
        friListView = (ListView) findViewById(R.id.friListView);
        satListView = (ListView) findViewById(R.id.satListView);

        ArrayList<String> sunEmpList = new ArrayList<>();
        ArrayList<String> monEmpList = new ArrayList<>();
        ArrayList<String> tuesEmpList = new ArrayList<>();
        ArrayList<String> wedEmpList = new ArrayList<>();
        ArrayList<String> thurEmpList = new ArrayList<>();
        ArrayList<String> friEmpList = new ArrayList<>();
        ArrayList<String> satEmpList = new ArrayList<>();



        String[] empList = new String[]{"blue", "cyan", "red", "gold", "yellow", "white", "green", "turq"};

        sunEmpList.addAll(Arrays.asList(empList));
        monEmpList = tuesEmpList = wedEmpList = thurEmpList = friEmpList = satEmpList = sunEmpList;



        sunAdapter = new ArrayAdapter<>(this, R.layout.list_row, sunEmpList);
        monAdapter = new ArrayAdapter<>(this, R.layout.list_row, monEmpList);
        tuesAdapter = new ArrayAdapter<>(this, R.layout.list_row, tuesEmpList);
        wedAdapter = new ArrayAdapter<>(this, R.layout.list_row, wedEmpList);
        thurAdapter = new ArrayAdapter<>(this, R.layout.list_row, thurEmpList);
        friAdapter = new ArrayAdapter<>(this, R.layout.list_row, friEmpList);
        satAdapter = new ArrayAdapter<>(this, R.layout.list_row, satEmpList);


        sunListView.setAdapter(sunAdapter);
        monListView.setAdapter(monAdapter);
        tuesListView.setAdapter(tuesAdapter);
        wedListView.setAdapter(wedAdapter);
        thurListView.setAdapter(thurAdapter);
        friListView.setAdapter(friAdapter);
        satListView.setAdapter(satAdapter);


//        reqListView = (ListView) findViewById(R.id.reqList);
//        reqListView.setAdapter(requestMainAdapter);
//        requestMainAdapter.loadObjects();
//        reqListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ParseObject request = requestMainAdapter.getItem(position);
//                ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Time_Off");
//                query.getInBackground(request.getObjectId(),new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                        if (e == null) {
//                            approveTimeOff(object);
//                        } else {
//                            Log.d("Retrieval Failed", e.getMessage());
//                        }
//                    }
//                });
//            }
//        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
