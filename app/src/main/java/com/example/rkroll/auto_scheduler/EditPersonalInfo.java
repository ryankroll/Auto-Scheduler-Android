package com.example.rkroll.auto_scheduler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;

public class EditPersonalInfo extends AppCompatActivity {

    private TextView empEmailTextView;
    private TextView empPhoneNumberTextView;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private Button saveButton;

    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String empEmail = intent.getStringExtra("EXTRA_EMP_EMAIL");
        String empPhoneNumber = intent.getStringExtra("EXTRA_EMP_PHONENUMBER");

        emailEditText = ((EditText) findViewById(R.id.editEmail));
        phoneNumberEditText = ((EditText) findViewById(R.id.editPhoneNumber));
        empEmailTextView = (TextView) findViewById(R.id.currentEmail);
        empPhoneNumberTextView = (TextView) findViewById(R.id.currentNumber);

        empEmailTextView.setText(empEmail);
        empPhoneNumberTextView.setText(empPhoneNumber);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private final View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isEmpty(emailEditText) && isEmpty(phoneNumberEditText)){
                String newEmail = emailEditText.getText().toString();
                long newPhoneNumber = Long.parseLong(phoneNumberEditText.getText().toString());
                verifyUser(newEmail, newPhoneNumber);
            } else if (isEmpty(emailEditText)) {
                String newEmail = emailEditText.getText().toString();
                verifyUser(newEmail);
            } else if (isEmpty(phoneNumberEditText)) {
                long newPhoneNumber = Long.parseLong(phoneNumberEditText.getText().toString());
                verifyUser(newPhoneNumber);
            }

        }
    };


   private final void verifyUser(final String email, final long PH){

       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Verify user");
       builder.setMessage("Please enter login credentials");
       LinearLayout layout = new LinearLayout(this);
       layout.setOrientation(LinearLayout.VERTICAL);
       final EditText passwordEditText = new EditText(this);
       final EditText usernameEditText = new EditText(this);
       usernameEditText.setHint("input username");
       passwordEditText.setHint("input password");
       layout.addView(usernameEditText);
       layout.addView(passwordEditText);
       builder.setView(layout);

       builder.setNegativeButton(android.R.string.cancel, null);
       builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               String password;
               String username;
               password = passwordEditText.getText().toString();
               username = usernameEditText.getText().toString();
               try {
                   ParseUser user = ParseUser.logIn(username, password);
                   user.setEmail(email);
                   user.put("phoneNumber", PH);
                   user.saveInBackground();
               } catch (ParseException e) {
                   e.printStackTrace();
               }
           }
       });
       AlertDialog ad = builder.create();
       ad.show();

   }

    private final void verifyUser(final long PH){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Verify user");
        builder.setMessage("Please enter login credentials");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        final EditText passwordEditText = new EditText(this);
        final EditText usernameEditText = new EditText(this);
        usernameEditText.setHint("input username");
        passwordEditText.setHint("input password");
        layout.addView(usernameEditText);
        layout.addView(passwordEditText);
        builder.setView(layout);

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password;
                String username;
                password = passwordEditText.getText().toString();
                username = usernameEditText.getText().toString();
                try {
                    ParseUser user = ParseUser.logIn(username, password);
                    user.put("phoneNumber", PH);
                    user.saveInBackground();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        AlertDialog ad = builder.create();
        ad.show();

    }

    private final void verifyUser(final String email){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Verify user");
        builder.setMessage("Please enter login credentials");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        final EditText passwordEditText = new EditText(this);
        final EditText usernameEditText = new EditText(this);
        usernameEditText.setHint("input username");
        passwordEditText.setHint("input password");
        layout.addView(usernameEditText);
        layout.addView(passwordEditText);
        builder.setView(layout);

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password;
                String username;
                password = passwordEditText.getText().toString();
                username = usernameEditText.getText().toString();
                try {
                    ParseUser user = ParseUser.logIn(username, password);
                    user.setEmail(email);
                    user.saveInBackground();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        AlertDialog ad = builder.create();
        ad.show();

    }

    private boolean isEmpty(EditText etText) {
        return (etText.getText().toString().trim().length() > 0);
    }

}
