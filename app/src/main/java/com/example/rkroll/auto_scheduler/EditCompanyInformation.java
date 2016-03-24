package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
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
import com.parse.SaveCallback;


public class EditCompanyInformation extends AppCompatActivity {

    private EditText companyEditText;
    private EditText storeIdEditText;
    private Button saveButton;
    private TextView companyNameTextView;
    private TextView storeIdTextView;
    ParseObject currentCompany = new ParseObject("Store");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        companyEditText = ((EditText) findViewById(R.id.editCompanyName));
        storeIdEditText = ((EditText) findViewById(R.id.editStoreID));

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

        companyNameTextView= (TextView) findViewById(R.id.companyName);
        storeIdTextView = (TextView) findViewById(R.id.storeId);
        Intent intent = getIntent();
        String companyName = intent.getStringExtra("EXTRA_COMPANY_NAME");
        String storeId = intent.getStringExtra("EXTRA_STOREID");
        companyNameTextView.setText(companyName);
        storeIdTextView.setText(storeId);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
        query.whereEqualTo("companyName", "Wendy's");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    objectRetrievalSuccessful(object);
                } else {
                    objectRetreivalFailed();
                }
            }

        });


    }

    private final View.OnClickListener saveButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            String newCompanyName = companyEditText.getText().toString();
            int newStoreId = Integer.parseInt(storeIdEditText.getText().toString());

            currentCompany.put("companyName", newCompanyName);
            currentCompany.put("storeID", newStoreId);
            currentCompany.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("Success", "Success");
                    }
                    else {
                        Log.d("Exception: ", e.getMessage());
                    }
                }
            });
        }

    };

    public void objectRetrievalSuccessful(ParseObject object) {
        currentCompany = object;
    }

    public void objectRetreivalFailed() {

    }

}
