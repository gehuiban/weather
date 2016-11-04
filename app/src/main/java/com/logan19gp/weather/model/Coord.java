package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Coord implements Parcelable {
    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };

    public static final String LON = "lon";
    public static final String LAT = "lat";
    private Double lon;
    private Double lat;

    public Coord(JSONObject coordObj) {
        try {
            if (coordObj.has(LON)) {
                setLon(coordObj.getDouble(LON));
            }
            if (coordObj.has(LAT)) {
                setLat(coordObj.getDouble(LAT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Coord(Parcel in) {
        setLon((Double) in.readValue(Double.class.getClassLoader()));
        setLat((Double) in.readValue(Double.class.getClassLoader()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(lon);
        parcel.writeValue(lat);
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}