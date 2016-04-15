package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.app.Activity;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ManagerOptions extends AppCompatActivity {

    private ListView reqListView;
    private ListView availListView;
    private ParseQueryAdapter<ParseObject> availMainAdapter;
    private ParseQueryAdapter<ParseObject> requestMainAdapter;

    private Date date;

    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};

    SimpleDateFormatStringToDate mDate = new SimpleDateFormatStringToDate();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        availMainAdapter = new ParseQueryAdapter<ParseObject>(this, "Request_Availability");
        availMainAdapter.setTextKey("name");

        requestMainAdapter = new ParseQueryAdapter<ParseObject>(this, "Request_Time_Off");
        requestMainAdapter.setTextKey("name");

        reqListView = (ListView) findViewById(R.id.reqList);
        reqListView.setAdapter(requestMainAdapter);
        requestMainAdapter.loadObjects();
        reqListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject request = requestMainAdapter.getItem(position);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Time_Off");
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
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Request_Availability");
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //If general manager button is pressed from menu attempt to access Store information
        if (id == R.id.history) {
            // create a new AlertDialog
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);

            // set the AlertDialog's title
            builder.setTitle(
                    getString(R.string.enterHistoryDate));


            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);

            final EditText startDate = new EditText(this);
            final EditText endDate = new EditText(this);

            startDate.setHint("Enter the first date");
            startDate.setInputType(InputType.TYPE_CLASS_DATETIME);
            endDate.setHint("Enter the last date");
            endDate.setInputType(InputType.TYPE_CLASS_DATETIME);

            ll.addView(startDate);
            ll.addView(endDate);

            builder.setView(ll);

            builder.setNegativeButton(android.R.string.cancel, null);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ManagerOptions.this, ScheduleHistory.class);
                    startActivity(intent);
                }
            });
            AlertDialog ad = builder.create();
            ad.show();

            return true;
        }

        // if manager settings button is pressed from menu attempt to access add employee screen
        if (id == R.id.createEditSchedule) {
            Intent intent = new Intent(this, CreateEditSchedule.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void approveAvailabilityChange(ParseObject object) {
        Intent intent = new Intent(this, AvailRequest.class);
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
        temp = ("Start Date: " + mDate.parseDate(object.getDate("startDate"), DATEFORMATS));
        startDate.setText(temp);
        temp = ("End Date: " + (mDate.parseDate(object.getDate("endDate"), DATEFORMATS)));
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

