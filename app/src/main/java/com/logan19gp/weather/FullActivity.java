package com.logan19gp.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.logan19gp.weather.model.CurrentWeather;
import com.logan19gp.weather.model.WeekDay;
import com.logan19gp.weather.utils.Utils;

/**
 * Created by george on 11/3/2016.
 */
public class FullActivity extends AppCompatActivity {

    public static final String EXTRA_DAY = "EXTRA_DAY";

    private TextView dayText;
    private TextView dateText;
    private TextView tempText;
    private TextView minTempText;
    private TextView humidityText;
    private TextView pressureText;
    private TextView windText;
    private TextView weatherText;
    private ImageView weatherImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day);
        dayText = (TextView) findViewById(R.id.today_text);
        dateText = (TextView) findViewById(R.id.date_text);
        tempText = (TextView) findViewById(R.id.temp_text);
        minTempText = (TextView) findViewById(R.id.min_temp_text);
        weatherText = (TextView) findViewById(R.id.weather_text);
        humidityText = (TextView) findViewById(R.id.humidity_text);
        pressureText = (TextView) findViewById(R.id.presure_text);
        windText = (TextView) findViewById(R.id.wind_text);
        weatherImg = (ImageView) findViewById(R.id.weather_image);

        final Intent intent = getIntent();
        Object obj = intent.getParcelableExtra(EXTRA_DAY);
        if (obj instanceof WeekDay) {
            setViews((WeekDay) obj);
        } else if (obj instanceof CurrentWeather) {
            setViews((CurrentWeather) obj);
        }
    }

    private void setViews(WeekDay weekDay) {

        if (weekDay.getDt() != null) {
            dayText.setText(Utils.getDayOfWeek(this, weekDay.getDt() * 1000));
            dateText.setText(Utils.getDateFormatted(weekDay.getDt() * 1000));
        }

        if (weekDay.getTemp() != null) {
            if (weekDay.getTemp().getMax() != null) {
                tempText.setText(String.format(getString(R.string.temp), weekDay.getTemp().getMax()));
            }
            if (weekDay.getTemp().getMin() != null) {
                minTempText.setText(String.format(getString(R.string.temp), weekDay.getTemp().getMin()));
            }
        }

        if (weekDay.getWeather() != null && weekDay.getWeather().size() > 0) {
            if (weekDay.getWeather().get(0).getMain() != null) {
                weatherText.setText(weekDay.getWeather().get(0).getMain());
            }
            if (weekDay.getWeather().get(0).getIcon() != null) {
                weatherImg.setImageResource(Utils.getImageFromCode(weekDay.getWeather().get(0).getIcon()));
            }
        }
        if (weekDay.getHumidity() != null) {
            humidityText.setText(String.format(getString(R.string.humidity_format), weekDay.getHumidity()));
        }
        if (weekDay.getPressure() != null) {
            pressureText.setText(String.format(getString(R.string.pressure_format), weekDay.getPressure()));
        }
        if (weekDay.getSpeed() != null && weekDay.getDeg() != null) {
            windText.setText(String.format(getString(R.string.wind_format), weekDay.getSpeed(), Utils.getDirectionFromAngle(weekDay.getDeg())));
        }
    }

    private void setViews(CurrentWeather currentWeather) {

        if (currentWeather.getDt() != null) {
            dayText.setText(Utils.getDayOfWeek(this, currentWeather.getDt() * 1000));
            dateText.setText(Utils.getDateFormatted(currentWeather.getDt() * 1000));
        }
        if (currentWeather.getMain() != null) {
            if (currentWeather.getMain().getTemp() != null) {
                tempText.setText(String.format(getString(R.string.temp), currentWeather.getMain().getTemp()));
            }
            if (currentWeather.getMain().getTemp_min() != null) {
                minTempText.setText(String.format(getString(R.string.temp), currentWeather.getMain().getTemp_min()));
            }
        }
        if (currentWeather.getWeather() != null && currentWeather.getWeather().size() > 0) {
            if (currentWeather.getWeather().get(0).getMain() != null) {
                weatherText.setText(currentWeather.getWeather().get(0).getMain());
            }
            if (currentWeather.getWeather().get(0).getIcon() != null) {
                weatherImg.setImageResource(Utils.getImageFromCode(currentWeather.getWeather().get(0).getIcon()));
            }
        }
        if (currentWeather.getMain() != null && currentWeather.getMain().getHumidity() != null) {
            humidityText.setText(String.format(getString(R.string.humidity_format), currentWeather.getMain().getHumidity()));
        }
        if (currentWeather.getMain() != null && currentWeather.getMain().getPressure() != null) {
            pressureText.setText(String.format(getString(R.string.pressure_format), currentWeather.getMain().getPressure()));
        }
        if (currentWeather.getWind() != null && currentWeather.getWind().getSpeed() != null && currentWeather.getWind().getDeg() != null) {
            windText.setText(String.format(getString(R.string.wind_format), currentWeather.getWind().getSpeed(),
                    Utils.getDirectionFromAngle(currentWeather.getWind().getDeg())));
        }
    }
}