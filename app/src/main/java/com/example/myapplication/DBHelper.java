package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CarApp.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table and column names
    private static final String TABLE_CUSTOMER = "Customerdetails";
    private static final String TABLE_CAR = "CarDetails";


    private static final String COLUMN_USERNAME = "s_username";
    private static final String COLUMN_NAME = "s_name";
    private static final String COLUMN_PHONE = "s_phone";
    private static final String COLUMN_PASSWORD = "s_pass";

    // CarDetails table columns
    private static final String COLUMN_RC = "c_rc";
    private static final String COLUMN_CAR_NAME = "c_carname";
    private static final String COLUMN_OWNER = "c_owner";
    private static final String COLUMN_MODEL = "c_model";
    private static final String COLUMN_EXPIRY = "c_expiry";
    private static final String COLUMN_COLOR = "c_color";
    private static final String COLUMN_FUEL = "c_fuel";
    private static final String COLUMN_CHASSIS = "c_chassis";
    private static final String COLUMN_USERNAME_FK = "c_username";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the Customerdetails table
        String createCustomerTable = "CREATE TABLE " + TABLE_CUSTOMER + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " REAL, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createCustomerTable);

        // Create the CarDetails table with a foreign key relationship
        String createCarTable = "CREATE TABLE " + TABLE_CAR + " (" +
                COLUMN_RC + " TEXT PRIMARY KEY, " +
                COLUMN_CAR_NAME + " TEXT, " +
                COLUMN_OWNER + " TEXT, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_EXPIRY + " TEXT, " +
                COLUMN_COLOR + " TEXT, " +
                COLUMN_FUEL + " TEXT, " +
                COLUMN_CHASSIS + " TEXT, " +
                COLUMN_USERNAME_FK + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_USERNAME_FK + ") REFERENCES " +
                TABLE_CUSTOMER + "(" + COLUMN_USERNAME + "))";
        db.execSQL(createCarTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables if they exist and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    public long addUser(String username, String name, int phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_PASSWORD, password);
        return db.insert(TABLE_CUSTOMER, null, values);
    }

    public long insertCarDetails( String carName, String rc, String chassis, String fuel, String color, String model, String expiry, String owner) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RC, rc);
        values.put(COLUMN_CAR_NAME, carName);
        values.put(COLUMN_OWNER, owner);
        values.put(COLUMN_MODEL, model);
        values.put(COLUMN_EXPIRY, expiry);
        values.put(COLUMN_COLOR, color);
        values.put(COLUMN_FUEL, fuel);
        values.put(COLUMN_CHASSIS, chassis);
        return db.insert(TABLE_CAR, null, values);
    }

    public Cursor getCarDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CAR;
        return db.rawQuery(query, null);
    }



    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"s_username", "s_name", "s_phone"};
        String selection = "s_username=? and s_pass=?";
        String[] seletionArga = {username, password};
        Cursor cursor = db.query("Customerdetails", columns, selection, seletionArga, null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }

}
    
    
    
    
