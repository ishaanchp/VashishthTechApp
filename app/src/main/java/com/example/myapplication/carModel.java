package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/*public class carModel implements Parcelable {
    String car_name, model, rc_number, owner, chassis, expiry, color, fuel_type, user;

    String[] columns = new String[]{"c_rc", "c_carname", "c_owner", "c_model", "c_expiry", "c_color", "c_fuel", "c_chassis", "c_username"};

    public carModel(String rcNumber, String carName, String model, String owner_name, String set_expiry, String set_color, String fuelType, String chassis_no, String username) {
        this.model = model;
        this.car_name = carName;
        this.rc_number = rcNumber;
        this.owner = owner_name;
        this.chassis = chassis_no;
        this.expiry = set_expiry;
        this.color = set_color;
        this.fuel_type = fuelType;
        this.user = username;
    }

    protected carModel(Parcel in) {
        car_name = in.readString();
        model = in.readString();
        rc_number = in.readString();
        owner = in.readString();
        chassis = in.readString();
        expiry = in.readString();
        color = in.readString();
        fuel_type = in.readString();
    }

    public static final Creator<carModel> CREATOR = new Creator<carModel>() {
        @Override
        public carModel createFromParcel(Parcel in) {
            return new carModel(in);
        }

        @Override
        public carModel[] newArray(int size) {
            return new carModel[size];
        }
    };
*/
    public class carModel implements Parcelable{
        public String car_name;
        public String rc_number;
        public String model;
        public String owner;
        public String chassis;
        public String expiry;
        public String fuel_type;
        public String color;

        public carModel(String car_name, String rc_number, String model, String owner, String chassis, String expiry, String fuel_type, String color) {
            this.car_name = car_name;
            this.rc_number = rc_number;
            this.model = model;
            this.owner = owner;
            this.chassis = chassis;
            this.expiry = expiry;
            this.fuel_type = fuel_type;
            this.color = color;
        }

        // Getter and setter methods for your fields



    protected carModel(Parcel in) {
        car_name = in.readString();
        model = in.readString();
        rc_number = in.readString();
        owner = in.readString();
        chassis = in.readString();
        expiry = in.readString();
        color = in.readString();
        fuel_type = in.readString();
      /*  user = in.readString();
        columns = in.createStringArray();*/
    }

    public static final Creator<carModel> CREATOR = new Creator<carModel>() {
        @Override
        public carModel createFromParcel(Parcel in) {
            return new carModel(in);
        }

        @Override
        public carModel[] newArray(int size) {
            return new carModel[size];
        }
    };

    public String getModel() {
        return model;
    }

    public String getRc_number() {
        return rc_number;
    }

    public String getOwner() {
        return owner;
    }

    public String getChassis() {
        return chassis;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getColor() {
        return color;
    }

    public String getFuelType() {
        return fuel_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(car_name);
        dest.writeString(model);
        dest.writeString(rc_number);
        dest.writeString(owner);
        dest.writeString(chassis);
        dest.writeString(expiry);
        dest.writeString(color);
        dest.writeString(fuel_type);
    }
}