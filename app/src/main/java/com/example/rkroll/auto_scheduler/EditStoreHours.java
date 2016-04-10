package com.example.rkroll.auto_scheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.Locale;

public class EditStoreHours extends AppCompatActivity {

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

    ParseObject currentCompany = new ParseObject("Store");

    private Button saveButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store_hours);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Store hour edit text fields
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

        //Store hours text view
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

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
        query.whereEqualTo("companyName", "Wendy's");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    objectRetrievalSuccessful(object);
                } else {
                    Log.d("Object Retrieval: ", e.getMessage());
                }
            }

        });

    }

    private final View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!(isEmpty(sundayStartEditText))) {
                int temp = Integer.parseInt(sundayStartEditText.getText().toString());
                currentCompany.put("sundayStartTime", temp);
            }
            if (!(isEmpty(sundayEndEditText))) {
                int temp = Integer.parseInt(sundayEndEditText.getText().toString());
                currentCompany.put("sundayEndTime", temp);
            }
            if (!(isEmpty(mondayStartEditText))) {
                int temp = Integer.parseInt(mondayStartEditText.getText().toString());
                currentCompany.put("mondayStartTime", temp);
            }

            if (!(isEmpty(mondayEndEditText ))) {
                int temp = Integer.parseInt(mondayEndEditText.getText().toString());
                currentCompany.put("mondayEndTime", temp);
            }
            if (!(isEmpty(tuesdayStartEditText))) {
                int temp = Integer.parseInt(tuesdayStartEditText.getText().toString());
                currentCompany.put("tuesdayStartTime", temp);
            }
            if (!(isEmpty(tuesdayEndEditText))) {
                int temp = Integer.parseInt(tuesdayEndEditText.getText().toString());
                currentCompany.put("tuesdayEndTime", temp);
            }
            if (!(isEmpty(wednesdayStartEditText))) {
                int temp = Integer.parseInt(wednesdayStartEditText.getText().toString());
                currentCompany.put("wednesdayStartTime", temp);
            }
            if (!(isEmpty(wednesdayEndEditText))) {
                int temp = Integer.parseInt(wednesdayEndEditText.getText().toString());
                currentCompany.put("wednesdayEndTime", temp);
            }
            if (!(isEmpty(thursdayStartEditText))) {
                int temp = Integer.parseInt(thursdayStartEditText.getText().toString());
                currentCompany.put("thursdayStartTime", temp);
            }
            if (!(isEmpty(thursdayEndEditText))) {
                int temp = Integer.parseInt(thursdayEndEditText.getText().toString());
                currentCompany.put("thursdayEndTime", temp);
            }
            if (!(isEmpty(fridayStartEditText))) {
                int temp = Integer.parseInt(fridayStartEditText.getText().toString());
                currentCompany.put("fridayStartTime", temp);
            }
            if (!(isEmpty(fridayEndEditText))) {
                int temp = Integer.parseInt(fridayEndEditText.getText().toString());
                currentCompany.put("fridayEndTime", temp);
            }
            if (!(isEmpty(saturdayStartEditText))) {
                int temp = Integer.parseInt(saturdayStartEditText.getText().toString());
                currentCompany.put("saturdayStartTime", temp);
            }
            if (!(isEmpty(saturdayEndEditText))) {
                int temp = Integer.parseInt(saturdayEndEditText.getText().toString());
                currentCompany.put("saturdayEndTime", temp);
            }

            currentCompany.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Object Save: ", "Success");
                    } else {
                        Log.d("Exception: ", e.getMessage());
                    }
                }
            });

        }
    };


    public void objectRetrievalSuccessful(ParseObject object) {
        displayStoreHours(object);
        currentCompany = object;
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
}