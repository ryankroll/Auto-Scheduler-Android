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
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class UserOptions extends AppCompatActivity {

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

    private TextView employeeName;
    private TextView empEmail;
    private TextView empPhoneNumber;
    private TextView empBirthday;
    private TextView empHireDate;
    private TextView empPos;

    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};

    SimpleDateFormatStringToDate mDate = new SimpleDateFormatStringToDate();

    ParseUser currentUser = ParseUser.getCurrentUser();
    ParseObject current_Avail = new ParseObject("Availability");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sundayStartTextView = (TextView) findViewById(R.id.sundayStartTime);
        sundayEndTextView = (TextView) findViewById(R.id.sundayEndTime);
        mondayStartTextView = (TextView) findViewById(R.id.mondayStartTime);
        mondayEndTextView = (TextView) findViewById(R.id.mondayEndTime);
        tuesdayStartTextView = (TextView) findViewById(R.id.tuesdayStartTime);
        tuesdayEndTextView = (TextView) findViewById(R.id.tuesdayEndTime);
        wednesdayStartTextView = (TextView) findViewById(R.id.wednesdayStartTime);
        wednesdayEndTextView = (TextView) findViewById(R.id.wednesdayEndTime);
        thursdayStartTextView = (TextView) findViewById(R.id.thursdayStartTime);
        thursdayEndTextView = (TextView) findViewById(R.id.thursdayEndTime);
        fridayStartTextView = (TextView) findViewById(R.id.fridayStartTime);
        fridayEndTextView = (TextView) findViewById(R.id.fridayEndTime);
        saturdayStartTextView = (TextView) findViewById(R.id.saturdayStartTime);
        saturdayEndTextView = (TextView) findViewById(R.id.saturdayEndTime);

        employeeName = (TextView) findViewById(R.id.employeeName);
        empEmail = (TextView) findViewById(R.id.emp);
        empPhoneNumber = (TextView) findViewById(R.id.employeeNumber);
        empBirthday = (TextView) findViewById(R.id.empBirthday);
        empHireDate = (TextView) findViewById(R.id.empHireDate);
        empPos = (TextView) findViewById(R.id.empPosStatus);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Availability");
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

        displayEmployeeInfo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_option, menu);
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
            Intent intent = new Intent(this, MakeTimeOffRequest.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void editInfo(View view){
        Intent intent = new Intent(this, EditPersonalInfo.class);
        intent.putExtra("EXTRA_EMP_EMAIL", empEmail.getText().toString());
        intent.putExtra("EXTRA_EMP_PHONENUMBER", empPhoneNumber.getText().toString());
        startActivity(intent);
    }

    public void changeAvailHrs(View view){
        Intent intent = new Intent(this, EditPersonalAvail.class);
        startActivity(intent);
    }

    public void objectRetrievalSuccessful(ParseObject object) {
        displayStoreHours(object);
        current_Avail.equals(object);
        Log.d("Object Retreival:", "Successful");
    }


    public void displayStoreHours(ParseObject object) {
        sundayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("sundayStartTime")));
        sundayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("sundayEndTime")));
        mondayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("mondayStartTime")));
        mondayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("mondayEndTime")));
        tuesdayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt
                ("tuesdayStartTime")));
        tuesdayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("tuesdayEndTime")));
        wednesdayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt
                ("wednesdayStartTime")));
        wednesdayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("wednesdayEndTime")));
        thursdayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt
                ("thursdayStartTime")));
        thursdayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("thursdayEndTime")));
        fridayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("fridayStartTime")));
        fridayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("fridayEndTime")));
        saturdayStartTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt
                ("saturdayStartTime")));
        saturdayEndTextView.setText(String.format(Locale.getDefault(), "%04d", object.getInt("saturdayEndTime")));
    }

    public void displayEmployeeInfo() {
        employeeName.setText(currentUser.getString("name"));
        empEmail.setText(currentUser.getString("email"));
        String temp = String.valueOf(currentUser.getNumber("phoneNumber"));
        //String.format("(%s) %s-%s", temp.substring(0,3), temp.substring(3,6), temp.substring(6,10));
        empPhoneNumber.setText(temp);
        String dateString = mDate.parseDate(currentUser.getDate("birthDate"), DATEFORMATS);
        empBirthday.setText(dateString);
        dateString = mDate.parseDate(currentUser.getDate("hireDate"), DATEFORMATS);
        empHireDate.setText(dateString);
        if(currentUser.getBoolean("isFullTime")) {
            empPos.setText("Full-Time");
        } else {
            empPos.setText("Part-Time");
        }




    }





}
