package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RegistrationForm extends AppCompatActivity {
    EditText e_sub,e_division,e_name;
    Button btn;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    String dept,subject,Class,division,name;
    RecyclerView deptRecyclerView,ClassRecyclerView;
    ListView listView;
    ArrayList<String> deptList=new ArrayList<>();
    ArrayList<String>classList=new ArrayList<>();
    HashMap<String,Object> hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        e_name=findViewById(R.id.name);
        e_division=findViewById(R.id.division);
        e_sub=findViewById(R.id.subject);
        btn=findViewById(R.id.btn);
        deptList.add("Civil");
        deptList.add("Computer Science");
        deptList.add("CSBS");
        deptList.add("EnTc");
        deptList.add("Information Technology");
        deptList.add("Mechanical");
        classList.add("First Year");
        classList.add("Second Year");
        classList.add("Third Year");
        classList.add("Final Year");
        deptRecyclerView=findViewById(R.id.deptList);
        ClassRecyclerView=findViewById(R.id.classList);
        deptRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SelectListAdapter adapter=new SelectListAdapter(this,deptList);
        deptRecyclerView.setAdapter(adapter);
        ClassRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SelectClassAdapter classAdapter=new SelectClassAdapter(this,classList);
        ClassRecyclerView.setAdapter(classAdapter);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               subject=e_sub.getText().toString();
               division=e_division.getText().toString();
               name=e_name.getText().toString();
               Class=SelectClassAdapter.ClassList.get(0);
               dept=SelectListAdapter.DeptList.get(0);
               hashMap.put("Department",dept);
               hashMap.put("Subject",subject);
               hashMap.put("Classes",Class);
               hashMap.put("Division",division);
               hashMap.put("Name",name);
               firebaseDatabase.getReference().child("Teachers Details").child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).push().updateChildren(hashMap);
               startActivity(new Intent(RegistrationForm.this,SelectActivity.class));
               finish();
           }
       });

    }
}