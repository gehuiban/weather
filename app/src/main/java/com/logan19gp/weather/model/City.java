package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class City implements Parcelable {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String COORD = "coord";
    public static final String COUNTRY = "country";
    public static final String POPULATION = "population";
    private Integer id;
    private String name;
    private Coord coord;
    private String country;
    private Long population;

    public City(JSONObject cityObject) {
        try {
            if (cityObject.has(ID)) {
                setId(cityObject.getInt(ID));
            }
            if (cityObject.has(NAME)) {
                setName(cityObject.getString(NAME));
            }
            if (cityObject.has(COORD)) {
                setCoord(new Coord(cityObject.getJSONObject(COORD)));
            }
            if (cityObject.has(COUNTRY)) {
               setCountry(cityObject.getString(COUNTRY));
            }
            if (cityObject.has(POPULATION)) {
                setPopulation(cityObject.getLong(POPULATION));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected City(Parcel in) {
        setId((Integer) in.readValue(Integer.class.getClassLoader()));
        setName(in.readString());
        setCoord((Coord) in.readParcelable(Coord.class.getClassLoader()));
        setCountry(in.readString());
        setPopulation((Long) in.readValue(Long.class.getClassLoader()));
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(id);
        parcel.writeString(name);
        parcel.writeParcelable(coord, i);
        parcel.writeString(country);
        parcel.writeValue(population);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}