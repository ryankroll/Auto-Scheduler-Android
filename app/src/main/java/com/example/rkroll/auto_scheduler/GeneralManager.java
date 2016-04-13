package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class GeneralManager extends AppCompatActivity {

    private static final String EMPLOYEES = "Employees";
    private String LIST_STATE_KEY;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private List<ParseUser> currentEmployees;  // List of employees for viewing
    private EmployeeAdapter adapter;
    private RecyclerView rv;
    LinearLayoutManager llm = new LinearLayoutManager(this);
    //private UserReaderDbHelper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get reference to the RecyclerView to configure it
        rv=(RecyclerView)findViewById(R.id.employeeRecyclerView);

        // use a LinearLayoutManager to display items in a vertical list
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        //userDb = new UserReaderDbHelper(this);


        //Initialize the user array and adapter.
        initializeData();
        initializeAdapter();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //If general manager button is pressed from menu attempt to access Store information
        if (id == R.id.storeInfo) {
            Intent intent = new Intent(this, CompanyInformation.class);
            startActivity(intent);
            return true;
        }

        // if manager settings button is pressed from menu attempt to access add employee screen
        if (id == R.id.addEmployee) {
            Intent intent = new Intent(this, add_employee.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.approvalDecisionsGM){
            Intent intent = new Intent(this, ViewManagerRequests.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // itemClickListener launches employee info activity for General Manager
    private final View.OnClickListener itemClickListener =
            new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    startViewEmployeeInfo();
                }
            };

    // itemLongClickListener displays a dialog allowing the user to share
    // edit or delete a saved search
    private final View.OnLongClickListener itemLongClickListener =
            new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // get the tag that the user long touched
                    final String tag = ((TextView) view).getText().toString();

                    // create a new AlertDialog
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(GeneralManager.this);

                    // set the AlertDialog's title
                    builder.setTitle(
                            getString(R.string.promoteDeleteTitle, tag));

                    // set list of items to display and create event handler
                    builder.setItems(R.array.changeEmployeeStatus,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0: // Promote Employee
                                            promoteEmployee(tag);
                                            break;
                                        case 1: // delete
                                            deleteEmployee(tag);
                                            break;
                                    }
                                }
                            }
                    );

                    // set the AlertDialog's negative Button
                    builder.setNegativeButton(getString(R.string.cancel), null);

                    builder.create().show(); // display the AlertDialog
                    return true;
                }
            };

    private void promoteEmployee(String emp){
        // create a new AlertDialog
        AlertDialog.Builder builder =
                new AlertDialog.Builder(GeneralManager.this);

        // set the AlertDialog's title
        builder.setTitle(
                getString(R.string.promoteToTitle, emp));

        // set list of items to display and create event handler
        builder.setItems(R.array.promotionStatus,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // Changing employee status to full-time

                                break;
                            case 1: // Changing employee status to a manager

                                break;
                            case 2: // Changing employee status to a general manager

                                break;
                        }
                    }
                }

        );

        // set the AlertDialog's negative Button
        builder.setNegativeButton(getString(R.string.cancel), null);

        builder.create().show(); // display the AlertDialog
    }

    private void deleteEmployee(String emp){
        adapter.notifyDataSetChanged();
    }

    private void initializeData() {
        Log.d("used", "intializeData");
        currentEmployees = new ArrayList<>();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (objects != null) {
                    //currentEmployees.clear();
                    for (int i = 0; i < objects.size(); i++) {
                        currentEmployees.add(objects.get(i));
                        //userDb.insertUser("ryan", "test Id");
                        /*userDb.insertUser(currentEmployees.get(i).getString("name"),
                                currentEmployees.get(i).getObjectId());
                        Log.d("Database Operations", "Row Inserted into DB...");
                    */
                    }
                }
            }
        });

        //Log.d("User", userDb.getUser("Ryan Kroll").toString());
    }

    private void initializeAdapter() {
        // create RecyclerView.Adapter to bind tags to the RecyclerView
        adapter = new EmployeeAdapter(
                currentEmployees, itemLongClickListener);
        rv.setAdapter(adapter);
    }

    private void startViewEmployeeInfo() {
        // create an Intent to launch new activity to view specific employee
        Intent intent = new Intent(this, ViewEmployeeInfo.class);
        startActivity(intent); // Go to Employee info screen
    }

}
