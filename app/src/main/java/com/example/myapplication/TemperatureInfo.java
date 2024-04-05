package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;

public class TemperatureInfo {
    public static String DTIME= "daily_time";
    public static String DMAX = "daily_max";
    public static String DMIN = "daily_min";
    public static String DWC="daily_weather_code";

    public static String HT = "hourly_time";
    public static String HTEM = "hourly_tem";
    public static ArrayList<HashMap<String, String>>hourlytemperatureList = new ArrayList<>();
    public static ArrayList<HashMap<String, String>>temperatureList = new ArrayList<>();
    public static void addhourlyTemperature (String time1, String hourly_2m) {
        HashMap<String, String> hourlytemperature = new HashMap<>();
        hourlytemperature.put(HT, time1);
        hourlytemperature.put(HTEM, hourly_2m);
        hourlytemperatureList.add(hourlytemperature);
    }
    public static void addTemperature (String date, String daily_max, String daily_min, String daily_weather_code){
        HashMap<String, String> temperature = new HashMap<>();
        temperature.put(DTIME, date);
        temperature.put(DMAX, daily_max);
        temperature.put(DMIN, daily_min);
        temperature.put(DWC, daily_weather_code);
        temperatureList.add(temperature);
    }
    
}
