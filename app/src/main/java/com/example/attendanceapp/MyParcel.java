package com.example.attendanceapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyParcel implements Parcelable {
    ArrayList<Model> modelList=new ArrayList<>();
    String DEPARTMENT,CLASS,DIVISION,SUBJECT,DATE;
    ArrayList<String> absentList=new ArrayList<>();
    protected MyParcel(Parcel in) {
   this.modelList=in.readArrayList(null);
   this.absentList=in.readArrayList(null);
   this.DEPARTMENT=in.readString();
   this.CLASS=in.readString();
   this.DIVISION=in.readString();
   this.SUBJECT=in.readString();
   this.DATE=in.readString();
    }

    public static final Creator<MyParcel> CREATOR = new Creator<MyParcel>() {
        @Override
        public MyParcel createFromParcel(Parcel in) {
            return new MyParcel(in);
        }

        @Override
        public MyParcel[] newArray(int size) {
            return new MyParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
parcel.writeSerializable(this.modelList);
parcel.writeString(this.DEPARTMENT);
parcel.writeString(this.CLASS);
parcel.writeString(this.DIVISION);
parcel.writeString(this.SUBJECT);
parcel.writeString(this.DATE);
parcel.writeSerializable(this.absentList);
    }
}
