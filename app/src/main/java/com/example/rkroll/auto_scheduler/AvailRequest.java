package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;
import java.util.Locale;

public class AvailRequest extends AppCompatActivity {

        private TextView nameTextView;
        private TextView sundayReqTime;
        private TextView mondayReqTime;
        private TextView tuesdayReqTime;
        private TextView wednesdayReqTime;
        private TextView thursdayReqTime;
        private TextView fridayReqTime;
        private TextView saturdayReqTime;

        private TextView sundayTime;
        private TextView mondayTime;
        private TextView tuesdayTime;
        private TextView wednesdayTime;
        private TextView thursdayTime;
        private TextView fridayTime;
        private TextView saturdayTime;

        private Button approveButton;
        private Button denyButton;

        private String objectId;
        private String userId;
        private static String notScheduled = "N/A";
        private ParseObject currentAvail = new ParseObject("Availability");

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_avail_request);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Intent intent = getIntent();
            objectId = intent.getStringExtra("EXTRA_OBJECT_ID");
            userId = intent.getStringExtra("EXTRA_USER_ID");
            String name = intent.getStringExtra("EXTRA_NAME");

            nameTextView = (TextView) findViewById(R.id.userReq);
            nameTextView.setText(name);

            sundayReqTime = (TextView) findViewById(R.id.sundayTime);
            mondayReqTime = (TextView) findViewById(R.id.mondayTime);
            tuesdayReqTime = (TextView) findViewById(R.id.tuesdayTime);
            wednesdayReqTime = (TextView) findViewById(R.id.wednesdayTime);
            thursdayReqTime = (TextView) findViewById(R.id.thursdayTime);
            fridayReqTime = (TextView) findViewById(R.id.fridayTime);
            saturdayReqTime = (TextView) findViewById(R.id.saturdayTime);

            sundayTime = (TextView) findViewById(R.id.sundayReqTime);
            mondayTime = (TextView) findViewById(R.id.mondayReqTime);
            tuesdayTime = (TextView) findViewById(R.id.tuesdayReqTime);
            wednesdayTime = (TextView) findViewById(R.id.wednesdayReqTime);
            thursdayTime = (TextView) findViewById(R.id.thursdayReqTime);
            fridayTime = (TextView) findViewById(R.id.fridayReqTime);
            saturdayTime = (TextView) findViewById(R.id.saturdayReqTime);

            approveButton = (Button) findViewById(R.id.acceptChange);
            approveButton.setOnClickListener(approveButtonListener);

            denyButton = (Button) findViewById(R.id.denyChange);
            denyButton.setOnClickListener(denyButtonListener);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Availability");
            query.whereEqualTo("userId", userId);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        //currentAvail.put("userId", object.getString("userId"));
                        objectRetrievalSuccessful(object);
                    } else {
                        Log.d("Retrieval Failed", e.getMessage());
                    }
                }
            });

            ParseQuery<ParseObject> requestQuery = ParseQuery.getQuery("Request_Availability");
            requestQuery.getInBackground(objectId, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        requestObjectRetrievalSuccessful(object);
                    } else {
                        Log.d("Retrieval Failed", e.getMessage());
                    }
                }
            });

        }

    private final View.OnClickListener approveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseQuery<ParseObject> requestQuery = ParseQuery.getQuery("Request_Availability");
            requestQuery.getInBackground(objectId, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        //approvedAvailChange(object);
                    } else {
                        Log.d("Retrieval Failed", e.getMessage());
                    }
                }
            });
        }
    };
    private final View.OnClickListener denyButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseQuery<ParseObject> requestQuery = ParseQuery.getQuery("Request_Availability");
            requestQuery.getInBackground(objectId, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        object.deleteInBackground();
                        toast toast = new toast();
                        toast.displayLongToast(getApplicationContext(), "Availability change " +
                                "denied");
                    } else {
                        Log.d("Retrieval Failed", e.getMessage());
                    }
                }
            });

        }
    };
/*
    public void approvedAvailChange(ParseObject object) {

        if (!isEmpty(object.getNumber("sundayStartTime"))){
            Log.d("SundayStartTime", object.getNumber("sundayStartTime").toString());
            currentAvail.put("sundayStartTime", object.getNumber
                    ("sundayStartTime"));
            Log.d("SundayStart", currentAvail.getNumber("sundayStartTime").toString());
        }
        Log.d("SundayEndTime", String.valueOf(object.getInt("sundayEndTime")));

        if (!isEmpty(object.getInt("sundayEndTime"))) {
            Log.d("SundayEndTime", object.getNumber("sundayEndTime").toString());
            currentAvail.put("sundayEndTime", object.getNumber("sundayEndTime"));
            Log.d("SundayStart", currentAvail.getNumber("sundayStartTime").toString());
        }

        currentAvail.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("Object", "Saved Successfully");
                } else {
                    Log.d("Object", e.getMessage());
                }
            }
        });
    }
*/
    public void requestObjectRetrievalSuccessful (ParseObject object) {

        String temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("sundayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("sundayEndTime"));
        if(!checkIfZero(temp)) {
            sundayReqTime.setText(temp);
        } else {
            sundayReqTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("mondayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("mondayEndTime"));
        if(!checkIfZero(temp)) {
            mondayReqTime.setText(temp);
        } else {
            mondayReqTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("tuesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("tuesdayEndTime"));
        if(!checkIfZero(temp)) {
            tuesdayReqTime.setText(temp);
        } else {
            tuesdayReqTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("wednesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("wednesdayEndTime"));
        if(!checkIfZero(temp)) {
            wednesdayReqTime.setText(temp);
        } else {
            wednesdayReqTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("thursdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("thursdayEndTime"));
        if(!checkIfZero(temp)) {
            thursdayReqTime.setText(temp);
        } else {
            thursdayReqTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("fridayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("fridayEndTime"));
        if(!checkIfZero(temp)) {
            fridayReqTime.setText(temp);
        } else {
            fridayReqTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("saturdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("saturdayEndTime"));
        if(!checkIfZero(temp)) {
            saturdayReqTime.setText(temp);
        } else {
            saturdayReqTime.setText(notScheduled);
        }
    }

    public void objectRetrievalSuccessful(ParseObject object) {
        String temp =(String.format(Locale.getDefault(), "%04d", object.getInt
                ("sundayStartTime"))) + (" - ") + String.format(Locale.getDefault(), "%04d",
                object
                .getInt("sundayEndTime"));
        if(!checkIfZero(temp)) {
            sundayTime.setText(temp);
        } else {
            sundayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("mondayStartTime")) + (" - ") + String.format(Locale.getDefault(), "%04d", object
                .getInt("mondayEndTime"));
        if(!checkIfZero(temp)) {
            mondayTime.setText(temp);
        } else {
            mondayTime.setText(notScheduled);
        }
        temp = String.format(Locale.getDefault(), "%04d", object.getInt
                ("tuesdayStartTime")) + " - " + String.format(Locale.getDefault(), "%04d", object
                .getInt("tuesdayEndTime"));
        if(!checkIfZero(temp)) {
            tuesdayTime.setText(temp);
        } else {
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
        }

        public boolean isEmpty(Number s) {
            return (s == 0);
        }

}