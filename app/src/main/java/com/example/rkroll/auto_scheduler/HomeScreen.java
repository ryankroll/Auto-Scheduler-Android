package com.example.rkroll.auto_scheduler;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class HomeScreen extends AppCompatActivity {
    TableLayout tableLayout;
    int daysOfWeek = 7;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tableLayout = (TableLayout) findViewById(R.id.weekSchedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




        buildTable(7,daysOfWeek);

        setSupportActionBar(toolbar);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //If general manager button is pressed from menu attempt to access general manager settings
        if (id == R.id.action_settings) {

            /**************************
             NEED TO DO CHECK HERE TO MAKE SURE USER IS GENERAL MANAGER
             ***************************/

            Intent intent = new Intent(this, GeneralManager.class);
            startActivity(intent);
            return true;
        }

        // if manager settings button is pressed from menu attempt to access manager activities
        if (id == R.id.man_settings) {

            /**************************
             NEED TO DO CHECK HERE TO MAKE SURE USER IS A MANAGER
             ***************************/

            Intent intent = new Intent(this, Manager.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "HomeScreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rkroll.auto_scheduler/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "HomeScreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rkroll.auto_scheduler/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void buildTable(int col, int rows) {
        TextView tv;
        TableRow tr;
        String day = null;
        String tableBox = null;
        int dimen = (int) this.getResources().getDimension(R.dimen.tableSize);

        for (int i = 1; i <= rows; i++) {
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));

            for (int j = 1; j <= col; j++) {
                tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                tv.setPadding(5, 5, 30, 5);

                if (j == 1){
                    switch(i){
                        case 1: day = "Sun: ";
                            break;
                        case 2: day = "Mon: ";
                            break;
                        case 3: day = "Tues: ";
                            break;
                        case 4: day = "Wed: ";
                            break;
                        case 5: day = "Thurs: ";
                            break;
                        case 6: day = "Fri: ";
                            break;
                        case 7: day = "Sat: ";
                            break;
                    }
                    tv.setText(day);
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