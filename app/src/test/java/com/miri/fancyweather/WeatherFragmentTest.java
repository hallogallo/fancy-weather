package com.miri.fancyweather;

import org.junit.Test;

/**
 * Created by miri on 09.01.17.
 */

public class WeatherFragmentTest {

    @Test
    public void testWeatherFragment() throws Exception {
        WeatherFragment TestWeatherFragment = new WeatherFragment();
        WeatherObj dummyObj = new WeatherObj("few clouds" , "11d" , "-1" , "Marzipanien");
        TestWeatherFragment.setWeather(dummyObj);
    }

}
