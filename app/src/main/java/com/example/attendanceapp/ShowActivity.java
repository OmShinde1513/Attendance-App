package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ShowActivity extends AppCompatActivity {
//    Spinner departmentSpinner, subjectSpinner, classSpinner, divisionSpinner;
    CalendarView calendarView;
    String absentString;
    LinearLayout lDate, lRoll, absString;
    public static String  DEPARTMENT="Information Technology", CLASS="Second Year", DIVISION="B";
    public static String DATE,SUBJECT;
    FloatingActionButton fab;
    RadioGroup radioGroup;
    TextView textView,sub1;

    public static ArrayList<Model> modelList = new ArrayList<>();
    ArrayList<String> absentRoll = new ArrayList<>();
    Button Show;
    public static ArrayList<String> absentList = new ArrayList<>();
    private EditText rollText;
    private TextView ShowDate;
    FirebaseDatabase firebaseDatabase;
    ArrayAdapter<String> subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lDate = findViewById(R.id.lDate);
        lRoll = findViewById(R.id.lRoll);
//        departmentSpinner = findViewById(R.id.departmentSpinner);
//        subjectSpinner = findViewById(R.id.subjectSpinner);
//        classSpinner = findViewById(R.id.classSpinner);
//        divisionSpinner = findViewById(R.id.divisionSpinner);
        calendarView = findViewById(R.id.ShowCalender);
        calendarView.setVisibility(View.GONE);
        fab=findViewById(R.id.fab1);
        ShowDate = findViewById(R.id.ShowDate);
        Show = findViewById(R.id.show);
        radioGroup = findViewById(R.id.radio);
        rollText = findViewById(R.id.e_roll);
        textView = findViewById(R.id.textView);
        sub1=findViewById(R.id.sub1);
        absString = findViewById(R.id.absString);
        absString.setVisibility(View.GONE);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        firebaseDatabase = FirebaseDatabase.getInstance();



//        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.departmentList);
//        departmentSpinner.setAdapter(departmentAdapter);
//        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.divisionList);
//        divisionSpinner.setAdapter(divisionAdapter);
//        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.classList);
//        classSpinner.setAdapter(classAdapter);
//        subjectAdapter = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_spinner_dropdown_item, SelectActivity.subjectList);
//        subjectSpinner.setAdapter(subjectAdapter);
        DateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
        DATE = dateFormat.format(calendarView.getDate());
        ShowDate.setText(DATE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setVisibility(View.VISIBLE);
            }
        });
//        SetDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (SetDate.getText() == "Set Date") {
//                    calendarView.setVisibility(View.VISIBLE);
//                    SetDate.setText("set");
//                } else {
//                    calendarView.setVisibility(View.GONE);
//                    showDate.setText(DATE);
//                    SetDate.setText("Set Date");
//                }
//            }
//        });
        lDate.setVisibility(View.GONE);
        lRoll.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rDate:
                        lDate.setVisibility(View.VISIBLE);
                        lRoll.setVisibility(View.GONE);
                        absString.setVisibility(View.GONE);
                        break;
                    case R.id.rRoll:
                        lRoll.setVisibility(View.VISIBLE);
                        lDate.setVisibility(View.GONE);
                        absString.setVisibility(View.GONE);
                        break;
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                DATE = i2 + "-" + (i1 + 1) + "-" + i;
                ShowDate.setText(DATE);
                calendarView.setVisibility(View.GONE);
            }
        });
//        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    DEPARTMENT = adapterView.getAdapter().getItem(i).toString();
//
//                absString.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    DIVISION = adapterView.getAdapter().getItem(i).toString();
//                absString.setVisibility(View.GONE);
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
//
//                    CLASS = adapterView.getAdapter().getItem(i).toString();
//
//                absString.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                SUBJECT = adapterView.getAdapter().getItem(i).toString();
//                absString.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absentRoll.clear();
                modelList.clear();
                absentList.clear();
                if (SUBJECT != null) {
                    if (radioGroup.getCheckedRadioButtonId() == R.id.rDate && !DATE.isEmpty()) {

                        DatabaseReference databaseReference = firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child(SUBJECT).child(DATE);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String abs = Objects.requireNonNull(snapshot.getValue()).toString();
                                    Collections.addAll(absentRoll, abs.split(" "));
                                    firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child("Students Details").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            int c = 0;
                                            if (snapshot.exists()) {
                                                for (DataSnapshot sn : snapshot.getChildren()) {

                                                    for (String a : absentRoll) {
                                                        if (a.equals(sn.getKey())) {
                                                            modelList.add(sn.getValue(Model.class));
                                                            absentList.add(a);
                                                            c++;
                                                        }
                                                    }
                                                }
                                            }
                                            if (c != 0) {
                                                startActivity(new Intent(ShowActivity.this, DisplayActivity.class));
                                            } else {
                                                Toast.makeText(ShowActivity.this, "Data not Found", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                } else {
                                    Toast.makeText(ShowActivity.this, "Data not Found", Toast.LENGTH_SHORT).show();
                                }

                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });

                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.rRoll && !rollText.getText().toString().isEmpty()) {
                        String s = rollText.getText().toString();
                        firebaseDatabase.getReference().child(DEPARTMENT).child(CLASS).child(DIVISION).child(SUBJECT).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                absentString = "";
                                int c = 0;
                                if (snapshot.exists()) {
                                    for (DataSnapshot sn : snapshot.getChildren()) {
                                        if (Objects.requireNonNull(sn.getValue()).toString().contains(s)) {
                                            absentString += sn.getKey();
                                            absentString += "\n";
                                            c++;
                                        }
                                    }
                                    if (c == 0) {
                                        Toast.makeText(ShowActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                                    } else {
                                        absString.setVisibility(View.VISIBLE);
                                        textView.setText(absentString);
                                    }
                                } else {
                                    Toast.makeText(ShowActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        Toast.makeText(ShowActivity.this, "Choose anyone field", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShowActivity.this, "Please fill the required Details", Toast.LENGTH_SHORT).show();
                }


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
                    sub1.setText(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
