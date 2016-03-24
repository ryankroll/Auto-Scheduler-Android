package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CompanyInformation extends AppCompatActivity {

    private TextView companyName;
    private TextView storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        companyName = (TextView) findViewById(R.id.companyName);
        storeId = (TextView) findViewById(R.id.storeId);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
        query.whereEqualTo("companyName", "Wendy's");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    objectRetrivalSuccessful(object);
                } else {
                    objectRetrivalFailed();
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

    public void objectRetrivalSuccessful(ParseObject object) {
        companyName.setText(object.getString("companyName"));
        storeId.setText(String.valueOf(object.getInt("storeID")));
    };
    public void objectRetrivalFailed() {

    };


}
