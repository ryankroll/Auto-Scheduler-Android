package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GeneralManager extends AppCompatActivity {

    private static final String EMPLOYEES = "Employees";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private List<String> currentEmployees;  // List of employees for viewing


    private EmployeeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        currentEmployees = new ArrayList<String>();
        currentEmployees.add("ryan");
        currentEmployees.add("brian");
        currentEmployees.add("troy");

        Collections.sort(currentEmployees, String.CASE_INSENSITIVE_ORDER);

        // get reference to the RecyclerView to configure it
        RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recyclerView);

        // use a LinearLayoutManager to display items in a vertical list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // create RecyclerView.Adapter to bind tags to the RecyclerView
        adapter = new EmployeeAdapter(
                currentEmployees, itemClickListener, itemLongClickListener);
        recyclerView.setAdapter(adapter);

//        ListView ls = getListView();

//        String[] employees = {"brian", "ryan", "troy"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, employees);
//        getListView().setAdapter(adapter);


//        ls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//                return false;
//            }
//        });


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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GeneralManager Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rkroll.auto_scheduler/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GeneralManager Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rkroll.auto_scheduler/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    // itemClickListener launches web browser to display search results
    private final View.OnClickListener itemClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // create an Intent to launch new activity to view specific employee
//                    Intent intent = new Intent(this, employeeView.class);
//
//                    startActivity(intent); // show employee information
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

    }
}
