package com.example.myproxy;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Scanner;

public class RequestReader extends IntentService {
    public RequestReader() {
        super("RequestReader");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
