package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(this);

    TextView login;
    EditText name;
    EditText password;

    TextView new_user;
    Button signup;
    Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.textView);
        new_user = findViewById(R.id.new_user);
        name = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signup = findViewById(R.id.button8);
        login_button = findViewById(R.id.login);
        dbHelper = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(login.this, signup.class);
                startActivity(i2);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String enteredUsername = name.getText().toString();
               String enteredPassword = password.getText().toString();
               if(enteredUsername.isEmpty() || enteredPassword.isEmpty()){
                   Toast.makeText(login.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
               }else{
                   if(dbHelper.validateUser(enteredUsername,enteredPassword)){
                       Intent next = new Intent(login.this, carlist.class);
                       startActivity(next);
                   }else{
                       Toast.makeText(login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });




    }


}