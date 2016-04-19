package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GeneralManager extends AppCompatActivity {

    private static final String[] EMPLOYEES =  new String[]{"M M", "Z Z", "V V", "Troy T", "Steve S", "G G", "E E", "R R", "Ryan R", "Brian B", "c", "b", "t1", "test", "Ryan Kroll"};
    private ArrayAdapter<String> arrayAdapter;

    private String LIST_STATE_KEY;

    private ListView empListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        empListView = (ListView) findViewById(R.id.empList);
        ArrayList<String> empList = new ArrayList<>();

        empList.addAll(Arrays.asList(EMPLOYEES));

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_row, empList);

        empListView.setAdapter(arrayAdapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        if (id == R.id.logout){
            ParseUser.logOut();
            Intent intent = new Intent(this, MainActivity.class);
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
//        adapter.notifyDataSetChanged();
    }



    private void startViewEmployeeInfo() {
        // create an Intent to launch new activity to view specific employee
        Intent intent = new Intent(this, ViewEmployeeInfo.class);
        startActivity(intent); // Go to Employee info screen
    }

}
