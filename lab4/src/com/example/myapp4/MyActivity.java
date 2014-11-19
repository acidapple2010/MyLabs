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


        adapter = new ArrayAdapter<String>(this, R.layout.green_textview_item, list);

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
        if (item.getItemId() == R.id.renew) {
            //setList(arrayCity, arrayTemp, arrayHumidity);
            setList();
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    private ArrayList<String> list = new ArrayList<String>();
    private double[] phfi = new double[]{55.75222, 48.85341, 51.50853, 38.89511, 39.9075};
    private double[] alpf = new double[]{37.61556, 2.3488, -0.12574, -77.03637, 116.39723};

    private ArrayList<City> cites = new ArrayList<City>();


    /*
    private String city;
    private int temp;
    private int humidity;

    private ArrayList arrayCity = new ArrayList<String>();
    private ArrayList arrayTemp = new ArrayList<Double>();
    private ArrayList arrayHumidity = new ArrayList<Double>();
    */
    private String url=null;

    private void WeatherFetcher() {
        cites.clear();
        for(int i=0; i<alpf.length; i++) {

            url = "http://api.openweathermap.org/data/2.5/find?lat=" + phfi[i] + "&lon=" + alpf[i] + "&cnt=1";
            AsyncDataFetcher dataFetcher1 = new AsyncDataFetcher(url, new DataFetcherInterface<JSONObject>() {
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
                            JSONObject main = listA.getJSONObject("main");

                            //город
                            City city = new City((int)(main.getDouble("temp") - 273.0),main.getInt("humidity"),listArray.getJSONObject(i).getString("name"));
                            cites.add(city);
                            /*
                            city = listA.getString("name");

                            //влажность и температура
                            JSONObject main = listA.getJSONObject("main");
                            temp = (int)(main.getDouble("temp") - 273.0);
                            humidity = (int)main.getDouble("humidity");
                            arrayCity.add(city);
                            arrayTemp.add(temp);
                            arrayHumidity.add(humidity);
                            */
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

    public void setList(){
    //(ArrayList arrayCity, ArrayList arrayTemp, ArrayList arrayHumidity) {
        list.clear();

        /*
        this.arrayCity = arrayCity;
        this.arrayTemp = arrayTemp;
        this.arrayHumidity = arrayHumidity;
        */
        for(City city : cites ){
            String cityString = String.format("%s t°%d %d", city.getTetle(), city.getTemp(), city.getHumidite());
            list.add(cityString);
        }
        /*
        for (int i = 0; i < arrayCity.size(); i++) {
            list.add(arrayCity.get(i)+"  t°="+arrayTemp.get(i) +"°C;  "+arrayHumidity.get(i)+"%");
        }
        */
    }
}
