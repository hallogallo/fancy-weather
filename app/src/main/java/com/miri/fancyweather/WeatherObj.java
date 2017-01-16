package com.miri.fancyweather;

/**
 *  @author Miriam Hüpper
 *  Simple Class to transport weather data in an easier way
 *  created on 17.12.2016
 */
public class WeatherObj {

    private final String weather , icon , temperature, location;

    public WeatherObj(String weather , String icon ,  String temperature , String location) {
        this.weather = weather;
        this.icon = icon;
        this.temperature = temperature;
        this.location = location;
    }

    /**
     * Getter for weather condition
     * @return weather condition
     */
    public String getWeather() {
        return weather;
    }

    /**
     * Getter for icon code
     * @return icon code
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Getter for temperature
     * @return temperaturn in °C
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Getter for location name
     * @return location name
     */
    public String getLocation() {
        return location;
    }
}
