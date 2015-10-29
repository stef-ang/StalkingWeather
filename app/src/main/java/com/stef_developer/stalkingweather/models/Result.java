package com.stef_developer.stalkingweather.models;

import java.util.List;

/**
 * Created by stef_ang on 10/30/2015.
 */
public class Result {
    City city;
    String cod;
    Double message;
    int cnt;
    List<DayResult> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<DayResult> getList() {
        return list;
    }

    public void setList(List<DayResult> list) {
        this.list = list;
    }
}
