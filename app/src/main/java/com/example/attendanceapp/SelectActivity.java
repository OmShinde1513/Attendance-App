package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SelectActivity extends AppCompatActivity {
 Button seeBtn,addBtn,seeDefaulter,addStdBtn;
 public static String SUBJECT;

//    public static ArrayList<String> subjectList=new ArrayList<>();
//    public static ArrayList<String> departmentList=new ArrayList<>();
//    public static ArrayList<String> classList=new ArrayList<>();
//    public static ArrayList<String> divisionList=new ArrayList<>();
 FirebaseDatabase firebaseDatabase;
 FirebaseAuth firebaseAuth;

 FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

//        departmentList.add(0,"Select Department");
//        departmentList.add("Information Technology");
//        departmentList.add("Computer Science");
//        departmentList.add("EnTc");
//        departmentList.add("Mechanical");
//        departmentList.add("Civil");
//        departmentList.add("Electrical");

//        classList.add(0,"Select Class");
//        classList.add("First Year");
//        classList.add("Second Year");
//        classList.add("Third Year");
//        classList.add("Final Year");

//        divisionList.add(0,"Select Division");
//        divisionList.add("A");
//        divisionList.add("B");
//        divisionList.add("C");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        seeBtn=findViewById(R.id.see);
        addBtn=findViewById(R.id.add);
        addStdBtn=findViewById(R.id.stdBtn);
        seeDefaulter=findViewById(R.id.seeDefaulterBtn);
//        subBtn=findViewById(R.id.subBtn);
        seeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,ShowActivity.class));
            }
        });
        addStdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,AddStudents.class));

            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,StartActivity.class));
            }
        });
        seeDefaulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectActivity.this,DefaulterList.class));
            }
        });
//        subBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SelectActivity.this,AddSubjects.class));
//            }
//        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user=firebaseAuth.getCurrentUser();
//        if(user!=null){
//            String uid=user.getUid();
//            firebaseDatabase.getReference().child("Teachers Details").child(uid).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists()) {
//                        SUBJECT = snapshot.getValue().toString();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//    }
    //    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseDatabase.getReference().child("Teachers Details").child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot)
//            {
//                if(snapshot.exists()) {
////                     subjectList.clear();
////                     classList.clear();
////                     divisionList.clear();
////                     departmentList.clear();
//                     for(DataSnapshot sn:snapshot.getChildren()){
////                         Log.d("item", Objects.requireNonNull(sn.getValue()).toString());
//                         TeacherModel Model = snapshot.getValue(TeacherModel.class);
//                         assert Model != null;
//                         Log.d("item",Model.Department);
////                         Collections.addAll(departmentList, Model.getDepartment().split("\n"));
////                         Collections.addAll(divisionList,Model.getDivision().split("\n"));
////                         Collections.addAll(classList,Model.getClasses().split("\n"));
////                         Collections.addAll(subjectList,Model.getSubject().split("\n"));
////                         departmentList.add(0,"Select Departments");
////                         classList.add(0,"Select Class");
////                         subjectList.add(0,"Select Subjects");
////                         divisionList.add(0,"Select Division");
//                     }
//
//                }
//                else
//                {
//                    Toast.makeText(SelectActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//    }
}