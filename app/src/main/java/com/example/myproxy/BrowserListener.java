package com.example.myproxy;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import java.net.ServerSocket;

public class BrowserListener extends IntentService {
    public BrowserListener( ) {
        super("BrowserListener");
    }
    @Override
    protected void onHandleIntent(Intent workIntent) {
        int localport = 55000;
        ServerSocket ss;
        try {
            ss = new ServerSocket(localport);
            Log.d("kimiya", "socketCreated");
            while (true) {
                new Proxy(ss.accept()).start();
            }
        } catch (Exception e) {
            Log.e("kimiya", "BrowseListener: " + e.getMessage());
        }
    }
}