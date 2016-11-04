package com.logan19gp.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george on 11/3/2016.
 */
public class Main  implements Parcelable {

    private Double temp;
    private Double humidity;
    private Double pressure;
    private Double temp_min;
    private Double temp_max;
    private Double sea_level;
    private Double grnd_level;
    private Double temp_kf;

    public static final String TEMP = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String PRESURE = "pressure";
    public static final String TEMP_MIN = "temp_min";
    public static final String TEMP_MAX = "temp_max";
    public static final String SEA_LEVEL = "sea_level";
    public static final String GRND_LEVEL = "grnd_level";
    public static final String TEMP_KF = "temp_kf";

    public Main(JSONObject mainObj) {
        try {
            if (mainObj.has(TEMP)) {
                setTemp(mainObj.getDouble(TEMP));
            }
            if (mainObj.has(HUMIDITY)) {
                setHumidity(mainObj.getDouble(HUMIDITY));
            }
            if (mainObj.has(PRESURE)) {
                setPressure(mainObj.getDouble(PRESURE));
            }
            if (mainObj.has(TEMP_MIN)) {
                setTemp_min(mainObj.getDouble(TEMP_MIN));
            }
            if (mainObj.has(TEMP_MAX)) {
                setTemp_max(mainObj.getDouble(TEMP_MAX));
            }
            if (mainObj.has(SEA_LEVEL)) {
                setSea_level(mainObj.getDouble(SEA_LEVEL));
            }
            if (mainObj.has(GRND_LEVEL)) {
                setGrnd_level(mainObj.getDouble(GRND_LEVEL));
            }
            if (mainObj.has(TEMP_KF)) {
                setTemp_kf(mainObj.getDouble(TEMP_KF));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Main(Parcel in) {
        setTemp((Double) in.readValue(Double.class.getClassLoader()));
        setHumidity((Double) in.readValue(Double.class.getClassLoader()));
        setPressure((Double) in.readValue(Double.class.getClassLoader()));
        setTemp_min((Double) in.readValue(Double.class.getClassLoader()));
        setTemp_max((Double) in.readValue(Double.class.getClassLoader()));
        setSea_level((Double) in.readValue(Double.class.getClassLoader()));
        setGrnd_level((Double) in.readValue(Double.class.getClassLoader()));
        setTemp_kf((Double) in.readValue(Double.class.getClassLoader()));
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(temp);
        parcel.writeValue(humidity);
        parcel.writeValue(pressure);
        parcel.writeValue(temp_min);
        parcel.writeValue(temp_max);
        parcel.writeValue(sea_level);
        parcel.writeValue(grnd_level);
        parcel.writeValue(temp_kf);
    }

    public Double getTemp() {
        return temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public Double getSea_level() {
        return sea_level;
    }

    public Double getGrnd_level() {
        return grnd_level;
    }

    public Double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public void setSea_level(Double sea_level) {
        this.sea_level = sea_level;
    }

    public void setGrnd_level(Double grnd_level) {
        this.grnd_level = grnd_level;
    }

    public void setTemp_kf(Double temp_kf) {
        this.temp_kf = temp_kf;
    }
}