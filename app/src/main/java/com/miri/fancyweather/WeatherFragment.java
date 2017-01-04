package com.miri.fancyweather;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by miri on 04.12.16.
 */

public class WeatherFragment extends Fragment {
    private String location , temperature , weather;

    public void setWeather(String weather , String temperature , String location) {
        this.weather = weather;
        this.temperature = temperature;
        this.location = location;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View weatherView = inflater.inflate(R.layout.weather_view, container, false);
        Drawable image = getResources().getDrawable(R.drawable.sonne);
        ((ImageView) weatherView.findViewById(R.id.weatherPic)).setImageDrawable(image);
        ((TextView) weatherView.findViewById(R.id.weatherText)).setText(weather);
        ((TextView) weatherView.findViewById(R.id.temperature)).setText(temperature + "°C");
        ((TextView) weatherView.findViewById(R.id.locationName)).setText(location);
        return weatherView;
    }

}
