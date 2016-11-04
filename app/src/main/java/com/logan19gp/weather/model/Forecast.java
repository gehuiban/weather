package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 11/3/2016.
 */
public class Forecast  implements Parcelable {

    public static final String CITY = "city";
    public static final String COD = "cod";
    public static final String MESSAGE = "message";
    public static final String CNT = "cnt";
    public static final String LIST = "list";

    private City city;
    private Integer cod;
    private Double message;
    private Integer cnt;
    private List<WeekDay> list;

    public Forecast(String json) {
        if (json == null || json.length() < 1) {
            Log.e("WEATHER_Forecast", "json is empty");
            return;
        }
        try {
            JSONObject jObj = new JSONObject(json);

            if (jObj.has(CITY)) {
                setCity(new City(jObj.getJSONObject(CITY)));
            }
            if (jObj.has(COD)) {
                setCod(jObj.getInt(COD));
            }

            if (jObj.has(MESSAGE)) {
                setMessage(jObj.getDouble(MESSAGE));
            }
            if (jObj.has(CNT)) {
                setCnt(jObj.getInt(CNT));
            }
            if (jObj.has(LIST)) {
                JSONArray weatherArray = jObj.getJSONArray(LIST);
                List<WeekDay> listOfWeather = new ArrayList<>();
                for (int id = 0; id < weatherArray.length(); id++) {
                    WeekDay weekDay = new WeekDay(weatherArray.getJSONObject(id));
                    listOfWeather.add(weekDay);
                }
                setList(listOfWeather);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Forecast(Parcel in) {
        setCity((City) in.readParcelable(City.class.getClassLoader()));
        setCod((Integer) in.readValue(Integer.class.getClassLoader()));
        setMessage((Double) in.readValue(Double.class.getClassLoader()));
        setCnt((Integer) in.readValue(Integer.class.getClassLoader()));
        setList(in.createTypedArrayList(WeekDay.CREATOR));
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(city, i);
        parcel.writeValue(cod);
        parcel.writeValue(message);
        parcel.writeValue(cnt);
        parcel.writeTypedList(list);
    }

    public City getCity() {
        return city;
    }

    public Integer getCod() {
        return cod;
    }

    public Double getMessage() {
        return message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public List<WeekDay> getList() {
        return list;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public void setList(List<WeekDay> list) {
        this.list = list;
    }
}