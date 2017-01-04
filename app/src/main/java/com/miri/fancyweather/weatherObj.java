package com.miri.fancyweather;

/**
 * Created by miri on 17.12.16.
 */

public class weatherObj {

    private final String weather , temperature, location;

    public weatherObj(String weather , String temperature , String location) {
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
