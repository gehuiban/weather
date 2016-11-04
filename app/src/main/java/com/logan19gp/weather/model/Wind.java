package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Wind implements Parcelable {

    public static final String SPEED = "speed";
    public static final String DEG = "deg";
    private Double speed;
    private Double deg;

    protected Wind(Parcel in) {
        setSpeed((Double) in.readValue(Double.class.getClassLoader()));
        setDeg((Double) in.readValue(Double.class.getClassLoader()));
    }

    public Wind(JSONObject windObj) {
        try {
            if (windObj.has(SPEED)) {
                setSpeed(windObj.getDouble(SPEED));
            }
            if (windObj.has(DEG)) {
                setDeg(windObj.getDouble(DEG));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(speed);
        parcel.writeValue(deg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    public Double getSpeed() {
        return speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }
}