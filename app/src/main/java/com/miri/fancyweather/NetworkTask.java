package com.miri.fancyweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * Created by miri on 17.12.16.
 */

// http://api.openweathermap.org/data/2.5/weather?q=Berlin,de&appid=bbaed1871651e959ae40331f73061812

public class NetworkTask extends AsyncTask<URL, Void, WeatherObj> {

    Context mContext;

    public NetworkTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected WeatherObj doInBackground(URL... urls) {
        return this.getWeather(urls[0]);
    }

    public WeatherObj getWeather(URL url) {

        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if ((networkInfo != null) && networkInfo.isConnectedOrConnecting()) {

            try {
                URLConnection urlConnection = url.openConnection();
                InputStream weatherStream = urlConnection.getInputStream();
                BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(weatherStream));
                StringBuilder responseBuilder = new StringBuilder();

                String inputChunk;
                while ((inputChunk = inputStreamReader.readLine()) != null) {
                    responseBuilder.append(inputChunk);
                }


                try {

                    JSONObject jsonObject = new JSONObject(responseBuilder.toString());
                    String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    double tempKelvin = jsonObject.getJSONObject("main").getDouble("temp");
                    String location = jsonObject.getString("name");
                    String temp = Long.toString(Math.round(tempKelvin - 273.0));
                    return new WeatherObj(weather, temp, location);

                } catch (JSONException e) {
                    e.printStackTrace();
                    String blub = responseBuilder.toString();
                    return new WeatherObj("JSON error", "", blub);
                }


            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }
    }

}
