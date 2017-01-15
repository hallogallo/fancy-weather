package com.miri.fancyweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NetworkTest {

    @Mock
    Context mMockContext;

    @Mock
    ConnectivityManager mockConnectivityManager;

    @Mock
    NetworkInfo mockNetworkInfo;

    @Test
    public void testJSONParsing() throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

        Mockito.when(mMockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager);
        Mockito.when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        Mockito.when(mockNetworkInfo.isConnectedOrConnecting()).thenReturn(true);

        NetworkTask testNetworkTask = new NetworkTask(mMockContext);
        WeatherObj testWeatherObj = testNetworkTask.getWeather(new URL("http://127.0.0.1:8000/test"));

        assertEquals(testWeatherObj.getIcon() , "13d");
        assertEquals(testWeatherObj.getLocation() , "Berlin");
        assertEquals(testWeatherObj.getTemperature() , "0");
        assertEquals(testWeatherObj.getWeather() , "snow");

    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "{\"coord\":{\"lon\":13.41,\"lat\":52.52},\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}," +
                    "{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":273.21,\"pressure\":1015," +
                    "\"humidity\":86,\"temp_min\":273.15,\"temp_max\":274.15},\"visibility\":8000,\"wind\":{\"speed\":3.6,\"deg\":310},\"clouds\":{\"all\":75},\"dt" +
                    "\":1484493600,\"sys\":{\"type\":1,\"id\":4892,\"message\":0.4033,\"country\":\"DE\",\"sunrise\":1484464144,\"sunset\":1484493810},\"id\":2950159, \"name\":\"Berlin\",\"cod\":200}";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


}



/*
*
HTTP/1.1 200 OK
Server: openresty
Date: Sun, 15 Jan 2017 16:03:13 GMT
Content-Type: application/json; charset=utf-8
Content-Length: 496
Connection: keep-alive
X-Cache-Key: /data/2.5/weather?q=berlin,de
Access-Control-Allow-Origin: *
Access-Control-Allow-Credentials: true
Access-Control-Allow-Methods: GET, POST
*
* */

/*.withBody("{\"coord\":{\"lon\":13.41,\"lat\":52.52},\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}," +
                            "{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":273.64,\"pressure\":1015," +
                            "\"humidity\":86,\"temp_min\":273.15,\"temp_max\":274.15},\"visibility\":8000,\"wind\":{\"speed\":3.6,\"deg\":310},\"clouds\":{\"all\":75},\"dt" +
                            "\":1484493600,\"sys\":{\"type\":1,\"id\":4892,\"message\":0.4033,\"country\":\"DE\",\"sunrise\":1484464144,\"sunset\":1484493810},\"id\":2950159, \"name\":\"Berlin\",\"cod\":200}")*/