package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SMS extends AppCompatActivity {
    ConstraintLayout layout;
    EditText e_start,e_end,sms;
    String start,end,s;
    Button done;
    FloatingActionButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        layout=findViewById(R.id.cLayout);
        e_start=findViewById(R.id.sTime);
        e_end=findViewById(R.id.eTime);

        send=findViewById(R.id.send);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getCurrentFocus()==e_start || getCurrentFocus()==e_end || getCurrentFocus()==sms)
                {
                    InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end=e_end.getText().toString();
                start=e_start.getText().toString();
                if(end.isEmpty() || start.isEmpty()){
                    Toast.makeText(SMS.this, "Please fill the required details", Toast.LENGTH_SHORT).show();
                }
                else{
                    s = " is absent for "+ShowActivity.SUBJECT+ " lecture at "+start+" - "+end;
                    for(Model model:ShowActivity.modelList){
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(model.getContact(),null,model.getName()+s,null,null);

                    }
                    Toast.makeText(SMS.this, "Message is sent to everyone", Toast.LENGTH_SHORT).show();

                }
                }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}