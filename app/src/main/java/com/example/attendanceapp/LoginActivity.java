package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
LinearLayout linearLayout;
EditText l_email,l_password;
private FirebaseAuth firebaseAuth;
private String email,password;
Button SignupBtn,LoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       linearLayout=findViewById(R.id.linearlayout);
       l_email=findViewById(R.id.l_email);
       l_password=findViewById(R.id.l_password);
       SignupBtn=findViewById(R.id.signUp);
       LoginBtn=findViewById(R.id.Login);
       firebaseAuth=FirebaseAuth.getInstance();
       linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(getCurrentFocus()==l_email || getCurrentFocus()==l_password){
                   InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                   inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
               }
           }
       });
       SignupBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
           }
       });
       LoginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               email=l_email.getText().toString();
               password=l_password.getText().toString();
               if(email.isEmpty() || password.isEmpty()){
                   Toast.makeText(LoginActivity.this, "Enter the required credentials", Toast.LENGTH_SHORT).show();
               }
               else if(password.length()<6){
                   Toast.makeText(LoginActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
               }
               else {
                   firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                       @Override
                       public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(LoginActivity.this,SelectActivity.class));
                            finish();
                       }
                   });
                   firebaseAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user != null && !user.getUid().isEmpty()) {
            startActivity(new Intent(LoginActivity.this, SelectActivity.class));
            finish();
        }
    }
}