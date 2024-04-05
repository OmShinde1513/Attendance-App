package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseSubject extends AppCompatActivity {
    Button btn;
    RadioButton ADBMS,CNA,CT,FLAT,SEAD;
    String Subject;
    RadioGroup radioGroup;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);
        radioGroup=findViewById(R.id.radiogroup);
        ADBMS=findViewById(R.id.adbms);
        CNA=findViewById(R.id.cna);
        CT=findViewById(R.id.ct);
        FLAT=findViewById(R.id.flat);
        SEAD=findViewById(R.id.sead);
        btn=findViewById(R.id.done);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
             switch (i) {
                 case R.id.adbms: {
                     Subject = "ADBMS";
                     break;
                 }
                 case R.id.cna: {
                     Subject = "CNA";
                     break;
                 }
                 case R.id.ct: {
                     Subject = "CT";
                     break;
                 }
                 case R.id.flat: {
                     Subject = "FLAT";
                     break;
                 }
                 case R.id.sead: {
                     Subject = "SEAD";
                 }


             }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(Subject!=null && user!=null){
                    String uid=user.getUid();
                    firebaseDatabase.getReference().child("Teachers Details").child(uid).setValue(Subject).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete()){
                                startActivity(new Intent(ChooseSubject.this,SelectActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(ChooseSubject.this, "Some error occurred Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(ChooseSubject.this, "Select Subject", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}