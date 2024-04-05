package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;


public class DefaulterList extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    ArrayList<String> subjectList=new ArrayList<>();
    HashMap<String,Integer> TotalLecture=new HashMap<>();
    CalendarView calendarView;
    FloatingActionButton sBtn,eBtn;
    float total;
    static float t;
    RecyclerView recyclerView;
    ArrayList<Integer> rollList=new ArrayList<>();
    LinearLayout linearLayout;
    Button show;
    TextView sDate,eDate;
    Date d1,d2,d3;
    DateFormat dateFormat;
    ArrayList<listModel> listModelArrayList=new ArrayList<>();
    HashMap<String,Model> modelHashMap=new HashMap<>();
    ArrayList<String> list=new ArrayList<>();

    HashMap<String,HashMap<String,Integer>> SubjectMap=new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaulter_list);
        calendarView=findViewById(R.id.calender1);
        calendarView.setVisibility(View.GONE);
        show=findViewById(R.id.see);
        sBtn=findViewById(R.id.sBtn);
        eBtn=findViewById(R.id.eBtn);
        sDate=findViewById(R.id.sDate);
        eDate=findViewById(R.id.eDate);
        recyclerView=findViewById(R.id.recyclerview);
        linearLayout=findViewById(R.id.linearlayout);
        linearLayout.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dateFormat = new SimpleDateFormat("d-M-y");
        defaulterAdapter adapter=new defaulterAdapter(DefaulterList.this,listModelArrayList);
        recyclerView.setAdapter(adapter);
         firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child("Information Technology").child("Second Year").child("B").child("Students Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot sn:snapshot.getChildren()){
                        if (sn.exists()){
                            modelHashMap.put(sn.getKey(),sn.getValue(Model.class));
                            rollList.add(Integer.parseInt(sn.getKey().trim()));
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
         sBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 show.setVisibility(View.VISIBLE);
                 calendarView.setVisibility(View.VISIBLE);
                 calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                     @Override
                     public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                         String DATE=i2+"-"+(i1+1)+"-"+i;
                         sDate.setText(DATE);
                         calendarView.setVisibility(View.GONE);
                         if(!eDate.getText().toString().isEmpty()){
                             listModelArrayList.clear();
                             adapter.notifyDataSetChanged();
                             getData();
                         }
                     }
                 });

             }
         });
         eBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                      show.setVisibility(View.VISIBLE);
                     calendarView.setVisibility(View.VISIBLE);
                     calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                         @Override
                         public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                             String DATE = i2 + "-" + (i1 + 1) + "-" + i;
                             eDate.setText(DATE);
                             calendarView.setVisibility(View.GONE);
                             if(!sDate.getText().toString().isEmpty()) {
                                 getData();
                                 listModelArrayList.clear();
                                 adapter.notifyDataSetChanged();
                             }
                         }
                     });


             }
         });

        subjectList.add(0,"CNA");
        subjectList.add(1,"ADBMS");
        subjectList.add(2,"CT");
        subjectList.add(3,"FLAT");
        subjectList.add(4,"SEAD");

//        calculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)

//        });

      show.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                   show.setVisibility(View.GONE);
                  if(!SubjectMap.isEmpty()) {

                      t=total/100;
                      linearLayout.setVisibility(View.VISIBLE);
                      for (int a : rollList) {
                          String s = a + "";
                          listModelArrayList.add(new listModel(s, modelHashMap.get(s).getName(), TotalLecture.get("ADBMS") - SubjectMap.get("ADBMS").getOrDefault(s, 0) , TotalLecture.get("CNA") - SubjectMap.get("CNA").getOrDefault(s, 0) , TotalLecture.get("CT") - SubjectMap.get("CT").getOrDefault(s, 0) , TotalLecture.get("FLAT") - SubjectMap.get("FLAT").getOrDefault(s, 0) , TotalLecture.get("SEAD") - SubjectMap.get("SEAD").getOrDefault(s, 0) ));
                      }

                      adapter.notifyDataSetChanged();
                  }
                  else{
                      Toast.makeText(DefaulterList.this, "Data not found", Toast.LENGTH_SHORT).show();
                  }
              }
      });

    }
    public void getData(){
        String s1=sDate.getText().toString();
        String s2=eDate.getText().toString();
        if(s1.isEmpty() || s2.isEmpty()){
            Toast.makeText(DefaulterList.this, "Select the dates", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                d1 = dateFormat.parse(sDate.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                d2 = dateFormat.parse(eDate.getText().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (d2 != null && d1 != null) {
                if (d1.compareTo(d2) > 0) {
                    Toast.makeText(this, "Starting date must be less than ending date", Toast.LENGTH_SHORT).show();
                    show.setEnabled(false);
                } else {
                    show.setEnabled(true);
                    SubjectMap.clear();
                    TotalLecture.clear();
                    total = 0;
                    for (String SUBJECT : subjectList) {
                        DatabaseReference reference = firebaseDatabase.getReference().child("Information Technology").child("Second Year").child("B").child(SUBJECT);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    int c = 0;
                                    HashMap<String, Integer> hashMap = new HashMap<>();
                                    for (DataSnapshot sn : snapshot.getChildren()) {
                                        if (sn.exists()) {
                                            try {
                                                d3 = dateFormat.parse(Objects.requireNonNull(sn.getKey()));
                                            } catch (ParseException e) {
                                                throw new RuntimeException(e);
                                            }
                                            if (d3.compareTo(d1) >= 0 && d2.compareTo(d3) >= 0) {
                                                c++;
                                                total++;
                                                String p = Objects.requireNonNull(sn.getValue()).toString();
                                                for (String s : p.split(" ")) {
                                                    int i = hashMap.getOrDefault(s, 0);
                                                    hashMap.put(s, i + 1);
                                                }

                                            }
                                        } else {
                                            Toast.makeText(DefaulterList.this, "Data not found", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                    SubjectMap.put(SUBJECT, hashMap);
                                    TotalLecture.put(SUBJECT, c);
                                } else {
                                    Toast.makeText(DefaulterList.this, "Data not Found", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });
                    }


                }
            }else {
                Toast.makeText(DefaulterList.this, "Some error occurred", Toast.LENGTH_SHORT).show();
            }
        }
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