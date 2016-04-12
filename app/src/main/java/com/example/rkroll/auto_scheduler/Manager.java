package com.example.rkroll.auto_scheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.parse.ParseUser;

import java.util.Date;
import java.util.List;

public class Manager extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView requestRV;
    private RecyclerView availRV;
    LinearLayoutManager llmRequest = new LinearLayoutManager(this);
    LinearLayoutManager llmAvail = new LinearLayoutManager(this);
    private UserReaderDbHelper userDb;
    private List<ParseUser> currentEmployees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Getting the two different references for the Recycler Views
        requestRV = (RecyclerView) findViewById(R.id.requestRecyclerView);
        availRV = (RecyclerView) findViewById(R.id.availRecyclerView);


        requestRV.setLayoutManager(llmRequest);
//        adapter = new EmployeeAdapter(b, itemClickListener, itemLongClickListener);
        requestRV.setAdapter(adapter);


        availRV.setLayoutManager(llmAvail);
//        adapter = new EmployeeAdapter(b, itemClickListener, itemLongClickListener);
        availRV.setAdapter(adapter);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //If general manager button is pressed from menu attempt to access Store information
        if (id == R.id.history) {
            // create a new AlertDialog
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);

            // set the AlertDialog's title
            builder.setTitle(
                    getString(R.string.enterHistoryDate));


            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);

            final EditText startDate = new EditText(this);
            final EditText endDate = new EditText(this);

            startDate.setHint("Enter the first date");
            startDate.setInputType(InputType.TYPE_CLASS_DATETIME);
            endDate.setHint("Enter the last date");
            endDate.setInputType(InputType.TYPE_CLASS_DATETIME);

            ll.addView(startDate);
            ll.addView(endDate);

            builder.setView(ll);

            builder.setNegativeButton(android.R.string.cancel, null);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Manager.this, ScheduleHistory.class);
                    startActivity(intent);
                }
            });
            AlertDialog ad = builder.create();
            ad.show();

            return true;
        }

        // if manager settings button is pressed from menu attempt to access add employee screen
        if (id == R.id.createEditSchedule) {
            Intent intent = new Intent(this, CreateEditSchedule.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final View.OnClickListener itemClickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            };

    private final View.OnLongClickListener itemLongClickListener =
            new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // get the tag that the user long touched
                    final String tag = ((TextView) view).getText().toString();

                    return true;
                }
            };
}
