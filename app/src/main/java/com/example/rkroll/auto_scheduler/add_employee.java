package com.example.rkroll.auto_scheduler;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseSession;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import com.example.rkroll.auto_scheduler.SimpleDateFormatStringToDate;

import java.util.Date;

public class add_employee extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText nameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText birthDateEditText;
    private EditText hireDateEditText;
    private EditText phoneNumberEditText;
    private CheckBox manager;
    private CheckBox generalManager;
    private Button submit;
    private boolean m;
    private boolean gm;
    private static int defaultStartAvailability = 0;
    private static int defaultEndAvailability = 2359;

    private Date date;

    private String[] DATEFORMATS = {"MM-dd-yyyy", "MM/dd/yyyy", "MMddyyyy"};

    SimpleDateFormatStringToDate mDate = new SimpleDateFormatStringToDate();

    //private String userSession;
    ParseUser currentUser = new ParseUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameEditText = ((EditText) findViewById(R.id.usernameEditText));
        nameEditText = ((EditText) findViewById(R.id.nameEditText));
        passwordEditText = ((EditText) findViewById(R.id.passwordEditText));
        emailEditText = ((EditText) findViewById(R.id.emailEditText));
        birthDateEditText = ((EditText) findViewById(R.id.birthDateEditText));
        hireDateEditText = ((EditText) findViewById(R.id.hireDateEditText));
        phoneNumberEditText = ((EditText) findViewById(R.id.phoneNumberEditText));
        manager = ((CheckBox) findViewById(R.id.Manager));
        generalManager = ((CheckBox) findViewById(R.id.getGeneralManager));
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(submitListener);

        //userSession = currentUser.getSessionToken();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private final View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseUser user = new ParseUser();

            String username = usernameEditText.getText().toString();
            if (username.isEmpty()) {
                fillOutField();
            } else {
                user.setUsername(username);
            }
            String password = passwordEditText.getText().toString();
            if(password.isEmpty()) {
                fillOutField();
            } else {
                user.setPassword(password);
            }
            String name = nameEditText.getText().toString();
            if (name.isEmpty()) {
                fillOutField();
            } else {
                user.put("name", name);
            }
            String email = emailEditText.getText().toString();
            if (email.isEmpty()) {
                fillOutField();
            } else {
                user.setEmail(email);
            }
            String hDate = hireDateEditText.getText().toString();
            if (hDate.isEmpty()) {
                fillOutField();
            } else {
                date = mDate.parseDate(hDate, DATEFORMATS);
                user.put("hireDate", date);
            }
            String bDate = birthDateEditText.getText().toString();
            if (bDate.isEmpty()) {
                fillOutField();
            } else {
                date = mDate.parseDate(bDate, DATEFORMATS);
                user.put("birthDate", date);
            }
            double phoneNumber = Double.parseDouble(phoneNumberEditText.getText().toString());
            if (phoneNumber == 0) {
                fillOutField();
            } else {
                user.put("phoneNumber", phoneNumber);
            }
            if (m && gm)
            {
                toast toast = new toast();
                toast.displayLongToast(getApplicationContext(), "Please only select manager or " +
                        "general manager");
            } else if (m){
                user.put("isManager", m);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            myObjectSavedSuccessfully();
                        } else {
                            Log.d("User failed to save", e.getMessage());
                        }
                    }
                });
            } else if (gm) {
                user.put("isGeneralManager", gm);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            myObjectSavedSuccessfully();
                        } else {
                            Log.d("User failed to save", e.getMessage());
                        }
                    }
                });
            } else {
                Log.d("User", "Not manager or GM");
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            myObjectSavedSuccessfully();
                        } else {
                            Log.d("User failed to save", e.getMessage());
                        }
                    }
                });
            }

            /*Log.d("Session Token", userSession);

            ParseUser.becomeInBackground("userSession", new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Log.d("User", currentUser.getUsername());
                    } else {
                        Log.d("Token", "could not be validated");
                    }
                }
            });*/

            }

    };

    public void myObjectSavedSuccessfully() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        CharSequence text = "User saved successfully";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        //Add open availability for user if object saved successfully
        setAvailability();

    }

    public void onCheckBoxClicked (View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.Manager:
                if (checked)
                    m = true;
                else {
                    m = false;
                }
                break;
            case R.id.getGeneralManager:
                if (checked)
                    gm = true;
                else
                    gm = false;
                break;
        }
    }
    public  void setAvailability() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseObject a = new ParseObject("Availability");
        a.put("userId", currentUser.getObjectId());
        a.put("sundayStartTime", defaultStartAvailability);
        a.put("sundayEndTime", defaultEndAvailability);
        a.put("mondayStartTime", defaultStartAvailability);
        a.put("mondayEndTime", defaultEndAvailability);
        a.put("tuesdayStartTime", defaultStartAvailability);
        a.put("tuesdayEndTime", defaultEndAvailability);
        a.put("wednesdayStartTime", defaultStartAvailability);
        a.put("wednesdayEndTime", defaultEndAvailability);
        a.put("wednesdayStartTime", defaultStartAvailability);
        a.put("wednesdayEndTime", defaultEndAvailability);
        a.put("thursdayStartTime", defaultStartAvailability);
        a.put("thursdayEndTime", defaultEndAvailability);
        a.put("fridayStartTime", defaultStartAvailability);
        a.put("fridayEndTime", defaultEndAvailability);
        a.put("saturdayStartTime", defaultStartAvailability);
        a.put("saturdayEndTime", defaultEndAvailability);

        a.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("Availability", "Saved successfully");
                } else{
                    Log.d("Failure", e.getMessage());
                }
            }
        });

    }

    public void fillOutField() {
        toast toast = new toast();
        toast.displayLongToast(getApplicationContext(), "Please fill out all fields");

    }

}
