package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText name;
    EditText create_password;
    EditText employeee_id;
    EditText confirm_password;
    EditText create_username;
    CheckBox checkBox;
    Button register;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.editTextText);
        employeee_id = findViewById(R.id.editTextPhone);
        create_password = findViewById(R.id.editTextText2);
        confirm_password = findViewById(R.id.editTextText3);
        create_username = findViewById(R.id.editTextTextEmailAddress2) ;
        checkBox = findViewById(R.id.checkBox);
        register = findViewById(R.id.button);
        dbHelper = new DBHelper(this);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    register.setVisibility(View.VISIBLE);
                }
                else{
                    register.setVisibility(View.INVISIBLE);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = name.getText().toString();
                String enteredPhone = employeee_id.getText().toString();
                String enteredPassword = create_password.getText().toString();
                String enteredConfirmPassword = confirm_password.getText().toString();
                String enteredUsername = create_username.getText().toString();
                if (enteredName.isEmpty() || enteredPhone.isEmpty() || enteredPassword.isEmpty() ||
                        enteredConfirmPassword.isEmpty() || enteredUsername.isEmpty()) {
                    Toast.makeText(signup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!enteredPassword.equals(enteredConfirmPassword)) {
                    Toast.makeText(signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    long result = dbHelper.addUser(enteredUsername, enteredName, Integer.parseInt(enteredPhone), enteredPassword);
                    if (result != -1) {
                        // Registration was successful
                        Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Log.d("Debug", "Name: " + enteredName);
                        Intent loginintent = new Intent(signup.this, login.class);
                        startActivity(loginintent);

                    } else {
                        Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });




    }
}