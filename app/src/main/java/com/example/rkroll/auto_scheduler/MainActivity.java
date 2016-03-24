package com.example.rkroll.auto_scheduler;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TextInputLayout;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;



public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this);
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        setContentView(R.layout.content_main);

        usernameEditText = ((TextInputLayout) findViewById(
                R.id.login_username_input)).getEditText();
        passwordEditText = ((TextInputLayout) findViewById(
                R.id.login_password_input)).getEditText();
        loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(loginListener);
    }



    private final View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(username.isEmpty() | password.isEmpty()) {
                inputUsernamePassword();
            }
            else {
                 ParseUser.logInInBackground(username, password, new LogInCallback() {
                     @Override
                     public void done(ParseUser user, ParseException e) {
                         if (e == null && user != null) {
                             loginSuccessful();
                         } else if (user == null) {
                             usernameOrPasswordIsInvalid();
                         } else {
                             somethingWentWrong();
                         }
                     }
                 });
            }
    }
    };
    public void inputUsernamePassword(){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Please input a username and password";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public void usernameOrPasswordIsInvalid(){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Username or Password is invalid";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void loginSuccessful(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void somethingWentWrong(){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Something went wrong please try to login again.";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

