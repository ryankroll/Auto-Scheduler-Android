package com.example.rkroll.auto_scheduler;


import android.content.Context;
import android.widget.Toast;

// Use this class to display toast messages by calling one function
public class toast {
    private String message;
    private static Context context;

    toast() {
        //default constructor
    };

    public void displayLongToast(Context c, String m) {
        CharSequence text = m;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(c, text, duration);
        toast.show();
    }

    public void displayShortToast(Context c, String m) {
        CharSequence text = m;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(c, text, duration);
        toast.show();
    }



}
