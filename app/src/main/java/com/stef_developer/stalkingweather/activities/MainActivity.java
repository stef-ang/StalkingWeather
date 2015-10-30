package com.stef_developer.stalkingweather.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stef_developer.stalkingweather.R;
import com.stef_developer.stalkingweather.adapter.WeatherAdapter;
import com.stef_developer.stalkingweather.fragment.SettingDialog;
import com.stef_developer.stalkingweather.models.DayResult;
import com.stef_developer.stalkingweather.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SettingDialog.ISettingDialog {

    public static final String APPID = "61cbaa944c1df9998f3ce3fad6d623a1";

    List<DayResult> listResult = new ArrayList<>();
    WeatherAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    FloatingActionButton fabInfo, fabSetting;

    String city = "Surabaya";
    int day = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvWeather);
        recyclerView.setLayoutManager(layoutManager);

        fabInfo = (FloatingActionButton) findViewById(R.id.fabInfo);
        fabSetting = (FloatingActionButton) findViewById(R.id.fabSetting);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        adapter = new WeatherAdapter(listResult, city.toUpperCase());
        recyclerView.setAdapter(adapter);

        loadContent(city, day);
    }

    private void loadContent(String _city, int _day) {
        new LoadWeather(_city, _day).execute();
    }

    @Override
    public void afterSetting(String _city, int _day) {
        loadContent(_city, _day);
    }

    private class LoadWeather extends AsyncTask<Void, Void, Result> {

        String city;
        int days;

        public LoadWeather(String city, int days) {
            this.city = city;
            this.days = days;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Result doInBackground(Void... params) {
            String url = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&mode=json&units=metric&cnt=%s&APPID=%s",
                    this.city, String.valueOf(this.days), APPID);
            Log.v("check_url", url);
            HttpRequest request = HttpRequest.get(url);
            String result = request.body();
            Log.v("check_result", result);

            return basic().fromJson(result, Result.class);
        }

        @Override
        protected void onPostExecute(Result result) {
            progressBar.setVisibility(View.GONE);
            if (result.getCod().equals("200")) {
                adapter.setCity(result.getCity().getName());
                listResult.addAll(result.getList());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Pencarian cuaca pada kota " + city + " tidak ditemukan", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static Gson basic() {
        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        return gson;
    }
}
