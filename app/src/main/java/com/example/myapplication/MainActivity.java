package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.HashMap;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private ListView listView;
    private ListView listView2;

    void updatephoto(ImageView imageviews, int i){
        HashMap<String, String> weather_code = TemperatureInfo.temperatureList.get(i);
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code0")){
            imageviews.setImageResource(R.drawable.image1);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code1")){
            imageviews.setImageResource(R.drawable.image1);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code2")){
            imageviews.setImageResource(R.drawable.image2);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code3")){
            imageviews.setImageResource(R.drawable.image2);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code45")){
            imageviews.setImageResource(R.drawable.image8);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code48")){
            imageviews.setImageResource(R.drawable.image8);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code51")){
            imageviews.setImageResource(R.drawable.image5);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code53")){
            imageviews.setImageResource(R.drawable.image5);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code55")){
            imageviews.setImageResource(R.drawable.image5);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code56")){
            imageviews.setImageResource(R.drawable.image5);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code57")){
            imageviews.setImageResource(R.drawable.image5);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code61")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code63")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code65")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code66")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code67")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code71")){
            imageviews.setImageResource(R.drawable.image7);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code73")){
            imageviews.setImageResource(R.drawable.image7);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code75")){
            imageviews.setImageResource(R.drawable.image7);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code77")){
            imageviews.setImageResource(R.drawable.image7);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code80")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code81")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code82")){
            imageviews.setImageResource(R.drawable.image4);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code85")){
            imageviews.setImageResource(R.drawable.image7);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code86")){
            imageviews.setImageResource(R.drawable.image7);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code95")){
            imageviews.setImageResource(R.drawable.image6);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code96")){
            imageviews.setImageResource(R.drawable.image6);}
        if(Objects.equals(weather_code.get(TemperatureInfo.DWC), "Weather code99")){
            imageviews.setImageResource(R.drawable.image6);}

    }
    void time_visible() {
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now= LocalDateTime.now();
        TextView today = findViewById(R.id.todate);
        today.setText(dtf.format(now));
        TextView timer = findViewById(R.id.timer);
        int hr = new Date().getHours();
        int mn = new Date().getMinutes();
        String minutes;
        String hours;
        String current = "";
        if (mn<10) {
            minutes = "0"+mn;
        } else {
            minutes = "" + mn;
        }
        if (hr<10) {
            hours = "0"+hr;
        } else {
            hours = ""+hr;
        }
        current = hours + ":" + minutes;
        timer.setText(current);
        timer.setVisibility(View.VISIBLE);
        today.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timer = findViewById(R.id.timer);
        TextView today = findViewById(R.id.todate);
        time_visible();
        listView2 = findViewById(R.id.listview2);
        JsonHandlerThread jsonHandlerThread = new JsonHandlerThread();
        jsonHandlerThread.start();
        try {
            jsonHandlerThread.join();
            SimpleAdapter adapter2 = new SimpleAdapter(
                    this,
                    TemperatureInfo.hourlytemperatureList,
                    R.layout.list_view_layout2,
                    new String[]{TemperatureInfo.HT, TemperatureInfo.HTEM},
                    new int[]{R.id.hourly_time, R.id.hourly_tem}
            );

            listView2.setAdapter(adapter2);

        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException:" + e.getMessage());
        }
        listView = findViewById(R.id.listview);
        try {
            jsonHandlerThread.join();
            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    TemperatureInfo.temperatureList,
                    R.layout.list_view_layout,
                    new String[]{TemperatureInfo.DTIME,TemperatureInfo.DMAX, TemperatureInfo.DMIN, TemperatureInfo.DWC},
                    new int[]{R.id.daily_time, R.id.daily_max, R.id.daily_min}
            );
            ImageView imageview1 = findViewById(R.id.image1);
            updatephoto(imageview1,0);
            ImageView imageview2 = findViewById(R.id.image2);
            updatephoto(imageview2,1);
            ImageView imageview3 = findViewById(R.id.image3);
            updatephoto(imageview3,2);
            ImageView imageview4 = findViewById(R.id.image4);
            updatephoto(imageview4,3);
            ImageView imageview5 = findViewById(R.id.image5);
            updatephoto(imageview5,4);
            ImageView imageview6 = findViewById(R.id.image6);
            updatephoto(imageview6,5);
            ImageView imageview7 = findViewById(R.id.image7);
            updatephoto(imageview7,6);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HashMap<String, String> temperature = TemperatureInfo.temperatureList.get(position);
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            String str_code= temperature.get(TemperatureInfo.DWC);

                            builder.setTitle(str_code);
                            builder.setMessage("World Meteorological Organization (WMO) codes");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
            );
        }catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException:" + e.getMessage());
        }



        final Button backbutton = findViewById(R.id.backbutton);
        final Button dailybutton = findViewById(R.id.dailybutton);
        final Button hourlybutton = findViewById(R.id.hourlybutton);
        final ImageView image1= findViewById(R.id.image1);
        final ImageView image2= findViewById(R.id.image2);
        final ImageView image3= findViewById(R.id.image3);
        final ImageView image4= findViewById(R.id.image4);
        final ImageView image5= findViewById(R.id.image5);
        final ImageView image6= findViewById(R.id.image6);
        final ImageView image7= findViewById(R.id.image7);

        //back
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                dailybutton.setVisibility(View.VISIBLE);
                hourlybutton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.GONE);
                listView2.setVisibility(View.GONE);
                image1.setVisibility(View.GONE);
                image2.setVisibility(View.GONE);
                image3.setVisibility(View.GONE);
                image4.setVisibility(View.GONE);
                image5.setVisibility(View.GONE);
                image6.setVisibility(View.GONE);
                image7.setVisibility(View.GONE);
                time_visible();
            }
        });

        //daily
        dailybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                dailybutton.setVisibility(View.GONE);
                hourlybutton.setVisibility(View.GONE);
                backbutton.setVisibility(View.VISIBLE);
                timer.setVisibility(View.GONE);
                today.setVisibility(View.GONE);
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);
                image4.setVisibility(View.VISIBLE);
                image5.setVisibility(View.VISIBLE);
                image6.setVisibility(View.VISIBLE);
                image7.setVisibility(View.VISIBLE);
            }
        });

        //hourly
        hourlybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView2.setVisibility(View.VISIBLE);
                dailybutton.setVisibility(View.GONE);
                hourlybutton.setVisibility(View.GONE);
                backbutton.setVisibility(View.VISIBLE);
                timer.setVisibility(View.GONE);
                today.setVisibility(View.GONE);
            }
        });
    }
}

