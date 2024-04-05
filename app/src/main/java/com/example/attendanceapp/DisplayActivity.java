package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton Btn;
    TextView department,division,Class,subject,date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        recyclerView=findViewById(R.id.recyclerview);
        department=findViewById(R.id.department);
        division=findViewById(R.id.division);
        Class=findViewById(R.id.Class);
        subject=findViewById(R.id.subject);
        date=findViewById(R.id.date);
        Btn=findViewById(R.id.sms);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        department.setText("Department : "+ShowActivity.DEPARTMENT);
        Class.setText("Class : "+ShowActivity.CLASS);
        division.setText("Division : "+ ShowActivity.DIVISION);
        subject.setText("Subject : "+ShowActivity.SUBJECT);
        date.setText("Date : "+ShowActivity.DATE);
        DisplayAdapter displayAdapter=new DisplayAdapter(this);
        recyclerView.setAdapter(displayAdapter);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(DisplayActivity.this,SMS.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}