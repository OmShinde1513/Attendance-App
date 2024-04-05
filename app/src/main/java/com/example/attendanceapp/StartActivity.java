package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class StartActivity extends AppCompatActivity implements MakeCall{
    LinearLayout linearLayout;
    FloatingActionButton fab;
//Spinner departmentSpinner,classSpinner,divisionSpinner,subjectSpinner;
ArrayList<String> arrayList=new ArrayList<>();
 TextView absentRollNo,editTextDate,sub;
RecyclerView recyclerView;

CalendarView calendarView;
//String string;
//List<String> rollList = new ArrayList<>();
private final String DEPARTMENT="Information Technology",CLASS="Second Year",DIVISION="B";
Button setDate,Done;
private String SUBJECT;

HashMap<Integer,ArrayList<String>> hashMap=new HashMap<>();
parentRecycler adapter;
FirebaseDatabase firebaseDatabase;
//    ArrayAdapter<String> subjectAdapter;
private String DATE,ROLLno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        departmentSpinner=findViewById(R.id.departmentSpinner);
//        classSpinner=findViewById(R.id.classSpinner);
//        divisionSpinner=findViewById(R.id.divisionSpinner);
//        subjectSpinner=findViewById(R.id.subjectSpinner);
        calendarView=findViewById(R.id.calender);
        absentRollNo=findViewById(R.id.absent);
        editTextDate=findViewById(R.id.EditText_date);
        linearLayout=findViewById(R.id.linearlayout3);
        recyclerView=findViewById(R.id.recyclerview);
        sub=findViewById(R.id.sub);
        fab=findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(StartActivity.this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        DateFormat dateFormat = new SimpleDateFormat("d-M-y");
        DATE = dateFormat.format(calendarView.getDate());
        Done=findViewById(R.id.upload);
//        ArrayAdapter<String> departmentAdapter =new ArrayAdapter<>(StartActivity.this, android.R.layout.simple_spinner_dropdown_item,SelectActivity.departmentList);
//        departmentSpinner.setAdapter(departmentAdapter);
//        ArrayAdapter<String> classAdapter=new ArrayAdapter<>(StartActivity.this, android.R.layout.simple_spinner_dropdown_item,SelectActivity.classList);
//        classSpinner.setAdapter(classAdapter);
//        ArrayAdapter<String> divisionAdapter=new ArrayAdapter<>(StartActivity.this, android.R.layout.simple_spinner_dropdown_item,SelectActivity.divisionList);
//        divisionSpinner.setAdapter(divisionAdapter);
//        subjectAdapter=new ArrayAdapter<>(StartActivity.this, android.R.layout.simple_spinner_dropdown_item,SelectActivity.subjectList);
//        subjectSpinner.setAdapter(subjectAdapter);
       calendarView.setVisibility(View.GONE);
     fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             calendarView.setVisibility(View.VISIBLE);
         }
     });

       calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
               DATE=i2+"-"+(i1+1)+"-"+i;
               editTextDate.setText(DATE);
               calendarView.setVisibility(View.GONE);
           }
       });
//       linearLayout.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               if(getCurrentFocus()==absentRollNo || getCurrentFocus()==editTextDate)
//               {
//                   InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                   inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
//               }
//           }
//       });

      Done.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ROLLno=absentRollNo.getText().toString();

              if(DATE.isEmpty()){
                  Toast.makeText(StartActivity.this, "Please Select the date", Toast.LENGTH_SHORT).show();
              }
            else{
                firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child(SUBJECT).child(DATE).setValue(ROLLno);
                  Toast.makeText(StartActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();
                  absentRollNo.setText("");
                  adapter.notifyDataSetChanged();
            }

          }
      });
      setRollNos();
//      departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//          @Override
//          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                  DEPARTMENT=adapterView.getAdapter().getItem(i).toString();
//                  setSubject();
//
//          }
//
//          @Override
//          public void onNothingSelected(AdapterView<?> adapterView) {
//          }
//      });
//     subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//         @Override
//         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                 SUBJECT=adapterView.getAdapter().getItem(i).toString();
//                 setSubject();
//
//         }
//
//         @Override
//         public void onNothingSelected(AdapterView<?> adapterView) {
//
//         }
//     });
//     classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//         @Override
//         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                 CLASS=adapterView.getAdapter().getItem(i).toString();
//                 setSubject();
//         }
//
//         @Override
//         public void onNothingSelected(AdapterView<?> adapterView) {
//
//         }
//     });
//     divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//         @Override
//         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                 DIVISION=adapterView.getAdapter().getItem(i).toString();
//                 setSubject();
//         }
//
//         @Override
//         public void onNothingSelected(AdapterView<?> adapterView) {
//
//         }
//
//     });
    }
    public void setRollNos(){

            firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child("Students Details").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    arrayList.clear();
                    int c=0;
                    int i=0;
                    for(DataSnapshot sn : snapshot.getChildren()){
                        if(sn.exists()){
                            arrayList.add(sn.getKey());
                            c++;
                            if(c%5==0)
                            {
                                hashMap.put(i,arrayList);
                                i++;
                                arrayList=new ArrayList<>();
                            }
                        }

                    }
                   hashMap.put(i,arrayList);
//                    Log.d("str",arrayLists.toString());
                    Log.d("get",hashMap.get(0).toString());
                    adapter=new parentRecycler(StartActivity.this,hashMap);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    @Override
    public void callBack(String text,boolean flag) {

            String a=absentRollNo.getText().toString();
            if(flag)
            {
                a+=text;
                a+=" ";
            }
            else{
                a=a.replace(text+" ","");
            }
            absentRollNo.setText(a);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String uid= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        firebaseDatabase.getReference().child("Teachers Details").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    SUBJECT = Objects.requireNonNull(snapshot.getValue()).toString();
                    String s="Subject : "+SUBJECT;
                    sub.setText(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}