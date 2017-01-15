package com.miri.fancyweather;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by miri on 09.01.17.
 */

public class WeatherObjTest {

    @Test
    public void testWeatherObj() throws Exception {
        WeatherObj TestWeatherObj = new WeatherObj("clear sky" , "11d" , "5" , "Berlin");
        assertEquals(TestWeatherObj.getWeather() , "clear sky");
        assertEquals(TestWeatherObj.getIcon() , "11d");
        assertEquals(TestWeatherObj.getTemperature() , "5");
        assertEquals(TestWeatherObj.getLocation() , "Berlin");
    }

}

