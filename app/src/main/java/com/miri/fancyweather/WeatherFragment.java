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

    public void setWeather(WeatherObj weatherObj) {
        this.weather = weatherObj.getWeather();
        this.temperature = weatherObj.getTemperature();
        this.location = weatherObj.getLocation();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View weatherView = inflater.inflate(R.layout.weather_view, container, false);
        Drawable image;
        switch(weather) {
            case "clear sky" :
                image = getResources().getDrawable(R.drawable.sonne);
                break;
            case "few clouds" :
                image = getResources().getDrawable(R.drawable.sonnemitwolken);
                break;
            case "scattered clouds" :
                image = getResources().getDrawable(R.drawable.wolke);
                break;
            case "broken clouds" :
                image = getResources().getDrawable(R.drawable.wolke);
                break;
            case "shower rain" :
                image = getResources().getDrawable(R.drawable.regen);
                break;
            case "rain" :
                image = getResources().getDrawable(R.drawable.regen);
                break;
            case "thunderstorm" :
                image = getResources().getDrawable(R.drawable.jewitta);
                break;
            case "snow" :
                image = getResources().getDrawable(R.drawable.schnee);
                break;
            case "mist" :
                image = getResources().getDrawable(R.drawable.wolke);
                break;
            default:
                image = null;
                break;
        }
        if(image != null) {
            ((ImageView) weatherView.findViewById(R.id.weatherPic)).setImageDrawable(image);
        }
        ((TextView) weatherView.findViewById(R.id.weatherText)).setText(weather);
        ((TextView) weatherView.findViewById(R.id.temperature)).setText(temperature + "°C");
        ((TextView) weatherView.findViewById(R.id.locationName)).setText(location);
        return weatherView;
    }

}
