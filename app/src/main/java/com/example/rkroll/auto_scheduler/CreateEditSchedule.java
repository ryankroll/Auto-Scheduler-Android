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
    private int tableBoxsOfWeek = 7, maxEmp = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tableLayout = (TableLayout) findViewById(R.id.weekSchedule);
        buildTable(maxEmp,tableBoxsOfWeek);


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
        TextView tv;
        TableRow tr;
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

                if (j == 1){
                    switch(i){
                        case 1: tableBox = "Sun: ";
                            break;
                        case 2: tableBox = "Mon: ";
                            break;
                        case 3: tableBox = "Tues: ";
                            break;
                        case 4: tableBox = "Wed: ";
                            break;
                        case 5: tableBox = "Thurs: ";
                            break;
                        case 6: tableBox = "Fri: ";
                            break;
                        case 7: tableBox = "Sat: ";
                            break;
                    }
                    tv.setText(tableBox);
                }else{
                    tableBox = "R" + i + ", C" + j;
                    tv.setText(tableBox);
                }
                tr.addView(tv);

            }  // End of second for loop

            tableLayout.addView(tr);
        } // End of first for loop
    }

}
