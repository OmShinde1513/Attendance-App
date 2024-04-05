package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class AddSubjects extends AppCompatActivity {
//    Spinner departmentSpinner, classSpinner, divisionSpinner;
//
//    String  DEPARTMENT, CLASS, DIVISION,SUBJECT;
//    EditText e_subject;
//    Button Btn;
//    FirebaseDatabase firebaseDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_subjects);
//        departmentSpinner = findViewById(R.id.departmentSpinner);
//        classSpinner = findViewById(R.id.classSpinner);
//        divisionSpinner = findViewById(R.id.divisionSpinner);
//        e_subject=findViewById(R.id.subject);
//        Btn=findViewById(R.id.addBtn);
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.departmentList);
//        departmentSpinner.setAdapter(departmentAdapter);
//        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.divisionList);
//        divisionSpinner.setAdapter(divisionAdapter);
//        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.classList);
//        classSpinner.setAdapter(classAdapter);
//        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i!=0){
//                    DEPARTMENT = adapterView.getAdapter().getItem(i).toString();
//                }
//                else{
//                    DEPARTMENT=null;
//                }
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i!=0){
//                    DIVISION = adapterView.getAdapter().getItem(i).toString();
//                }
//                else{
//                    DIVISION=null;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i!=0)
//                {
//                    CLASS = adapterView.getAdapter().getItem(i).toString();
//                }
//                else{
//                    CLASS=null;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SUBJECT=e_subject.getText().toString();
//                if(DEPARTMENT==null || CLASS==null || DIVISION==null || SUBJECT.isEmpty()){
//                    Toast.makeText(AddSubjects.this, "Please fill the required details", Toast.LENGTH_SHORT).show();
//                }
//                else{
//
//                    firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child(SUBJECT).setValue("");
//                    Toast.makeText(AddSubjects.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();
//                    SUBJECT="";
//                }
//            }
//        });
//
//    }
}