package com.miri.fancyweather;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private String apiKey = "bbaed1871651e959ae40331f73061812";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        WeatherFragment weatherFragment = new WeatherFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, weatherFragment).commit();

        this.setWeather(weatherFragment);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_location) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //get weather data from OpenWeatherMap API and put them into weatherView
    public void setWeather(WeatherFragment weatherFragment) {

        WeatherObj weatherObj;
        WeatherObj errorObj = new WeatherObj("", "", "");

        try {

            URL weatherApiUrl = new URL("http://api.openweathermap.org/data/2.5/weather?q=Berlin,de&appid=bbaed1871651e959ae40331f73061812");
            NetworkTask networkTask = new NetworkTask(this);

            try {

                weatherObj = networkTask.execute(weatherApiUrl).get();

                if (weatherObj != null) {
                    weatherFragment.setWeather(weatherObj);
                } else {
                    Toast.makeText(this, "Network not available!", Toast.LENGTH_LONG).show();
                    weatherFragment.setWeather(errorObj);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
