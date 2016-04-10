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

    private static String notScheduled = "N/A";

    private TextView sundayStartTextView;
    private TextView sundayEndTextView;
    private TextView mondayStartTextView;
    private TextView mondayEndTextView;
    private TextView tuesdayStartTextView;
    private TextView tuesdayEndTextView;
    private TextView wednesdayStartTextView;
    private TextView wednesdayEndTextView;
    private TextView thursdayStartTextView;
    private TextView thursdayEndTextView;
    private TextView fridayStartTextView;
    private TextView fridayEndTextView;
    private TextView saturdayStartTextView;
    private TextView saturdayEndTextView;

    TableLayout tableLayout;
    int daysOfWeek = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tableLayout = (TableLayout) findViewById(R.id.weekSchedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        buildTable(7,daysOfWeek);

        setSupportActionBar(toolbar);

        sundayStartTextView = (TextView) findViewById(R.id.sundayTime);
        mondayStartTextView = (TextView) findViewById(R.id.mondayTime);
        tuesdayStartTextView = (TextView) findViewById(R.id.tuesdayTime);
        wednesdayStartTextView = (TextView) findViewById(R.id.wednesdayTime);
        thursdayStartTextView = (TextView) findViewById(R.id.thursdayTime);
        fridayStartTextView = (TextView) findViewById(R.id.fridayTime);
        saturdayStartTextView = (TextView) findViewById(R.id.saturdayTime);

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
                Intent intent = new Intent(this, Manager.class);
                startActivity(intent);
                return true;
            /*} else {
                t.displayLongToast(getApplicationContext(), "You do not have permission to access" +
                        " this.");
            }*/

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

        private void buildTable(int col, int rows) {
                TextView tv;
                TableRow tr;
                String day = null;
                String tableBox = null;
                int dimen = (int) this.getResources().getDimension(R.dimen.tableSize);

                        for (int i = 1; i <= rows; i++) {
                        tr = new TableRow(this);
                        tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                                        LayoutParams.WRAP_CONTENT));

                                for (int j = 1; j <= col; j++) {
                               tv = new TextView(this);
                                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                                                LayoutParams.WRAP_CONTENT));
                                tv.setPadding(5, 5, 30, 5);

                                        if (j == 1){
                                        switch(i){
                                                case 1: day = "Sun: ";
                                                        break;
                                                case 2: day = "Mon: ";
                                                        break;
                                                case 3: day = "Tues: ";
                                                        break;
                                                case 4: day = "Wed: ";
                                                       break;
                                                case 5: day = "Thurs: ";
                                                        break;
                                                case 6: day = "Fri: ";
                                                        break;
                                                case 7: day = "Sat: ";
                                                        break;
                                            }
                                        tv.setText(day);
                                    }else{
                                        tableBox = "R" + i + ", C" + j;
                                        tv.setText(tableBox);
                                    }
                                tr.addView(tv);

                                    }

                                tableLayout.addView(tr);
                    }
           }

}
