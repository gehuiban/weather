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
public class CurrentWeather implements Parcelable {
    public static final Creator<CurrentWeather> CREATOR = new Creator<CurrentWeather>() {
        @Override
        public CurrentWeather createFromParcel(Parcel in) {
            return new CurrentWeather(in);
        }

        @Override
        public CurrentWeather[] newArray(int size) {
            return new CurrentWeather[size];
        }
    };

    public static final String COORD = "coord";
    public static final String SYS = "sys";
    public static final String WEATHER = "weather";
    public static final String MAIN = "main";
    public static final String WIND = "wind";
    public static final String RAIN = "rain";
    public static final String CLOUDS = "clouds";
    public static final String DT = "dt";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COD = "cod";

    private Coord coord;
    private Sys sys;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Long dt;
    private Long id;
    private String name;
    private Integer cod;

    public CurrentWeather() {
        this.weather = new ArrayList<>();
    }

    public CurrentWeather(String json) {
        if (json == null || json.length() < 1) {
            Log.e("CurrentWeather", "json is empty");
            return;
        }
        try {
            JSONObject jObj = new JSONObject(json);
            if (jObj.has(COORD)) {
                setCoord(new Coord(jObj.getJSONObject(COORD)));
            }
            if (jObj.has(SYS)) {
                setSys(new Sys(jObj.getJSONObject(SYS)));
            }

            if (jObj.has(WEATHER)) {
                JSONArray weatherArray = jObj.getJSONArray(WEATHER);
                List<Weather> listOfWeather = new ArrayList<>();
                for (int id = 0; id < weatherArray.length(); id++) {
                    Weather weather = new Weather(weatherArray.getJSONObject(id));
                    listOfWeather.add(weather);
                }
                setWeather(listOfWeather);
            }
            if (jObj.has(MAIN)) {
                setMain(new Main(jObj.getJSONObject(MAIN)));
            }
            if (jObj.has(WIND)) {
                setWind(new Wind(jObj.getJSONObject(WIND)));
            }
            if (jObj.has(RAIN)) {
                setRain(new Rain(jObj.getJSONObject(RAIN)));
            }
            if (jObj.has(CLOUDS)) {
                setClouds(new Clouds(jObj.getJSONObject(CLOUDS)));
            }
            if (jObj.has(DT)) {
                setDt(jObj.getLong(DT));
            }
            if (jObj.has(ID)) {
                setId(jObj.getLong(ID));
            }
            if (jObj.has(NAME)) {
                setName(jObj.getString(NAME));
            }
            if (jObj.has(COD)) {
                setCod(jObj.getInt(COD));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CurrentWeather(Parcel in) {
        this();
        setCoord((Coord) in.readParcelable(Coord.class.getClassLoader()));
        setSys((Sys) in.readParcelable(Sys.class.getClassLoader()));
        setWeather(in.createTypedArrayList(Weather.CREATOR));
        setMain((Main) in.readParcelable(Main.class.getClassLoader()));
        setWind((Wind) in.readParcelable(Wind.class.getClassLoader()));
        setRain((Rain) in.readParcelable(Rain.class.getClassLoader()));
        setClouds((Clouds) in.readParcelable(Clouds.class.getClassLoader()));
        setDt((Long) in.readValue(Long.class.getClassLoader()));
        setId((Long) in.readValue(Long.class.getClassLoader()));
        setName(in.readString());
        setCod((Integer) in.readValue(Integer.class.getClassLoader()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(coord, flags);
        parcel.writeParcelable(sys, flags);
        parcel.writeTypedList(weather);
        parcel.writeParcelable(main, flags);
        parcel.writeParcelable(wind, flags);
        parcel.writeParcelable(rain, flags);
        parcel.writeParcelable(clouds, flags);
        parcel.writeValue(dt);
        parcel.writeValue(id);
        parcel.writeString(name);
        parcel.writeValue(cod);
    }

    public Coord getCoord() {
        return coord;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Long getDt() {
        return dt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }
}
