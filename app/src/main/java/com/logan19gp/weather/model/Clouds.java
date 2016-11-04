package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Clouds  implements Parcelable {

    public static final String ALL = "all";
    private String all;

    protected Clouds(Parcel in) {
        all = in.readString();
    }

    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

    public Clouds(JSONObject rainObject) {
        try {
            if (rainObject.has(ALL)) {
                setAll(rainObject.getString(ALL));
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
        parcel.writeString(all);
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
