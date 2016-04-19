package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ViewEmployeeInfo extends AppCompatActivity {

    private TextView empNameTextView;
    private TextView empEmailTextView;
    private TextView empPhoneNumberTextView;
    private TextView empBirthdayTextView;
    private TextView empHireDate;
    private TextView empPosStatus;

    private String empName;

    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};

    SimpleDateFormatStringToDate mDate = new SimpleDateFormatStringToDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        empNameTextView = (TextView) findViewById(R.id.employeeName);
        empEmailTextView = (TextView) findViewById(R.id.emp);
        empPhoneNumberTextView = (TextView) findViewById(R.id.employeeNumber);
        empBirthdayTextView = (TextView) findViewById(R.id.empBirthday);
        empHireDate = (TextView) findViewById(R.id.empHireDate);
        empPosStatus = (TextView) findViewById(R.id.empPosStatus);

        Intent intent = getIntent();
        empName = intent.getStringExtra("EXTRA_NAME");

        //ParseUser user = new ParseUser();
        //user.put("name", empName);

        ParseQuery<ParseUser> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("name", empName);
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if (e == null) {
                    objectRetrievalSuccessful(object);
                } else {
                    Log.d("Retrieval failed", e.getMessage());
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void objectRetrievalSuccessful(ParseUser object) {
        empNameTextView.setText((object.getString("name")));
        empEmailTextView.setText((object.getString("email")));
        String temp = String.valueOf(object.getNumber("phoneNumber"));
        temp = String.format("(%s) %s-%s", temp.substring(0,3), temp.substring(3,6), temp
                .substring(6,10));
        empPhoneNumberTextView.setText(temp);
        String dateString = mDate.parseDate(object.getDate("birthDate"), DATEFORMATS);
        empBirthdayTextView.setText(dateString);
        dateString = mDate.parseDate(object.getDate("hireDate"), DATEFORMATS);
        empHireDate.setText(dateString);
        if(object.getBoolean("isFullTime")) {
            empPosStatus.setText("Full-Time");
        } else {
            empPosStatus.setText("Part-Time");
        }
    }

}
