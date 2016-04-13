package com.example.rkroll.auto_scheduler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;

public class MakeTimeOffRequest extends AppCompatActivity {

    private EditText startDateEditText;
    private EditText endDateEditText;

    private Button saveButton;

    private Date date;

    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};

    SimpleDateFormatStringToDate mDate = new SimpleDateFormatStringToDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_time_off_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startDateEditText = ((EditText) findViewById(R.id.startDate));
        endDateEditText = ((EditText) findViewById(R.id.endDate));

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private final View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toast toast = new toast();
            if ((!(isEmpty(startDateEditText)) || (!(isEmpty(endDateEditText))))) {
                toast.displayLongToast(getApplicationContext(), "Please enter start and end date");
            } else {
                saveData();
            }

        }

    };


        private boolean isEmpty(EditText etText) {
            return (etText.getText().toString().trim().length() > 0);
        }


        public void saveData() {
            ParseObject timeOff = new ParseObject("Request_Time_Off");
            timeOff.put("userId", ParseUser.getCurrentUser().getObjectId());
            String tempDate = startDateEditText.getText().toString();
            date = mDate.parseDate(tempDate, DATEFORMATS);
            timeOff.put("startDate", date);
            tempDate = endDateEditText.getText().toString();
            date = mDate.parseDate(tempDate, DATEFORMATS);
            timeOff.put("isApproved", false);
            timeOff.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Time-Off", "Save successfull");
                        toast toast = new toast();
                        toast.displayLongToast(getApplicationContext(), "Time off request saved");

                    } else {
                        Log.d("Time-Off SAVE ERROR", e.getMessage());
                    }
                }
            });
        }

    }

