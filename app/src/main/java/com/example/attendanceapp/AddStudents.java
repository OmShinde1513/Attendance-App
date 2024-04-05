package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddStudents extends AppCompatActivity {
//    Spinner departmentSpinner, classSpinner, divisionSpinner;

    private final String  DEPARTMENT="Information Technology", CLASS="Second Year", DIVISION="B";
    private String ROLLno,PRNno,NAME,CONTACT;

    EditText e_name,e_rollNo,e_PRNno,e_contactNo;
    FirebaseDatabase firebaseDatabase;
    Button Done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
//        departmentSpinner = findViewById(R.id.departmentSpinner);
//        classSpinner = findViewById(R.id.classSpinner);
//        divisionSpinner = findViewById(R.id.divisionSpinner);
        e_rollNo=findViewById(R.id.rollNo);
        e_PRNno=findViewById(R.id.PRN);
        e_name=findViewById(R.id.name);
        e_contactNo=findViewById(R.id.contactNumber);
        Done=findViewById(R.id.addStd);
        firebaseDatabase = FirebaseDatabase.getInstance();
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
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
//                }
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
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ROLLno=e_rollNo.getText().toString();
                PRNno=e_PRNno.getText().toString();
                NAME=e_name.getText().toString();
                CONTACT=e_contactNo.getText().toString();

                if( NAME.isEmpty() || PRNno.isEmpty() || ROLLno.isEmpty() || CONTACT.isEmpty()){
                    Toast.makeText(AddStudents.this, "Please fill required details", Toast.LENGTH_SHORT).show();
                }
                else{
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("name",NAME);
                    hashMap.put("PRNno",PRNno);
                    hashMap.put("contact",CONTACT);
                    firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child("Students Details").child(ROLLno).updateChildren(hashMap);
                    Toast.makeText(AddStudents.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                    e_rollNo.setText("");
                    e_contactNo.setText("");
                    e_name.setText("");
                    e_PRNno.setText("");
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
