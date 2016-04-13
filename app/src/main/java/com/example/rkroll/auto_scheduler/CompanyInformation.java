package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Locale;

public class CompanyInformation extends AppCompatActivity {

    private TextView companyName;
    private TextView storeId;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        companyName = (TextView) findViewById(R.id.companyName);
        storeId = (TextView) findViewById(R.id.storeId);

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

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
        query.whereEqualTo("companyName", "Wendy's");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    objectRetrievalSuccessful(object);
                } else {
                    Log.d("Exception: ", e.getMessage());
                }
            }

        });
    }

    public void editInfo(View view){
        Intent intent = new Intent(this, EditCompanyInformation.class);
        intent.putExtra("EXTRA_COMPANY_NAME", companyName.getText().toString());
        intent.putExtra("EXTRA_STOREID", storeId.getText().toString());
        startActivity(intent);
    }

    public void changeStoreHrs(View view){
        Intent intent = new Intent(this, EditStoreHours.class);
        startActivity(intent);
    }

    public void objectRetrievalSuccessful(ParseObject object) {
        companyName.setText(object.getString("companyName"));
        storeId.setText(String.valueOf(object.getInt("storeID")));
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


}
