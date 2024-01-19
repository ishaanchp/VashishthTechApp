package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class carlist extends AppCompatActivity {
    RecyclerView car_list;
    RecyclerAdapter adapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carlist);
        adapter = new RecyclerAdapter(this, new ArrayList<>()); // Initialize the adapter with an empty list
        dbHelper = new DBHelper(this);

        car_list = findViewById(R.id.recycler_view);
        car_list.setAdapter(adapter);
        car_list.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton add = findViewById(R.id.addbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Create a dialog for user input
                    Dialog dialog = new Dialog(carlist.this);
                    dialog.setContentView(R.layout.app_update); // Replace 'dialog_layout' with the XML layout for your dialog

                    EditText edtName = dialog.findViewById(R.id.edtname);
                    EditText editRC = dialog.findViewById(R.id.editrc);
                    EditText editModel = dialog.findViewById(R.id.editmodel);
                    EditText editOwner = dialog.findViewById(R.id.owner);
                    EditText editChassis = dialog.findViewById(R.id.chassis);
                    EditText editExpiry = dialog.findViewById(R.id.Expiry);
                    EditText editColor = dialog.findViewById(R.id.Color);
                    EditText editFuel = dialog.findViewById(R.id.Fueltype);

                    Button btnAction = dialog.findViewById(R.id.btnAction);

                    btnAction.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String carName = edtName.getText().toString();
                            String rcNumber = editRC.getText().toString();
                            String model = editModel.getText().toString();
                            String owner = editOwner.getText().toString();
                            String chassis = editChassis.getText().toString();
                            String expiry = editExpiry.getText().toString();
                            String color = editColor.getText().toString();
                            String fuelType = editFuel.getText().toString();

                            // Insert car details into the database
                            long result = dbHelper.insertCarDetails(carName, rcNumber, chassis, fuelType, color, model, expiry, owner);

                            if (result != -1) {
                                Toast.makeText(carlist.this, "Car details inserted!", Toast.LENGTH_SHORT).show();

                                // Create a new carModel with the entered details
                                carModel newCar = new carModel(carName, rcNumber, model, owner, chassis, expiry, fuelType, color);

                                // Add the new car to the RecyclerView
                              adapter.addCar(newCar);

                                // Close the dialog
                                dialog.dismiss();
                            } else {
                                Toast.makeText(carlist.this, "Error inserting car details", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    dialog.show();
                }
            });

        // Load existing car details from the database
        loadCarDetailsFromDatabase();
    }

    private void loadCarDetailsFromDatabase() {
        Cursor cursor = dbHelper.getCarDetails();

        while (cursor.moveToNext()) {
            String rc_number = cursor.getString(cursor.getColumnIndexOrThrow("c_rc"));
            String car_name = cursor.getString(cursor.getColumnIndexOrThrow("c_carname"));
            String model = cursor.getString(cursor.getColumnIndexOrThrow("c_model"));
            String owner = cursor.getString(cursor.getColumnIndexOrThrow("c_owner"));
            String chassis = cursor.getString(cursor.getColumnIndexOrThrow("c_chassis"));
            String expiry = cursor.getString(cursor.getColumnIndexOrThrow("c_expiry"));
            String fueltype = cursor.getString(cursor.getColumnIndexOrThrow("c_fuel"));
            String color = cursor.getString(cursor.getColumnIndexOrThrow("c_color"));
            carModel car = new carModel(rc_number,car_name, model, owner, chassis, expiry, fueltype, color);
            adapter.addCar(car); // Add car to the RecyclerView
        }
    }
}
