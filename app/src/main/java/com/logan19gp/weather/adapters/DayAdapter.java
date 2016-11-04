package com.logan19gp.weather.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logan19gp.weather.FullActivity;
import com.logan19gp.weather.R;
import com.logan19gp.weather.model.WeekDay;
import com.logan19gp.weather.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by george on 11/3/2016.
 */
public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    List<WeekDay> dayItems = new ArrayList<>();
    Activity mActivity;

    public void addDays(List<WeekDay> newDays) {
        dayItems.addAll(newDays);
        notifyDataSetChanged();
    }

    public DayAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final WeekDay day = dayItems.get(position);
        if (day == null) {
            return;
        }
        if (day.getDt() != null) {
            String dayStr = Utils.getDayOfWeek(mActivity, day.getDt() * 1000);
            holder.dayText.setText(dayStr);
        }
        if (day.getWeather() != null && day.getWeather().size() > 0 && day.getWeather().get(0) != null) {
            if(day.getWeather().get(0).getMain() != null) {
                 holder.weatherText.setText(day.getWeather().get(0).getMain());
            }
            if(day.getWeather().get(0).getIcon() != null) {
                holder.weatherImage.setImageResource(Utils.getImageFromCode(day.getWeather().get(0).getIcon(), true));
            }
        }
        if (day.getTemp() != null) {
            if (day.getTemp().getMax() != null) {
                holder.maxText.setText(getTemp(day.getTemp().getMax()));
            }
            if (day.getTemp().getMin() != null) {
                holder.minText.setText(getTemp(day.getTemp().getMin()));
            }
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), FullActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        mActivity, new Pair<>(view.findViewById(R.id.day_text), "title_text"),
                        new Pair<>(view.findViewById(R.id.weather_image), "primary_image"),
                        new Pair<>(view.findViewById(R.id.temp_text), "temp_text"),
                        new Pair<>(view.findViewById(R.id.weather_text), "weather_text"),
                        new Pair<>(view.findViewById(R.id.min_temp_text), "temp_min_text")
                );

                intent.putExtra(FullActivity.EXTRA_DAY, day);
                mActivity.startActivity(intent, options.toBundle());
            }
        });
    }

    private String getTemp(Double temp) {
        return String.format(mActivity.getString(R.string.temp), temp);
    }

    @Override
    public int getItemCount() {
        return dayItems.size();
    }

    public void clearData() {
        dayItems.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView dayText;
        TextView weatherText;
        TextView maxText;
        TextView minText;
        ImageView weatherImage;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));

            container = (LinearLayout) itemView.findViewById(R.id.card_container);
            dayText = (TextView) itemView.findViewById(R.id.day_text);
            weatherText = (TextView) itemView.findViewById(R.id.weather_text);
            maxText = (TextView) itemView.findViewById(R.id.temp_text);
            minText = (TextView) itemView.findViewById(R.id.min_temp_text);
            weatherImage = (ImageView) itemView.findViewById(R.id.weather_image);
        }
    }
}
