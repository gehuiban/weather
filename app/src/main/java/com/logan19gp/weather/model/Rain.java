package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Rain  implements Parcelable {
    public static final Creator<Rain> CREATOR = new Creator<Rain>() {
        @Override
        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        @Override
        public Rain[] newArray(int size) {
            return new Rain[size];
        }
    };

    public static final String THREE_HOURS = "3h";
    private Double threeHours;

    protected Rain(Parcel in) {
        setThreeHours((Double) in.readValue(Float.class.getClassLoader()));
    }

    public Rain(JSONObject rainObject) {

        try {
            if (rainObject.has(THREE_HOURS)) {
                setThreeHours(rainObject.getDouble(THREE_HOURS));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(threeHours);
    }

    public Double getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(Double threeHours) {
        this.threeHours = threeHours;
    }
}