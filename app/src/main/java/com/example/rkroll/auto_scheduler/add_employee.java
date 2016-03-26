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

import com.parse.ParseUser;
import com.parse.SignUpCallback;



import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
    private String aFormat = "yyyy-MM-dd";
    private boolean m;
    private boolean gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameEditText = ((TextInputLayout) findViewById(R.id.username)).getEditText();
        nameEditText = ((TextInputLayout) findViewById(R.id.name)).getEditText();
        passwordEditText = ((TextInputLayout) findViewById(R.id.password)).getEditText();
        emailEditText = ((TextInputLayout) findViewById(R.id.email)).getEditText();
        birthDateEditText = ((TextInputLayout) findViewById(R.id.birthDate)).getEditText();
        hireDateEditText = ((TextInputLayout) findViewById(R.id.hireDate)).getEditText();
        phoneNumberEditText = ((TextInputLayout) findViewById(R.id.phoneNumber)).getEditText();
        manager = ((CheckBox) findViewById(R.id.Manager));
        generalManager = ((CheckBox) findViewById(R.id.getGeneralManager));
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(submitListener);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private final View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String hDate = hireDateEditText.getText().toString();
            Date hireDate = stringToDate(hDate, aFormat);
            String bDate = birthDateEditText.getText().toString();
            Date birthDate = stringToDate(bDate, aFormat);
            double phoneNumber = Double.parseDouble(phoneNumberEditText.getText().toString());

            ParseUser user = new ParseUser();

            user.setUsername(username);
            user.setPassword(password);
            user.put("name", name);
            user.setEmail(email);
            user.put("hireDate", hireDate);
            user.put("birthDate", birthDate);
            user.put("phoneNumber", phoneNumber);
            user.put("isManager", m);
            user.put("isGeneralManager", gm);

            if (username.isEmpty() | password.isEmpty() | name.isEmpty() | email.isEmpty() | hDate
                    .isEmpty() | bDate.isEmpty() | phoneNumber == 0) {
                // Add toast dialog to make sure user inputs all fields
                System.out.println("Please fill out all fields");
            } else {
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            myObjectSavedSuccessfully();
                        } else {
                            myObjectDidNotSaveSuccessfully();
                        }
                    }
                });
            }
        }
    };

    public Date stringToDate(String aDate, String aFormat) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;
    }

    public void myObjectSavedSuccessfully() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        CharSequence text = "User saved successfully";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void myObjectDidNotSaveSuccessfully() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        CharSequence text = "User was not saved";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onCheckBoxClicked (View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.Manager:
                if (checked)
                    m = true;
                break;
            case R.id.getGeneralManager:
                if (checked)
                    gm = true;
                break;
        }
    }
}
