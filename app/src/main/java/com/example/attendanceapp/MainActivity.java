package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
Spinner spinner;
List<String> departments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        departments.add(0,"Select Department");
        departments.add("Information Technology");
        departments.add("Computer Science");
        departments.add("EnTc");
        departments.add("Electrical");
        departments.add("Civil");
        departments.add("Mechanical");
    }
}