package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Sys implements Parcelable {

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };

    public static final String COUNTRY = "country";
    public static final String SUNRISE = "sunrise";
    public static final String SUNSET = "sunset";
    private String country;
    private Long sunrise;
    private Long sunset;

    public Sys(JSONObject sysObj) {
        try {
            if (sysObj.has(COUNTRY)) {
                setCountry(sysObj.getString(COUNTRY));
            }
            if (sysObj.has(SUNRISE)) {
               setSunrise(sysObj.getLong(SUNRISE));
            }
            if (sysObj.has(SUNSET)) {
                setSunset(sysObj.getLong(SUNSET));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Sys(Parcel in) {
        setCountry(in.readString());
        setSunrise((Long) in.readValue(Long.class.getClassLoader()));
        setSunset((Long) in.readValue(Long.class.getClassLoader()));
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(country);
        parcel.writeValue(sunrise);
        parcel.writeValue(sunset);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCountry() {
        return country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }
}