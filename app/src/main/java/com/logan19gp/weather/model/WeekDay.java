package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 11/3/2016.
 */
public class WeekDay implements Parcelable {

    public static final String DT = "dt";
    public static final String TEMP = "temp";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String WEATHER = "weather";
    public static final String SPEED = "speed";
    public static final String DEG = "deg";
    public static final String CLOUDS = "clouds";

    private Long dt;
    private Temperature temp;
    private Double pressure;
    private Double humidity;
    private List<Weather> weather;
    private Double speed;
    private Double deg;
    private Double clouds;

    protected WeekDay(Parcel in) {
        setDt((Long) in.readValue(Long.class.getClassLoader()));
        setTemp((Temperature) in.readParcelable(Temperature.class.getClassLoader()));
        setPressure((Double) in.readValue(Double.class.getClassLoader()));
        setHumidity((Double) in.readValue(Double.class.getClassLoader()));
        setWeather(in.createTypedArrayList(Weather.CREATOR));
        setSpeed((Double) in.readValue(Double.class.getClassLoader()));
        setDeg((Double) in.readValue(Double.class.getClassLoader()));
        setClouds((Double) in.readValue(Double.class.getClassLoader()));
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(dt);
        parcel.writeParcelable(temp, flags);
        parcel.writeValue(pressure);
        parcel.writeValue(humidity);
        parcel.writeTypedList(weather);
        parcel.writeValue(speed);
        parcel.writeValue(deg);
        parcel.writeValue(clouds);
    }

    public WeekDay(JSONObject dayObject) {
        try {
            if (dayObject.has(DT)) {
                setDt(dayObject.getLong(DT));
            }
            if (dayObject.has(TEMP)) {
                setTemp(new Temperature(dayObject.getJSONObject(TEMP)));
            }
            if (dayObject.has(PRESSURE)) {
                setPressure(dayObject.getDouble(PRESSURE));
            }
            if (dayObject.has(HUMIDITY)) {
                setHumidity(dayObject.getDouble(HUMIDITY));
            }
            if (dayObject.has(WEATHER)) {
                JSONArray weatherArray = dayObject.getJSONArray(WEATHER);
                List<Weather> listOfWeather = new ArrayList<>();
                for (int id = 0; id < weatherArray.length(); id++) {
                    Weather weather = new Weather(weatherArray.getJSONObject(id));
                    listOfWeather.add(weather);
                }
                setWeather(listOfWeather);
            }
            if (dayObject.has(SPEED)) {
                setSpeed(dayObject.getDouble(SPEED));
            }
            if (dayObject.has(DEG)) {
                setDeg(dayObject.getDouble(DEG));
            }
            if (dayObject.has(CLOUDS)) {
                setClouds(dayObject.getDouble(CLOUDS));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeekDay> CREATOR = new Creator<WeekDay>() {
        @Override
        public WeekDay createFromParcel(Parcel in) {
            return new WeekDay(in);
        }

        @Override
        public WeekDay[] newArray(int size) {
            return new WeekDay[size];
        }
    };

    public Long getDt() {
        return dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public Double getDeg() {
        return deg;
    }

    public Double getClouds() {
        return clouds;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }
}