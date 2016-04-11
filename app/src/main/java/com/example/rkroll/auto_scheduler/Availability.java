package com.example.rkroll.auto_scheduler;

// Still need to set up functions for modifying availability.  Must save in secondary table so
// manager can approve.

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Locale;

public class Availability extends AppCompatActivity {
    private TextView sundayTime;
    private TextView mondayTime;
    private TextView tuesdayTime;
    private TextView wednesdayTime;
    private TextView thursdayTime;
    private TextView fridayTime;
    private TextView saturdayTime;

    private EditText sundayStartEditText;
    private EditText sundayEndEditText;
    private EditText mondayStartEditText;
    private EditText mondayEndEditText;
    private EditText tuesdayStartEditText;
    private EditText tuesdayEndEditText;
    private EditText wednesdayStartEditText;
    private EditText wednesdayEndEditText;
    private EditText thursdayStartEditText;
    private EditText thursdayEndEditText;
    private EditText fridayStartEditText;
    private EditText fridayEndEditText;
    private EditText saturdayStartEditText;
    private EditText saturdayEndEditText;

    ParseUser currentUser = new ParseUser();

    private Button saveButton;

    //On create stuff

    /*
    sundayStartEditText = ((EditText) findViewById(R.id.editSundayStartTime));
    sundayEndEditText = ((EditText) findViewById(R.id.editSundayEndTime));
    mondayStartEditText = ((EditText) findViewById(R.id.editMondayStartTime));
    mondayEndEditText = ((EditText) findViewById(R.id.editMondayEndTime));
    tuesdayStartEditText = ((EditText) findViewById(R.id.editTuesdayStartTime));
    tuesdayEndEditText = ((EditText) findViewById(R.id.editTuesdayEndTime));
    wednesdayStartEditText = ((EditText) findViewById(R.id.editWednesdayStartTime));
    wednesdayEndEditText = ((EditText) findViewById(R.id.editWednesdayEndTime));
    thursdayStartEditText = ((EditText) findViewById(R.id.editThursdayStartTime));
    thursdayEndEditText = ((EditText) findViewById(R.id.editThursdayEndTime));
    fridayStartEditText = ((EditText) findViewById(R.id.editFridayStartTime));
    fridayEndEditText = ((EditText) findViewById(R.id.editFridayEndTime));
    saturdayStartEditText = ((EditText) findViewById(R.id.editSaturdayStartTime));
    saturdayEndEditText = ((EditText) findViewById(R.id.editSaturdayEndTime));

    sundayStartTextView = (TextView) findViewById(R.id.sundayTime);
    mondayStartTextView = (TextView) findViewById(R.id.mondayTime);
    tuesdayStartTextView = (TextView) findViewById(R.id.tuesdayTime);
    wednesdayStartTextView = (TextView) findViewById(R.id.wednesdayTime);
    thursdayStartTextView = (TextView) findViewById(R.id.thursdayTime);
    fridayStartTextView = (TextView) findViewById(R.id.fridayTime);
    saturdayStartTextView = (TextView) findViewById(R.id.saturdayTime);

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

    */

/*
    public void objectRetrievalSuccessful(ParseObject object){
        String temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("sundayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("sundayEndTime"));
        if(!checkIfZero(temp)) {
            sundayTime.setText(temp);
        } else {
            sundayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("mondayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("mondayEndTime"));
        if(!checkIfZero(temp)) {
            mondayTime.setText(temp);
        } else {
            mondayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("tuesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("tuesdayEndTime"));
        if(!checkIfZero(temp)){
            tuesdayTime.setText(temp);
        }else {
            tuesdayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("wednesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("wednesdayEndTime"));
        if(!checkIfZero(temp)) {
            wednesdayTime.setText(temp);
        } else {
            wednesdayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("thursdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("thursdayEndTime"));
        if(!checkIfZero(temp)) {
            thursdayTime.setText(temp);
        } else {
            thursdayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("fridayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("fridayEndTime"));
        if(!checkIfZero(temp)) {
            fridayTime.setText(temp);
        } else {
            fridayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("saturdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("saturdayEndTime"));
        if(!checkIfZero(temp)) {
            saturdayTime.setText(temp);
        } else {
            saturdayTime.setText(notScheduled);
        }
    }

    public boolean checkIfZero(String s) {
        return (s.equals("0000 - 0000"));
    }*/
}
