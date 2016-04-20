package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;
import java.util.Locale;

public class EditPersonalAvail extends AppCompatActivity {

    ParseUser currentUser = ParseUser.getCurrentUser();

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
    private EditText effectiveDate;


    ParseObject avail_Request = new ParseObject("Request_Availability");
    ParseObject current_Avail = new ParseObject("Availability");
    ParseObject manager_Avail_Request = new ParseObject("Request_Availability_Manager");

    private Button saveButton;

    private Date date;

    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};

    SimpleDateFormatStringToDate mDate = new SimpleDateFormatStringToDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_avail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        effectiveDate = ((EditText) findViewById(R.id.effectiveDate));

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

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

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
    }

    private final View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (ParseUser.getCurrentUser().getBoolean("isManager")) {
                managerAvailRequest();
            } else {
                availRequest();
            }

        }
    };

        public void managerAvailRequest() {
            int temp;

            if ((isEmpty(sundayStartEditText))) {
                temp = Integer.parseInt(sundayStartEditText.getText().toString());
                manager_Avail_Request.put("sundayStartTime", temp);
            }
            if ((isEmpty(sundayEndEditText))) {
                temp = Integer.parseInt(sundayEndEditText.getText().toString());
                manager_Avail_Request.put("sundayEndTime", temp);
            }
            if ((isEmpty(mondayStartEditText))) {
                temp = Integer.parseInt(mondayStartEditText.getText().toString());
                manager_Avail_Request.put("mondayStartTime", temp);
            }

            if ((isEmpty(mondayEndEditText))) {
                temp = Integer.parseInt(mondayEndEditText.getText().toString());
                manager_Avail_Request.put("mondayEndTime", temp);
            }
            if ((isEmpty(tuesdayStartEditText))) {
                temp = Integer.parseInt(tuesdayStartEditText.getText().toString());
                manager_Avail_Request.put("tuesdayStartTime", temp);
            }
            if ((isEmpty(tuesdayEndEditText))) {
                temp = Integer.parseInt(tuesdayEndEditText.getText().toString());
                manager_Avail_Request.put("tuesdayEndTime", temp);
            }
            if ((isEmpty(wednesdayStartEditText))) {
                temp = Integer.parseInt(wednesdayStartEditText.getText().toString());
                manager_Avail_Request.put("wednesdayStartTime", temp);
            }
            if ((isEmpty(wednesdayEndEditText))) {
                temp = Integer.parseInt(wednesdayEndEditText.getText().toString());
                manager_Avail_Request.put("wednesdayEndTime", temp);
            }
            if ((isEmpty(thursdayStartEditText))) {
                temp = Integer.parseInt(thursdayStartEditText.getText().toString());
                manager_Avail_Request.put("thursdayStartTime", temp);
            }
            if ((isEmpty(thursdayEndEditText))) {
                temp = Integer.parseInt(thursdayEndEditText.getText().toString());
                manager_Avail_Request.put("thursdayEndTime", temp);
            }
            if ((isEmpty(fridayStartEditText))) {
                temp = Integer.parseInt(fridayStartEditText.getText().toString());
                manager_Avail_Request.put("fridayStartTime", temp);
            }
            if ((isEmpty(fridayEndEditText))) {
                temp = Integer.parseInt(fridayEndEditText.getText().toString());
                manager_Avail_Request.put("fridayEndTime", temp);
            }
            if ((isEmpty(saturdayStartEditText))) {
                temp = Integer.parseInt(saturdayStartEditText.getText().toString());
                manager_Avail_Request.put("saturdayStartTime", temp);
            }
            if ((isEmpty(saturdayEndEditText))) {
                temp = Integer.parseInt(saturdayEndEditText.getText().toString());
                manager_Avail_Request.put("saturdayEndTime", temp);
            }

            String eDate = effectiveDate.getText().toString();
            if (eDate.isEmpty()) {
                fillOutField();
            } else {
                date = mDate.parseDate(eDate, DATEFORMATS);
                manager_Avail_Request.put("effectiveDate", date);
            }
            manager_Avail_Request.put("userId", currentUser.getObjectId());
            manager_Avail_Request.put("name", currentUser.get("name"));


            manager_Avail_Request.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Object Save: ", "Success");
                        toast toast = new toast();
                        toast.displayLongToast(getApplicationContext(), "Availability request " +
                                "saved");
                    } else {
                        Log.d("Exception", e.getMessage());
                    }
                }
            });

        }

        public void availRequest() {
            int temp;

            if ((isEmpty(sundayStartEditText))) {
                temp = Integer.parseInt(sundayStartEditText.getText().toString());
                avail_Request.put("sundayStartTime", temp);
            }
            if ((isEmpty(sundayEndEditText))) {
                temp = Integer.parseInt(sundayEndEditText.getText().toString());
                avail_Request.put("sundayEndTime", temp);
            }
            if ((isEmpty(mondayStartEditText))) {
                temp = Integer.parseInt(mondayStartEditText.getText().toString());
                avail_Request.put("mondayStartTime", temp);
            }

            if ((isEmpty(mondayEndEditText))) {
                temp = Integer.parseInt(mondayEndEditText.getText().toString());
                avail_Request.put("mondayEndTime", temp);
            }
            if ((isEmpty(tuesdayStartEditText))) {
                temp = Integer.parseInt(tuesdayStartEditText.getText().toString());
                avail_Request.put("tuesdayStartTime", temp);
            }
            if ((isEmpty(tuesdayEndEditText))) {
                temp = Integer.parseInt(tuesdayEndEditText.getText().toString());
                avail_Request.put("tuesdayEndTime", temp);
            }
            if ((isEmpty(wednesdayStartEditText))) {
                temp = Integer.parseInt(wednesdayStartEditText.getText().toString());
                avail_Request.put("wednesdayStartTime", temp);
            }
            if ((isEmpty(wednesdayEndEditText))) {
                temp = Integer.parseInt(wednesdayEndEditText.getText().toString());
                avail_Request.put("wednesdayEndTime", temp);
            }
            if ((isEmpty(thursdayStartEditText))) {
                temp = Integer.parseInt(thursdayStartEditText.getText().toString());
                avail_Request.put("thursdayStartTime", temp);
            }
            if ((isEmpty(thursdayEndEditText))) {
                temp = Integer.parseInt(thursdayEndEditText.getText().toString());
                avail_Request.put("thursdayEndTime", temp);
            }
            if ((isEmpty(fridayStartEditText))) {
                temp = Integer.parseInt(fridayStartEditText.getText().toString());
                avail_Request.put("fridayStartTime", temp);
            }
            if ((isEmpty(fridayEndEditText))) {
                temp = Integer.parseInt(fridayEndEditText.getText().toString());
                avail_Request.put("fridayEndTime", temp);
            }
            if ((isEmpty(saturdayStartEditText))) {
                temp = Integer.parseInt(saturdayStartEditText.getText().toString());
                avail_Request.put("saturdayStartTime", temp);
            }
            if ((isEmpty(saturdayEndEditText))) {
                temp = Integer.parseInt(saturdayEndEditText.getText().toString());
                avail_Request.put("saturdayEndTime", temp);
            }

            String eDate = effectiveDate.getText().toString();
            if (eDate.isEmpty()) {
                fillOutField();
            } else {
                date = mDate.parseDate(eDate, DATEFORMATS);
                avail_Request.put("effectiveDate", date);
            }
            avail_Request.put("userId", currentUser.getObjectId());
            avail_Request.put("name", currentUser.get("name"));


            avail_Request.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Object Save: ", "Success");
                        toast toast = new toast();
                        toast.displayLongToast(getApplicationContext(), "Availability request " +
                                "saved");
                    } else {
                        Log.d("Exception", e.getMessage());
                    }
                }
            });

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

        private boolean isEmpty(EditText etText) {
            return (etText.getText().toString().trim().length() > 0);
        }

        public void fillOutField() {
            toast toast = new toast();
            toast.displayLongToast(getApplicationContext(), "Please fill out the effective date.");

        }
    }





