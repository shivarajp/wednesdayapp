package com.shivaraj.wednesdayapp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Suggestions {

    private String addressName;
    //private boolean mIsHistory = false;

    public Suggestions(String suggestion) {
        this.addressName = suggestion.toLowerCase();
    }

    public Suggestions(Parcel source) {
        this.addressName = source.readString();
        //this.mIsHistory = source.readInt() != 0;
    }



    public static final Parcelable.Creator<Suggestions> CREATOR = new Parcelable.Creator<Suggestions>() {
        @Override
        public Suggestions createFromParcel(Parcel in) {
            return new Suggestions(in);
        }

        @Override
        public Suggestions[] newArray(int size) {
            return new Suggestions[size];
        }
    };

}
