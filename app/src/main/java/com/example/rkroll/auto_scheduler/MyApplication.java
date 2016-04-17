package com.example.rkroll.auto_scheduler;

import com.parse.Parse;

public class MyApplication extends android.app.Application {

    @Override
    public void onCreate() {
    super.onCreate();

    //This will only be called once in your app's entire lifecycle.
    Parse.initialize(this,
    getResources().getString(R.string.applicaiton_Id),
    getResources().
    getString(R.string.client_Key));
    }
}