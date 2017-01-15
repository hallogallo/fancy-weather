package com.miri.fancyweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by miri on 17.12.16.
 */

//This class creates a new background thread in which the network request is executed

public class NetworkTask extends AsyncTask<URL, Void, WeatherObj> {

    Context mContext;

    //Context is needed in order to use the system connectivity service
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

                    //parse answer to JSON and extract necessary fields
                    JSONObject jsonObject = new JSONObject(responseBuilder.toString());
                    String icon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                    String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    double tempKelvin = jsonObject.getJSONObject("main").getDouble("temp");
                    String location = jsonObject.getString("name");
                    String temp = Long.toString(Math.round(tempKelvin - 273.0));
                    return new WeatherObj(weather, icon , temp, location);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
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
