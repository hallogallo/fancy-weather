package com.miri.fancyweather;

/**
 * Created by miri on 17.12.16.
 */

public class WeatherObj {

    private final String weather , temperature, location;

    public WeatherObj(String weather , String temperature , String location) {
        this.weather = weather;
        this.temperature = temperature;
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLocation() {
        return location;
    }
}
