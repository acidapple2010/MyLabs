package com.example.myapp4;

/**
 * Created by mac on 17.11.14.
 */
public class City {
    private int temp;
    private int humidite;
    private String tetle;

    public City(int temp, int humidite, String tetle) {
        this.temp = temp;
        this.humidite = humidite;
        this.tetle = tetle;
    }

    public String getTetle() {
        return tetle;
    }

    public int getHumidite() {
        return humidite;
    }

    public int getTemp() {
        return temp;
    }
}
