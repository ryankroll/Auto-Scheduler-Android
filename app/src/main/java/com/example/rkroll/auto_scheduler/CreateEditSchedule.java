package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CreateEditSchedule extends AppCompatActivity {

    TableLayout tableLayout;
    private int daysOfWeekPlus1 = 8, maxEmp = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tableLayout = (TableLayout) findViewById(R.id.weekSchedule);
        buildTable(daysOfWeekPlus1, maxEmp);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // when Edit Schedule is click go to new edit schedule view
    public void goToEditSched (View view){
        Intent intent = new Intent(this, EditSchedule.class);
        startActivity(intent);
    }

    // When create schedule button is click perform method
    public void onClickCreateNewSched (View view){
        tableLayout.removeAllViews();

    }

    // Build a 2D table
    private void buildTable(int col, int rows) {
        TextView tv = null;
        TableRow tr = null;
        String tableBox = null;

        for (int i = 1; i <= rows; i++) {
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 1; j <= col; j++) {
                tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setPadding(5, 5, 40, 5);

                if (i >= 2 && j >= 2){
                    // input times for working during the week
                    // when j = 2 day is sunday / j = 8 day is Saturday etc.
                    tableBox = "R" + i + ", C" + j;
                    tv.setText(tableBox);
                }else if (i == 1 && j >= 2){
                    switch(j){
                        case 2: tableBox = "Sunday";
                            break;
                        case 3: tableBox = "Monday";
                            break;
                        case 4: tableBox = "Tuesday";
                            break;
                        case 5: tableBox = "Wednesday";
                            break;
                        case 6: tableBox = "Thursday";
                            break;
                        case 7: tableBox = "Friday";
                            break;
                        case 8: tableBox = "Saturday";
                            break;
                    }
                    tv.setText(tableBox);
                }else if (j == 1 && i >= 2) {
                    //  input code to get username of employee and set it to tableBox
                    tableBox = "username";
                    tv.setText(tableBox);
                }else{
                    tableBox = "Employee | Week";
                    tv.setText(tableBox);
                }
                tr.addView(tv);

            }  // End of second for loop

            tableLayout.addView(tr);
        } // End of first for loop
    }

}
