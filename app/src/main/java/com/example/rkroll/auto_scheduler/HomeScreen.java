package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import com.example.rkroll.auto_scheduler.toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class HomeScreen extends AppCompatActivity {

    ParseUser currentUser = ParseUser.getCurrentUser();

    ParseObject temp = new ParseObject("Schedule");
    toast t = new toast();

    private static String notScheduled = "N/A - N/A";

    private TextView sundayStartTextView;
    private TextView mondayStartTextView;
    private TextView tuesdayStartTextView;
    private TextView wednesdayStartTextView;
    private TextView thursdayStartTextView;
    private TextView fridayStartTextView;
    private TextView saturdayStartTextView;

    private TextView e1sundayStartTextView;
    private TextView e1mondayStartTextView;
    private TextView e1tuesdayStartTextView;
    private TextView e1wednesdayStartTextView;
    private TextView e1thursdayStartTextView;
    private TextView e1fridayStartTextView;
    private TextView e1saturdayStartTextView;

    private TextView e2sundayStartTextView;
    private TextView e2mondayStartTextView;
    private TextView e2tuesdayStartTextView;
    private TextView e2wednesdayStartTextView;
    private TextView e2thursdayStartTextView;
    private TextView e2fridayStartTextView;
    private TextView e2saturdayStartTextView;

    private TextView e3sundayStartTextView;
    private TextView e3mondayStartTextView;
    private TextView e3tuesdayStartTextView;
    private TextView e3wednesdayStartTextView;
    private TextView e3thursdayStartTextView;
    private TextView e3fridayStartTextView;
    private TextView e3saturdayStartTextView;

    private TextView e4sundayStartTextView;
    private TextView e4mondayStartTextView;
    private TextView e4tuesdayStartTextView;
    private TextView e4wednesdayStartTextView;
    private TextView e4thursdayStartTextView;
    private TextView e4fridayStartTextView;
    private TextView e4saturdayStartTextView;

    private TextView e5sundayStartTextView;
    private TextView e5mondayStartTextView;
    private TextView e5tuesdayStartTextView;
    private TextView e5wednesdayStartTextView;
    private TextView e5thursdayStartTextView;
    private TextView e5fridayStartTextView;
    private TextView e5saturdayStartTextView;

    private TextView e6sundayStartTextView;
    private TextView e6mondayStartTextView;
    private TextView e6tuesdayStartTextView;
    private TextView e6wednesdayStartTextView;
    private TextView e6thursdayStartTextView;
    private TextView e6fridayStartTextView;
    private TextView e6saturdayStartTextView;

    private TextView e7sundayStartTextView;
    private TextView e7mondayStartTextView;
    private TextView e7tuesdayStartTextView;
    private TextView e7wednesdayStartTextView;
    private TextView e7thursdayStartTextView;
    private TextView e7fridayStartTextView;
    private TextView e7saturdayStartTextView;

    private TextView e8sundayStartTextView;
    private TextView e8mondayStartTextView;
    private TextView e8tuesdayStartTextView;
    private TextView e8wednesdayStartTextView;
    private TextView e8thursdayStartTextView;
    private TextView e8fridayStartTextView;
    private TextView e8saturdayStartTextView;

    private TextView e9sundayStartTextView;
    private TextView e9mondayStartTextView;
    private TextView e9tuesdayStartTextView;
    private TextView e9wednesdayStartTextView;
    private TextView e9thursdayStartTextView;
    private TextView e9fridayStartTextView;
    private TextView e9saturdayStartTextView;

    private TextView e10sundayStartTextView;
    private TextView e10mondayStartTextView;
    private TextView e10tuesdayStartTextView;
    private TextView e10wednesdayStartTextView;
    private TextView e10thursdayStartTextView;
    private TextView e10fridayStartTextView;
    private TextView e10saturdayStartTextView;

    private TextView e11sundayStartTextView;
    private TextView e11mondayStartTextView;
    private TextView e11tuesdayStartTextView;
    private TextView e11wednesdayStartTextView;
    private TextView e11thursdayStartTextView;
    private TextView e11fridayStartTextView;
    private TextView e11saturdayStartTextView;

    private TextView e12sundayStartTextView;
    private TextView e12mondayStartTextView;
    private TextView e12tuesdayStartTextView;
    private TextView e12wednesdayStartTextView;
    private TextView e12thursdayStartTextView;
    private TextView e12fridayStartTextView;
    private TextView e12saturdayStartTextView;

    private TextView e13sundayStartTextView;
    private TextView e13mondayStartTextView;
    private TextView e13tuesdayStartTextView;
    private TextView e13wednesdayStartTextView;
    private TextView e13thursdayStartTextView;
    private TextView e13fridayStartTextView;
    private TextView e13saturdayStartTextView;

    private TextView e14sundayStartTextView;
    private TextView e14mondayStartTextView;
    private TextView e14tuesdayStartTextView;
    private TextView e14wednesdayStartTextView;
    private TextView e14thursdayStartTextView;
    private TextView e14fridayStartTextView;
    private TextView e14saturdayStartTextView;

    private TextView e15sundayStartTextView;
    private TextView e15mondayStartTextView;
    private TextView e15tuesdayStartTextView;
    private TextView e15wednesdayStartTextView;
    private TextView e15thursdayStartTextView;
    private TextView e15fridayStartTextView;
    private TextView e15saturdayStartTextView;

    private TextView e1Emp;
    private TextView e2Emp;
    private TextView e3Emp;
    private TextView e4Emp;
    private TextView e5Emp;
    private TextView e6Emp;
    private TextView e7Emp;
    private TextView e8Emp;
    private TextView e9Emp;
    private TextView e10Emp;
    private TextView e11Emp;
    private TextView e12Emp;
    private TextView e13Emp;
    private TextView e14Emp;
    private TextView e15Emp;



    TableLayout tableLayout;
    private int daysOfWeekPlus1 = 8;
    private int numWorkersForWeek = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tableLayout = (TableLayout) findViewById(R.id.weekSchedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //buildTable(daysOfWeekPlus1, numWorkersForWeek);

        setSupportActionBar(toolbar);

        sundayStartTextView = (TextView) findViewById(R.id.sundayTime);
        mondayStartTextView = (TextView) findViewById(R.id.mondayTime);
        tuesdayStartTextView = (TextView) findViewById(R.id.tuesdayTime);
        wednesdayStartTextView = (TextView) findViewById(R.id.wednesdayTime);
        thursdayStartTextView = (TextView) findViewById(R.id.thursdayTime);
        fridayStartTextView = (TextView) findViewById(R.id.fridayTime);
        saturdayStartTextView = (TextView) findViewById(R.id.saturdayTime);

        e1sundayStartTextView = (TextView) findViewById(R.id.e1ST);
        e1mondayStartTextView = (TextView) findViewById(R.id.e1MT);
        e1tuesdayStartTextView = (TextView) findViewById(R.id.e1TT);
        e1wednesdayStartTextView = (TextView) findViewById(R.id.e1WT);
        e1thursdayStartTextView = (TextView) findViewById(R.id.e1HT);
        e1fridayStartTextView = (TextView) findViewById(R.id.e1FT);
        e1saturdayStartTextView = (TextView) findViewById(R.id.e1SAT);

        e2sundayStartTextView = (TextView) findViewById(R.id.e2ST);
        e2mondayStartTextView = (TextView) findViewById(R.id.e2MT);
        e2tuesdayStartTextView = (TextView) findViewById(R.id.e2TT);
        e2wednesdayStartTextView = (TextView) findViewById(R.id.e2WT);
        e2thursdayStartTextView = (TextView) findViewById(R.id.e2HT);
        e2fridayStartTextView = (TextView) findViewById(R.id.e2FT);
        e2saturdayStartTextView = (TextView) findViewById(R.id.e2SAT);

        e3sundayStartTextView = (TextView) findViewById(R.id.e3ST);
        e3mondayStartTextView = (TextView) findViewById(R.id.e3MT);
        e3tuesdayStartTextView = (TextView) findViewById(R.id.e3TT);
        e3wednesdayStartTextView = (TextView) findViewById(R.id.e3WT);
        e3thursdayStartTextView = (TextView) findViewById(R.id.e3HT);
        e3fridayStartTextView = (TextView) findViewById(R.id.e3FT);
        e3saturdayStartTextView = (TextView) findViewById(R.id.e3SAT);

        e4sundayStartTextView = (TextView) findViewById(R.id.e4ST);
        e4mondayStartTextView = (TextView) findViewById(R.id.e4MT);
        e4tuesdayStartTextView = (TextView) findViewById(R.id.e4TT);
        e4wednesdayStartTextView = (TextView) findViewById(R.id.e4WT);
        e4thursdayStartTextView = (TextView) findViewById(R.id.e4HT);
        e4fridayStartTextView = (TextView) findViewById(R.id.e4FT);
        e4saturdayStartTextView = (TextView) findViewById(R.id.e4SAT);

        e5sundayStartTextView = (TextView) findViewById(R.id.e5ST);
        e5mondayStartTextView = (TextView) findViewById(R.id.e5MT);
        e5tuesdayStartTextView = (TextView) findViewById(R.id.e5TT);
        e5wednesdayStartTextView = (TextView) findViewById(R.id.e5WT);
        e5thursdayStartTextView = (TextView) findViewById(R.id.e5HT);
        e5fridayStartTextView = (TextView) findViewById(R.id.e5FT);
        e5saturdayStartTextView = (TextView) findViewById(R.id.e5SAT);

        e6sundayStartTextView = (TextView) findViewById(R.id.e6ST);
        e6mondayStartTextView = (TextView) findViewById(R.id.e6MT);
        e6tuesdayStartTextView = (TextView) findViewById(R.id.e6TT);
        e6wednesdayStartTextView = (TextView) findViewById(R.id.e6WT);
        e6thursdayStartTextView = (TextView) findViewById(R.id.e6HT);
        e6fridayStartTextView = (TextView) findViewById(R.id.e6FT);
        e6saturdayStartTextView = (TextView) findViewById(R.id.e6SAT);

        e7sundayStartTextView = (TextView) findViewById(R.id.e7ST);
        e7mondayStartTextView = (TextView) findViewById(R.id.e7MT);
        e7tuesdayStartTextView = (TextView) findViewById(R.id.e7TT);
        e7wednesdayStartTextView = (TextView) findViewById(R.id.e7WT);
        e7thursdayStartTextView = (TextView) findViewById(R.id.e7HT);
        e7fridayStartTextView = (TextView) findViewById(R.id.e7FT);
        e7saturdayStartTextView = (TextView) findViewById(R.id.e7SAT);

        e8sundayStartTextView = (TextView) findViewById(R.id.e8ST);
        e8mondayStartTextView = (TextView) findViewById(R.id.e8MT);
        e8tuesdayStartTextView = (TextView) findViewById(R.id.e8TT);
        e8wednesdayStartTextView = (TextView) findViewById(R.id.e8WT);
        e8thursdayStartTextView = (TextView) findViewById(R.id.e8HT);
        e8fridayStartTextView = (TextView) findViewById(R.id.e8FT);
        e8saturdayStartTextView = (TextView) findViewById(R.id.e8SAT);

        e9sundayStartTextView = (TextView) findViewById(R.id.e9ST);
        e9mondayStartTextView = (TextView) findViewById(R.id.e9MT);
        e9tuesdayStartTextView = (TextView) findViewById(R.id.e9TT);
        e9wednesdayStartTextView = (TextView) findViewById(R.id.e9WT);
        e9thursdayStartTextView = (TextView) findViewById(R.id.e9HT);
        e9fridayStartTextView = (TextView) findViewById(R.id.e9FT);
        e9saturdayStartTextView = (TextView) findViewById(R.id.e9SAT);

        e10sundayStartTextView = (TextView) findViewById(R.id.e10ST);
        e10mondayStartTextView = (TextView) findViewById(R.id.e10MT);
        e10tuesdayStartTextView = (TextView) findViewById(R.id.e10TT);
        e10wednesdayStartTextView = (TextView) findViewById(R.id.e10WT);
        e10thursdayStartTextView = (TextView) findViewById(R.id.e10HT);
        e10fridayStartTextView = (TextView) findViewById(R.id.e10FT);
        e10saturdayStartTextView = (TextView) findViewById(R.id.e10SAT);

        e11sundayStartTextView = (TextView) findViewById(R.id.e11ST);
        e11mondayStartTextView = (TextView) findViewById(R.id.e11MT);
        e11tuesdayStartTextView = (TextView) findViewById(R.id.e11TT);
        e11wednesdayStartTextView = (TextView) findViewById(R.id.e11WT);
        e11thursdayStartTextView = (TextView) findViewById(R.id.e11HT);
        e11fridayStartTextView = (TextView) findViewById(R.id.e11FT);
        e11saturdayStartTextView = (TextView) findViewById(R.id.e11SAT);

        e12sundayStartTextView = (TextView) findViewById(R.id.e12ST);
        e12mondayStartTextView = (TextView) findViewById(R.id.e12MT);
        e12tuesdayStartTextView = (TextView) findViewById(R.id.e12TT);
        e12wednesdayStartTextView = (TextView) findViewById(R.id.e12WT);
        e12thursdayStartTextView = (TextView) findViewById(R.id.e12HT);
        e12fridayStartTextView = (TextView) findViewById(R.id.e12FT);
        e12saturdayStartTextView = (TextView) findViewById(R.id.e12SAT);

        e13sundayStartTextView = (TextView) findViewById(R.id.e13ST);
        e13mondayStartTextView = (TextView) findViewById(R.id.e13MT);
        e13tuesdayStartTextView = (TextView) findViewById(R.id.e13TT);
        e13wednesdayStartTextView = (TextView) findViewById(R.id.e13WT);
        e13thursdayStartTextView = (TextView) findViewById(R.id.e13HT);
        e13fridayStartTextView = (TextView) findViewById(R.id.e13FT);
        e13saturdayStartTextView = (TextView) findViewById(R.id.e13SAT);

        e14sundayStartTextView = (TextView) findViewById(R.id.e14ST);
        e14mondayStartTextView = (TextView) findViewById(R.id.e14MT);
        e14tuesdayStartTextView = (TextView) findViewById(R.id.e14TT);
        e14wednesdayStartTextView = (TextView) findViewById(R.id.e14WT);
        e14thursdayStartTextView = (TextView) findViewById(R.id.e14HT);
        e14fridayStartTextView = (TextView) findViewById(R.id.e14FT);
        e14saturdayStartTextView = (TextView) findViewById(R.id.e14SAT);

        e15sundayStartTextView = (TextView) findViewById(R.id.e15ST);
        e15mondayStartTextView = (TextView) findViewById(R.id.e15MT);
        e15tuesdayStartTextView = (TextView) findViewById(R.id.e15TT);
        e15wednesdayStartTextView = (TextView) findViewById(R.id.e15WT);
        e15thursdayStartTextView = (TextView) findViewById(R.id.e15HT);
        e15fridayStartTextView = (TextView) findViewById(R.id.e15FT);
        e15saturdayStartTextView = (TextView) findViewById(R.id.e15SAT);

        e1Emp = (TextView) findViewById(R.id.e1Name);
        e2Emp = (TextView) findViewById(R.id.e2Name);
        e3Emp = (TextView) findViewById(R.id.e3Name);
        e4Emp = (TextView) findViewById(R.id.e4Name);
        e5Emp = (TextView) findViewById(R.id.e5Name);
        e6Emp = (TextView) findViewById(R.id.e6Name);
        e7Emp = (TextView) findViewById(R.id.e7Name);
        e8Emp = (TextView) findViewById(R.id.e8Name);
        e9Emp = (TextView) findViewById(R.id.e9Name);
        e10Emp = (TextView) findViewById(R.id.e10Name);
        e11Emp = (TextView) findViewById(R.id.e11Name);
        e12Emp = (TextView) findViewById(R.id.e12Name);
        e13Emp = (TextView) findViewById(R.id.e13Name);
        e14Emp = (TextView) findViewById(R.id.e14Name);
        e15Emp = (TextView) findViewById(R.id.e15Name);

        setTextViews();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Schedule");
        query.whereEqualTo("userId", currentUser.getObjectId());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    objectRetrievalSuccessful(object);
                } else {
                    Log.d("Retrieval Failed", e.getMessage());
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //If general manager button is pressed from menu attempt to access general manager settings
        if (id == R.id.action_settings) {

            //if (currentUser.getBoolean("isGeneralManager")) {
                Intent intent = new Intent(this, GeneralManager.class);
                startActivity(intent);
                return true;
            /*} else {
                t.displayLongToast(getApplicationContext(), "You do not have permission to access" +
                        " this.");
            }*/
        }

        // if manager settings button is pressed from menu attempt to access manager activities
        if (id == R.id.man_settings) {

            //if (currentUser.getBoolean("isManager")) {
                Intent intent = new Intent(this, ManagerOptions.class);
                startActivity(intent);
                return true;
            /*} else {
                t.displayLongToast(getApplicationContext(), "You do not have permission to access" +
                        " this.");
            }*/

        }

        if (id == R.id.user_options){

            Intent intent = new Intent(this, UserOptions.class);
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


    public void objectRetrievalSuccessful(ParseObject object){
        String temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("sundayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("sundayEndTime"));
        if(!checkIfZero(temp)) {
            sundayStartTextView.setText(temp);
        } else {
            sundayStartTextView.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("mondayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("mondayEndTime"));
        if(!checkIfZero(temp)) {
            mondayStartTextView.setText(temp);
        } else {
            mondayStartTextView.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("tuesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("tuesdayEndTime"));
        if(!checkIfZero(temp)){
            tuesdayStartTextView.setText(temp);
        }else {
            tuesdayStartTextView.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("wednesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("wednesdayEndTime"));
        if(!checkIfZero(temp)) {
            wednesdayStartTextView.setText(temp);
        } else {
            wednesdayStartTextView.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("thursdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("thursdayEndTime"));
        if(!checkIfZero(temp)) {
            thursdayStartTextView.setText(temp);
        } else {
            thursdayStartTextView.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("fridayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("fridayEndTime"));
        if(!checkIfZero(temp)) {
            fridayStartTextView.setText(temp);
        } else {
            fridayStartTextView.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("saturdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("saturdayEndTime"));
        if(!checkIfZero(temp)) {
            saturdayStartTextView.setText(temp);
        } else {
            saturdayStartTextView.setText(notScheduled);
        }
    }

    public boolean checkIfZero(String s) {
        return (s.equals("0000 - 0000"));
    }

    /*private void buildTable(int col, int rows) {
        TextView tv = null;
        TableRow tr = null;
        String tableBox = null;

        for (int i = 1; i <= rows; i++) {
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

            for (int j = 1; j <= col; j++) {
            tv = new TextView(this);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            tv.setPadding(5, 5, 40, 5);

                if (i >= 2 && j >= 2){
                    // input times for working during the week
                    // when j = 2 day is sunday / j = 8 day is Saturday etc.
                    tableBox = "R" + i + ", C" + j;
                    tv.setText(tableBox);
                }else if (i == 1 && j >= 2){
                    switch(j){
                        case 2: tableBox = "Sunday";
                                break;
                        case 3: tableBox = "Monday";
                                break;
                        case 4: tableBox = "Tuesday";
                                break;
                        case 5: tableBox = "Wednesday";
                               break;
                        case 6: tableBox = "Thursday";
                                break;
                        case 7: tableBox = "Friday";
                                break;
                        case 8: tableBox = "Saturday";
                                break;
                    }
                    tv.setText(tableBox);
                }else if (j == 1 && i >= 2) {
                    //  input code to get username of employee and set it to tableBox
                    tableBox = "username";
                    tv.setText(tableBox);
                }else{
                    tableBox = "Employee | Week";
                    tv.setText(tableBox);
                }
                tr.addView(tv);

            }  // End of second for loop

            tableLayout.addView(tr);
        } // End of first for loop
    }*/

    public void setTextViews() {
         e1Emp.setText("b");
         e1sundayStartTextView.setText("1500 - 2200");
         e1mondayStartTextView.setText("0800 - 1300");
         e1tuesdayStartTextView.setText("0800 - 1300");
         e1wednesdayStartTextView.setText("N/A - N/A");
         e1thursdayStartTextView.setText("N/A - N/A");
         e1fridayStartTextView.setText("1000 - 1700");
         e1saturdayStartTextView.setText("1400- 2200");

        e2Emp.setText("c");
        e2sundayStartTextView.setText("1500 - 2200");
        e2mondayStartTextView.setText("0800 - 1300");
        e2tuesdayStartTextView.setText("0800 - 1300");
        e2wednesdayStartTextView.setText("N/A - N/A");
        e2thursdayStartTextView.setText("N/A - N/A");
        e2fridayStartTextView.setText("1000 - 1700");
        e2saturdayStartTextView.setText("1400- 2200");

        e3Emp.setText("new");
        e3sundayStartTextView.setText("1500 - 2200");
        e3mondayStartTextView.setText("0800 - 1300");
        e3tuesdayStartTextView.setText("0800 - 1300");
        e3wednesdayStartTextView.setText("N/A - N/A");
        e3thursdayStartTextView.setText("N/A - N/A");
        e3fridayStartTextView.setText("1000 - 1700");
        e3saturdayStartTextView.setText("1400- 2200");

        e4Emp.setText("new");
        e4sundayStartTextView.setText("1500 - 2200");
        e4mondayStartTextView.setText("0800 - 1300");
        e4tuesdayStartTextView.setText("0800 - 1300");
        e4wednesdayStartTextView.setText("N/A - N/A");
        e4thursdayStartTextView.setText("N/A - N/A");
        e4fridayStartTextView.setText("1000 - 1700");
        e4saturdayStartTextView.setText("1400- 2200");

        e5Emp.setText("new");
        e5sundayStartTextView.setText("1500 - 2200");
        e5mondayStartTextView.setText("0800 - 1300");
        e5tuesdayStartTextView.setText("0800 - 1300");
        e5wednesdayStartTextView.setText("N/A - N/A");
        e5thursdayStartTextView.setText("N/A - N/A");
        e5fridayStartTextView.setText("1000 - 1700");
        e5saturdayStartTextView.setText("1400- 2200");

        e6Emp.setText("new");
        e6sundayStartTextView.setText("1500 - 2200");
        e6mondayStartTextView.setText("0800 - 1300");
        e6tuesdayStartTextView.setText("0800 - 1300");
        e6wednesdayStartTextView.setText("N/A - N/A");
        e6thursdayStartTextView.setText("N/A - N/A");
        e6fridayStartTextView.setText("1000 - 1700");
        e6saturdayStartTextView.setText("1400- 2200");

        e7Emp.setText("new");
        e7sundayStartTextView.setText("1500 - 2200");
        e7mondayStartTextView.setText("0800 - 1300");
        e7tuesdayStartTextView.setText("0800 - 1300");
        e7wednesdayStartTextView.setText("N/A - N/A");
        e7thursdayStartTextView.setText("N/A - N/A");
        e7fridayStartTextView.setText("1000 - 1700");
        e7saturdayStartTextView.setText("1400- 2200");

        e8Emp.setText("new");
        e8sundayStartTextView.setText("1500 - 2200");
        e8mondayStartTextView.setText("0800 - 1300");
        e8tuesdayStartTextView.setText("0800 - 1300");
        e8wednesdayStartTextView.setText("N/A - N/A");
        e8thursdayStartTextView.setText("N/A - N/A");
        e8fridayStartTextView.setText("1000 - 1700");
        e8saturdayStartTextView.setText("1400- 2200");

        e9Emp.setText("new");
        e9sundayStartTextView.setText("1500 - 2200");
        e9mondayStartTextView.setText("0800 - 1300");
        e9tuesdayStartTextView.setText("0800 - 1300");
        e9wednesdayStartTextView.setText("N/A - N/A");
        e9thursdayStartTextView.setText("N/A - N/A");
        e9fridayStartTextView.setText("1000 - 1700");
        e9saturdayStartTextView.setText("1400- 2200");

        e10Emp.setText("new");
        e10sundayStartTextView.setText("1500 - 2200");
        e10mondayStartTextView.setText("0800 - 1300");
        e10tuesdayStartTextView.setText("0800 - 1300");
        e10wednesdayStartTextView.setText("N/A - N/A");
        e10thursdayStartTextView.setText("N/A - N/A");
        e10fridayStartTextView.setText("1000 - 1700");
        e10saturdayStartTextView.setText("1400- 2200");

        e11Emp.setText("new");
        e11sundayStartTextView.setText("1500 - 2200");
        e11mondayStartTextView.setText("0800 - 1300");
        e11tuesdayStartTextView.setText("0800 - 1300");
        e11wednesdayStartTextView.setText("N/A - N/A");
        e11thursdayStartTextView.setText("N/A - N/A");
        e11fridayStartTextView.setText("1000 - 1700");
        e11saturdayStartTextView.setText("1400- 2200");

        e12Emp.setText("new");
        e12sundayStartTextView.setText("1500 - 2200");
        e12mondayStartTextView.setText("0800 - 1300");
        e12tuesdayStartTextView.setText("0800 - 1300");
        e12wednesdayStartTextView.setText("N/A - N/A");
        e12thursdayStartTextView.setText("N/A - N/A");
        e12fridayStartTextView.setText("1000 - 1700");
        e12saturdayStartTextView.setText("1400- 2200");

        e13Emp.setText("new");
        e13sundayStartTextView.setText("1500 - 2200");
        e13mondayStartTextView.setText("0800 - 1300");
        e13tuesdayStartTextView.setText("0800 - 1300");
        e13wednesdayStartTextView.setText("N/A - N/A");
        e13thursdayStartTextView.setText("N/A - N/A");
        e13fridayStartTextView.setText("1000 - 1700");
        e13saturdayStartTextView.setText("1400- 2200");

        e14Emp.setText("new");
        e14sundayStartTextView.setText("1500 - 2200");
        e14mondayStartTextView.setText("0800 - 1300");
        e14tuesdayStartTextView.setText("0800 - 1300");
        e14wednesdayStartTextView.setText("N/A - N/A");
        e14thursdayStartTextView.setText("N/A - N/A");
        e14fridayStartTextView.setText("1000 - 1700");
        e14saturdayStartTextView.setText("1400- 2200");

        e15Emp.setText("new");
        e15sundayStartTextView.setText("1500 - 2200");
        e15mondayStartTextView.setText("0800 - 1300");
        e15tuesdayStartTextView.setText("0800 - 1300");
        e15wednesdayStartTextView.setText("N/A - N/A");
        e15thursdayStartTextView.setText("N/A - N/A");
        e15fridayStartTextView.setText("1000 - 1700");
        e15saturdayStartTextView.setText("1400- 2200");

    }
}
