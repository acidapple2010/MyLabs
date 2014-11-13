package com.example.myapp_01;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import com.nitrobubbles.AndroidAxeLib.Internet.AsyncDataFetcher;
import com.nitrobubbles.AndroidAxeLib.Internet.DataFetcherInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 13.11.14.
 */
public class Weather extends Activity {
    ArrayList list = new ArrayList();
    String[] url = {
            "http://api.openweathermap.org/data/2.5/find?lat=55.75222&lon=37.61556&cnt=1",
            "http://api.openweathermap.org/data/2.5/find?lat=48.85341&lon=2.3488&cnt=1",
            "http://api.openweathermap.org/data/2.5/find?lat=51.50853&lon=-0.12574&cnt=1" };

    private String city;
    private double temp;
    private double humidity;
    String[] array = new String[]{city + " " + temp + " " + humidity};

    public void WeatherFetcher() {
        for(int i=0; i<url.length; i++) {
            AsyncDataFetcher dataFetcher1 = new AsyncDataFetcher(url[i], new DataFetcherInterface<JSONObject>() {
                @Override
                public void startLoading() {
                }

                @Override
                public void result(JSONObject echo) {
                    Log.e("Echo", echo.toString());
                    try {
                        JSONArray listArray = echo.getJSONArray("list");
                        for (int i = 0; i < listArray.length(); i++) {
                            JSONObject listA = listArray.getJSONObject(i);
                            //город
                            city = listA.getString("name");
                            //давление и температура
                            JSONObject main = listA.getJSONObject("main");
                            humidity = main.getDouble("humidity");
                            temp = (main.getDouble("temp") - 273.0);

                            //Collections.addAll(list, array);
                            //list.add(array);
                            //list.add(city + " " + temp + " " + humidity);
                            //Toast.makeText(Weather.this, Arrays.toString(array), Toast.LENGTH_SHORT).show();
                            Toast.makeText(Weather.this, String.valueOf(city + " " + temp + " " + humidity), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void finishLoading() {
                }

                @Override
                public void error(String error) {
                }
            });
            dataFetcher1.execute();

        }
    }

    //ArrayList<Weather> weatherArray = new ArrayList<Weather>();
    /*
    public Weather(String city, double temp, double humidity) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
    }
    */
}
