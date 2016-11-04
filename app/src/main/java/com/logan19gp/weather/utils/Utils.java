package com.logan19gp.weather.utils;

import android.content.Context;
import android.text.format.DateUtils;

import com.logan19gp.weather.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by george on 11/3/2016.
 */
public class Utils {
    private static SimpleDateFormat DateFormat = new SimpleDateFormat("MMMM dd");
    private static SimpleDateFormat DayFormat = new SimpleDateFormat("EEEE");

    private static Map<Integer, String> directions = new HashMap<>();
    private static Map<String, Integer> images = new HashMap<>();

    /**
     * @param dateLong
     * @return
     */
    public static String getDateFormatted(Long dateLong) {
        Date date = new Date(dateLong);
        return DateFormat.format(date);
    }

    /**
     * @param context
     * @param dateLong
     * @return
     */
    public static String getDayOfWeek(Context context, Long dateLong) {
        String dayStr = getDayOfWeek(dateLong);
        if (DateUtils.isToday(dateLong)) {
            dayStr = context.getString(R.string.today);
        } else if (DateUtils.isToday(dateLong - 24 * 60 * 60 * 1000)) {
            dayStr = context.getString(R.string.tomorrow);
        }
        return dayStr;
    }

    /**
     * @param dateLong
     * @return
     */
    public static String getDayOfWeek(Long dateLong) {
        Date date = new Date(dateLong);
        return DayFormat.format(date);
    }

    private static Map<Integer, String> getStoredDirections() {
        if (directions.size() > 0) {
            return directions;
        } else {
            directions.put(0, "N");
            directions.put(360, "N");
            directions.put(45, "NE");
            directions.put(90, "E");
            directions.put(135, "SE");
            directions.put(180, "S");
            directions.put(225, "SW");
            directions.put(270, "W");
            directions.put(305, "NW");
            return directions;
        }
    }

    public static Integer getImageFromCode(String imageCode) {
        return getImageFromCode(imageCode, false);
    }

    public static Integer getImageFromCode(String imageCode, boolean isArt) {

        for (String key : getStoredImages().keySet()) {
            if (key.equals(imageCode.replace(isArt ? "n" : "d", isArt ? "d" : "n"))) {
                return images.get(key);
            }
        }
        return R.drawable.background_dummy;
    }

    public static Map<String, Integer> getStoredImages() {
        if (images.size() > 0) {
            return images;
        } else {
            int idSmall = R.drawable.art_clear;
            int idBig = R.drawable.ic_clear;
            images.put("01d", idSmall);
            images.put("01n", idBig);
            images.put("02d", R.drawable.art_light_clouds);
            images.put("02n", R.drawable.ic_light_clouds);
            images.put("03d", R.drawable.art_cloudy);
            images.put("03n", R.drawable.ic_cloudy);
            images.put("04d", R.drawable.art_cloudy);
            images.put("04n", R.drawable.ic_cloudy);
            images.put("09d", idSmall);
            images.put("09n", idBig);
            images.put("10d", idSmall);
            images.put("10n", idBig);
            images.put("11d", idSmall);
            images.put("11n", idBig);
            images.put("13d", idSmall);
            images.put("13n", idBig);
            images.put("50d", idSmall);
            images.put("50n", idBig);
            return images;
        }
    }

    public static String getDirectionFromAngle(Double deg) {
        for (Integer key : getStoredDirections().keySet()) {
            if (key > deg - 22.5 && key < deg + 22.5) {
                return directions.get(key);
            }
        }
        return "";
    }
}
