package com.example.myproxy;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.net.Socket;

public class ParcelableSocket implements Parcelable {
    private Socket mySocket;

    protected ParcelableSocket(Socket socket) {
        Log.d("kimiya", "ps: cnstr");
        mySocket = socket;
    }

    protected ParcelableSocket(Parcel in) {
        Log.d("kimiya", "ps: cnstr_pre");
        mySocket = (Socket)in.readValue(null);
    }

    public static final Creator<ParcelableSocket> CREATOR = new Creator<ParcelableSocket>() {
        @Override
        public ParcelableSocket createFromParcel(Parcel in) {
            Log.d("kimiya", "ps: crt");
            return new ParcelableSocket(in);
        }

        @Override
        public ParcelableSocket[] newArray(int size) {
            return new ParcelableSocket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d("kimiya", "ps: write");
       parcel.writeValue(mySocket);
        //Log.d("kimiya", "ps: written");

    }
}
