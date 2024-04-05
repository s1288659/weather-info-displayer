package com.example.myapplication;

import android.health.connect.datatypes.units.Temperature;
import android.provider.Settings;
import android.util.Log;
import android.util.StateSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

public class JsonHandlerThread extends Thread{
    private static final String TAG = "JsonHandlerThread";
    //URL to get contacts JSON file
    private static String jsonUrl="https://api.open-meteo.com/v1/cma?latitude=22.2783&longitude=114.1747&hourly=temperature_2m&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=auto";
    public static String makeRequest(String jurl){
        String response = null;
        try{
            URL url = new URL(jurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //Read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = inputStreamToString(in);

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        System.out.println(response);
        return response;

    }

    private static String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String array;
        try {
            while ((array = reader.readLine()) != null) {
                sb.append(array).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }

    public void run(){
        String temperatureStr = makeRequest(jsonUrl);
        Log.e(TAG, "Response from url:" + temperatureStr);
        if (temperatureStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(temperatureStr);
                JSONObject hourly = jsonObj.getJSONObject("hourly");
                JSONArray time1 = hourly.getJSONArray("time");
                JSONArray hourly_2m = hourly.getJSONArray("temperature_2m");
                JSONObject daily = jsonObj.getJSONObject("daily");
                JSONArray time = daily.getJSONArray("time");
                JSONArray daily_2m_max = daily.getJSONArray("temperature_2m_max");
                JSONArray daily_2m_min = daily.getJSONArray("temperature_2m_min");
                JSONArray code = daily.getJSONArray("weather_code");
                for( int a =0; a<24; a++){
                    String htime = time1.getString(a);
                    String htem = hourly_2m.getString(a);
                    TemperatureInfo.addhourlyTemperature("Time:\t" + htime.substring(11,16), "Temperature:\t"+ htem+ "°C");
                }
                for (int i = 0; i < 8; i++) {
                    //looping through All Temperature
                    String t = time.getString(i);
                    String dmax = daily_2m_max.getString(i);
                    String dmin = daily_2m_min.getString(i);
                    String dwc = code.getString(i);
                    TemperatureInfo.addTemperature("Date:\t" + t, "Max:\t" + dmax + "°C", "Min:\t" + dmin + "°C", "Weather code" + dwc);
                }

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error:" + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

    }

}
