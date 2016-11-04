package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Temperature implements Parcelable {

    public static final String DAY = "day";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String NIGHT = "night";
    public static final String EVE = "eve";
    public static final String MORN = "morn";
    private Double day;
    private Double min;
    private Double max;
    private Double night;
    private Double eve;
    private Double morn;

    protected Temperature(Parcel in) {
        setDay((Double) in.readValue(Double.class.getClassLoader()));
        setMin((Double) in.readValue(Double.class.getClassLoader()));
        setMax( (Double) in.readValue(Double.class.getClassLoader()));
        setNight((Double) in.readValue(Double.class.getClassLoader()));
        setEve((Double) in.readValue(Double.class.getClassLoader()));
        setMorn((Double) in.readValue(Double.class.getClassLoader()));
    }

    public Temperature(JSONObject temperatureObject) {
        try {
            if (temperatureObject.has(DAY)) {
                setDay(temperatureObject.getDouble(DAY));
            }
            if (temperatureObject.has(MIN)) {
                setMin(temperatureObject.getDouble(MIN));
            }
            if (temperatureObject.has(MAX)) {
                setMax(temperatureObject.getDouble(MAX));
            }
            if (temperatureObject.has(NIGHT)) {
                setNight(temperatureObject.getDouble(NIGHT));
            }
            if (temperatureObject.has(EVE)) {
                setEve(temperatureObject.getDouble(EVE));
            }
            if (temperatureObject.has(MORN)) {
                setMorn(temperatureObject.getDouble(MORN));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(day);
        parcel.writeValue(min);
        parcel.writeValue(max);
        parcel.writeValue(night);
        parcel.writeValue(eve);
        parcel.writeValue(morn);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Temperature> CREATOR = new Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };

    public Double getDay() {
        return day;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getNight() {
        return night;
    }

    public Double getEve() {
        return eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }
}
