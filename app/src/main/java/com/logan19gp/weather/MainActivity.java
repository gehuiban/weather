package com.logan19gp.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.logan19gp.weather.adapters.DayAdapter;
import com.logan19gp.weather.model.CurrentWeather;
import com.logan19gp.weather.model.Forecast;
import com.logan19gp.weather.network.HttpClient;
import com.logan19gp.weather.utils.Utils;

/**
 * Created by george on 11/3/2016.
 */
public class MainActivity extends AppCompatActivity {

    public static final String DAYS_DATA = "DAYS_DATA";
    public static final String FORECAST_DATA = "FORECAST_DATA";
    public static final String LAST_GET = "LAST_GET";
    public static final String PREFS_FILE = "PREFS_FILE";
    public static final String API_KEY = "yourKey";
    public static final Long SIX_HOURS_MS = 6 * 60 * 60 * 1000l;

    private CurrentWeather currentWeather;
    private Forecast forecast;
    private DayAdapter dayAdapter;
    private RecyclerView recyclerView;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private View appBar;
    private TextView dayText;
    private TextView dateText;
    private TextView tempText;
    private TextView minTempText;
    private TextView weatherText;
    private ImageView weatherImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appBar = findViewById(R.id.appbar);
        dayText = (TextView) findViewById(R.id.day_text);
        dateText = (TextView) findViewById(R.id.date_text);
        tempText = (TextView) findViewById(R.id.temp_text);
        minTempText = (TextView) findViewById(R.id.min_temp_text);
        weatherText = (TextView) findViewById(R.id.weather_text);
        weatherImg = (ImageView) findViewById(R.id.weather_image);

        prefs = getSharedPreferences(MainActivity.PREFS_FILE, Context.MODE_PRIVATE);
        editor = prefs.edit();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dayAdapter = new DayAdapter(this);
        recyclerView.setAdapter(dayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String dayData = prefs.getString(FORECAST_DATA, null);
        String daysData = prefs.getString(DAYS_DATA, null);
        Long lastGet = prefs.getLong(LAST_GET, 0);
        Long hoursAgo = System.currentTimeMillis() - SIX_HOURS_MS;

        if (currentWeather == null || lastGet > hoursAgo) {
            if (dayData != null && lastGet > hoursAgo) {
                currentWeather = new CurrentWeather(dayData);
                setToday(currentWeather);
            } else {
                DownloadCurrentWeather currentWeatherTask = new DownloadCurrentWeather();
                currentWeatherTask.execute();
            }
        }
        else {
            setToday(currentWeather);
        }
        if (forecast == null || lastGet > hoursAgo) {
            if (daysData != null && lastGet > hoursAgo) {
                forecast = new Forecast(daysData);
                setForecastViews(forecast);
            } else {
                DownloadWeatherForecast weatherForecastTask = new DownloadWeatherForecast();
                weatherForecastTask.execute();
            }
        }
        else {
            setForecastViews(forecast);
        }
    }

    private void setToday(final CurrentWeather currentWeather) {
        if (currentWeather == null) {
            return;
        }
        if (currentWeather.getDt() != null) {
            dayText.setText(Utils.getDayOfWeek(this, currentWeather.getDt() * 1000));
            String date = ", " + Utils.getDateFormatted(currentWeather.getDt() * 1000);
            dateText.setText(date);
        }

        if (currentWeather.getMain() != null && currentWeather.getMain().getTemp() != null) {
            tempText.setText(String.format(getString(R.string.temp), currentWeather.getMain().getTemp()));
        }
        if (currentWeather.getMain() != null && currentWeather.getMain().getTemp_min() != null) {
            minTempText.setText(String.format(getString(R.string.temp), currentWeather.getMain().getTemp_min()));
        }
        if (currentWeather.getWeather() != null && currentWeather.getWeather().size() > 0) {

            if (currentWeather.getWeather().get(0).getMain() != null) {
                weatherText.setText(currentWeather.getWeather().get(0).getMain());
            }
            if (currentWeather.getWeather().get(0).getIcon() != null) {
                weatherImg.setImageResource(Utils.getImageFromCode(currentWeather.getWeather().get(0).getIcon()));
            }
        }
        appBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FullActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this, new Pair<>(view.findViewById(R.id.weather_text), "primary_image")
                );
                intent.putExtra(FullActivity.EXTRA_DAY, currentWeather);
                MainActivity.this.startActivity(intent, options.toBundle());
            }
        });
    }

    private void setForecastViews(Forecast forecast) {
        if (forecast != null && forecast.getList() != null && forecast.getList().size() > 0) {
            dayAdapter.clearData();
            dayAdapter.addDays(forecast.getList());
            recyclerView.invalidate();
        }
    }

    public class DownloadCurrentWeather extends AsyncTask<String, Void, CurrentWeather> {
        @Override
        protected CurrentWeather doInBackground(String... urls) {
            String json = HttpClient.getWeatherData("data/2.5/weather?q=Atlanta,ga&units=imperial&APPID=" + API_KEY);
            currentWeather = new CurrentWeather(json);

            editor.putLong(LAST_GET, System.currentTimeMillis());
            editor.putString(FORECAST_DATA, json);
            editor.apply();
            return currentWeather;
        }

        @Override
        protected void onPostExecute(CurrentWeather result) {
            setToday(currentWeather);
        }
    }

    private class DownloadWeatherForecast extends AsyncTask<String, Void, Forecast> {
        @Override
        protected Forecast doInBackground(String... urls) {
            String json = HttpClient.getWeatherData("data/2.5/forecast/daily?q=Atlanta,ga&units=imperial&cnt=5&APPID=" + API_KEY);
            forecast = new Forecast(json);

            editor.putLong(LAST_GET, System.currentTimeMillis());
            editor.putString(DAYS_DATA, json);
            editor.apply();
            return forecast;
        }

        @Override
        protected void onPostExecute(Forecast result) {
            setForecastViews(result);
        }
    }
}