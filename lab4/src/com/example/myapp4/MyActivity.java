package com.example.myapp4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.nitrobubbles.AndroidAxeLib.Internet.AsyncDataFetcher;
import com.nitrobubbles.AndroidAxeLib.Internet.DataFetcherInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private ListView listView;
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        WeatherFetcher();
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.renew){
            setList(arrayCity,arrayTemp,arrayHumidity);
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    private ArrayList<String> list = new ArrayList<String>();
    public String[] url = {
            "http://api.openweathermap.org/data/2.5/find?lat=55.75222&lon=37.61556&cnt=1",
            "http://api.openweathermap.org/data/2.5/find?lat=48.85341&lon=2.3488&cnt=1",
            "http://api.openweathermap.org/data/2.5/find?lat=51.50853&lon=-0.12574&cnt=1" };

    private String city;
    private double temp;
    private double humidity;
    private ArrayList arrayCity = new ArrayList<String>();
    private ArrayList arrayTemp = new ArrayList<Double>();
    private ArrayList arrayHumidity = new ArrayList<Double>();

    private void WeatherFetcher() {
        for(String iUrl:url) {
            AsyncDataFetcher dataFetcher1 = new AsyncDataFetcher(iUrl, new DataFetcherInterface<JSONObject>() {
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
                            temp = (main.getDouble("temp") - 273.0);
                            humidity = main.getDouble("humidity");
                            arrayCity.add(city);
                            arrayTemp.add(temp);
                            arrayHumidity.add(humidity);

                            //arrayCTH.add(i, ((String)city+ (Double)temp + (Double)humidity));

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

    public void setList(ArrayList arrayCity, ArrayList arrayTemp, ArrayList arrayHumidity) {
        list.clear();
        this.arrayCity = arrayCity;
        this.arrayTemp = arrayTemp;
        this.arrayHumidity = arrayHumidity;

        for (int i = 0; i < arrayCity.size(); i++) {
            list.add((String) arrayCity.get(i)+"  "+(Double) arrayTemp.get(i)+"  "+ (Double) arrayHumidity.get(i));
        }
    }
}
