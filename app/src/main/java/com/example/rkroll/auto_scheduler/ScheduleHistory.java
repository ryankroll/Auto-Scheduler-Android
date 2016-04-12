package com.example.rkroll.auto_scheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScheduleHistory extends AppCompatActivity {
    TableLayout tableLayout;
    private int numDays = 7, numWorking = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tableLayout = (TableLayout) findViewById(R.id.schedHistTable);
        buildTable(numDays, numWorking);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void buildTable(int col, int rows) {
        TextView tv;
        TableRow tr;
        String day = null;
        String tableBox = null;
        String getDate = null;

        for (int i = 1; i <= rows; i++) {
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 1; j <= col; j++) {
                tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setPadding(5, 5, 30, 5);

                if (j == 1){
                    getDate = "5/6";
                    tv.setText(getDate);
                }else{
                    tableBox = "R" + i + ", C" + j;
                    tv.setText(tableBox);
                }
                tr.addView(tv);

            }

            tableLayout.addView(tr);
        }
    }
}
