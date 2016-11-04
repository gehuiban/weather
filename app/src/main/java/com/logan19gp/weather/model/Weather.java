package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Weather implements Parcelable {
    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public static final String ID = "id";
    public static final String MAIN = "main";
    public static final String DESCRIPTION = "description";
    public static final String ICON = "icon";

    private Integer id;
    private String main;
    private String description;
    private String icon;

    public Weather(JSONObject sysObj) {
        try {
            if (sysObj.has(ID)) {
                setId(sysObj.getInt(ID));
            }
            if (sysObj.has(MAIN)) {
                setMain(sysObj.getString(MAIN));
            }
            if (sysObj.has(DESCRIPTION)) {
                setDescription(sysObj.getString(DESCRIPTION));
            }
            if (sysObj.has(ICON)) {
                setIcon(sysObj.getString(ICON));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Weather(Parcel in) {
        setId((Integer) in.readValue(Integer.class.getClassLoader()));
        setMain(in.readString());
        setDescription(in.readString());
        setIcon(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(id);
        parcel.writeString(main);
        parcel.writeString(description);
        parcel.writeString(icon);
    }

    public Integer getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}