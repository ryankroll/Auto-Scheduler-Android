package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.ListActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class ViewManagerRequests extends AppCompatActivity {


    private ListView reqListView;
    private ListView availListView;
    private ParseQueryAdapter<ParseObject> availMainAdapter;
    private ParseQueryAdapter<ParseObject> requestMainAdapter;
    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manager_requests);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        availMainAdapter = new ParseQueryAdapter<ParseObject>(this, "Request_Availability_Manager");
        availMainAdapter.setTextKey("name");

        requestMainAdapter = new ParseQueryAdapter<ParseObject>(this, "Request_Time_Off_Manager");
        requestMainAdapter.setTextKey("name");

        reqListView = (ListView) findViewById(R.id.reqList);
        reqListView.setAdapter(requestMainAdapter);
        requestMainAdapter.loadObjects();
        reqListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject request = requestMainAdapter.getItem(position);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Time_Off_Manager");
                query.getInBackground(request.getObjectId(),new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            approveTimeOff(object);
                        } else {
                            Log.d("Retrieval Failed", e.getMessage());
                        }
                    }
                });
            }
        });
        availListView = (ListView) findViewById(R.id.availList);
        availListView.setAdapter(availMainAdapter);
        availMainAdapter.loadObjects();
        availListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject avail = availMainAdapter.getItem(position);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Availability_Manager");
                query.getInBackground(avail.getObjectId(), new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            approveAvailabilityChange(object);
                        } else {
                            Log.d("Retrieval Failed", e.getMessage());
                        }
                    }
                });

            }
        });


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
//                    // get the tag that the user long touched
//                    final String tag = ((TextView) view).getText().toString();

                    return true;
                }
            };

    public void approveAvailabilityChange(ParseObject object) {
        Intent intent = new Intent(this, ViewManagerAvailChange.class);
        intent.putExtra("EXTRA_OBJECT_ID", object.getObjectId());
        intent.putExtra("EXTRA_USER_ID", object.getString("userId"));
        intent.putExtra("EXTRA_NAME", object.get("name").toString());
        startActivity(intent);
    }


    public void approveTimeOff(ParseObject object) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Approve Time Off Request");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final String objectId = object.getObjectId();
        final TextView name = new TextView(this);
        final TextView startDate = new TextView(this);
        final TextView endDate = new TextView(this);
        String temp = ("Employee Name: " + object.getString("name"));
        name.setText(temp);
        temp = ("Start Date: " + SimpleDateFormatStringToDate.parseDate(object.getDate("startDate"), DATEFORMATS));
        startDate.setText(temp);
        temp = ("End Date: " + (SimpleDateFormatStringToDate.parseDate(object.getDate("endDate"), DATEFORMATS)));
        endDate.setText(temp);
        layout.addView(name);
        layout.addView(startDate);
        layout.addView(endDate);
        builder.setView(layout);

        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                denyRequest(objectId);
                Log.d("email", "Time off request denied");
            }
        });

        builder.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setApproved(objectId);
                //requestMainAdapter.loadObjects();
                Log.d("email", "Time off request approved");
            }
        });

        AlertDialog ad = builder.create();
        ad.show();



        Log.d("Object Retreival:", "Successful");
    }

    public void setApproved(String ObjectId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Time_Off");
        query.getInBackground(ObjectId,new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    ParseObject temp = new ParseObject("Approved_Request_Time_Off");
                    temp.put("isApproved", true);
                    temp.put("userId", object.getString("userId"));
                    temp.put("name", object.getString("name"));
                    temp.put("startDate", object.getDate("startDate"));
                    temp.put("endDate", object.getDate("endDate"));
                    temp.saveInBackground();
                    object.deleteInBackground();
                    requestMainAdapter.loadObjects();
                } else {
                    Log.d("Retrieval Failed", e.getMessage());
                }
            }
        });

    }

    public void denyRequest(String ObjectId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Time_Off");
        query.getInBackground(ObjectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                }
            }
        });
    }
}
