package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class carModel_current {//implements Parcelable {
    static String car_name, model, rc_number, owner, chassis, expiry, color, fuel_type;

    public static void setCar_name(String c_name) {
        car_name = c_name;
    }

    public static void setModel(String m) {
        model = m;
    }

    public static void setRc_number(String rc_num) {
        rc_number = rc_num;
    }

    public static void setOwner(String own) {
        owner = own;
    }

    public static void setChassis(String c) {
        chassis = c;
    }

    public static void setExpiry(String e) {
        expiry = e;
    }

    public static void setColor(String col) {
        color = col;
    }

    public static void setFuel_type(String fuel) {
        fuel_type = fuel;
    }

    /*public carModel(String carName, String rcNumber, String model, String owner_name, String chasssis_no, String set_expiry, String set_color, String fuelType) {
        this.model = model;
        this.car_name = carName;
        this.rc_number = rcNumber;
        this.owner = owner_name;
        this.chassis = chasssis_no;
        this.expiry = set_expiry;
        this.color = set_color;
        this.fuel_type = fuelType;
    }*/

    /*protected carModel(Parcel in) {
        car_name = in.readString();
        model = in.readString();
        rc_number = in.readString();
        owner = in.readString();
        chassis = in.readString();
        expiry = in.readString();
        color = in.readString();
        fuel_type = in.readString();
    }*/

    /*public static final Creator<carModel> CREATOR = new Creator<carModel>() {
        @Override
        public carModel createFromParcel(Parcel in) {
            return new carModel(in);
        }*/

        /*@Override
        public carModel[] newArray(int size) {
            return new carModel[size];
        }
    };*/


    public static String getModel() {
        return model;
    }

    public static String getRc_number() {
        return rc_number;
    }

    public static String getOwner() {
        return owner;
    }

    public static String getChassis() {
        return chassis;
    }

    public static String getExpiry() {
        return expiry;
    }

    public static String getColor() {
        return color;
    }

    public static String getFuelType() {
        return fuel_type;
    }

}
