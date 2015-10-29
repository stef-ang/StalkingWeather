package com.stef_developer.stalkingweather.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.stef_developer.stalkingweather.R;
import com.stef_developer.stalkingweather.adapter.WeatherAdapter;
import com.stef_developer.stalkingweather.fragment.SettingDialog;
import com.stef_developer.stalkingweather.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SettingDialog.ISettingDialog {

    public static final String APPID = "61cbaa944c1df9998f3ce3fad6d623a1";

    List<Result> listResult = new ArrayList<>();
    RecyclerView.Adapter adapter;
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

        recyclerView = (RecyclerView) findViewById(R.id.rvWeather);
        recyclerView.setLayoutManager(layoutManager);

        fabInfo = (FloatingActionButton) findViewById(R.id.fabInfo);
        fabSetting = (FloatingActionButton) findViewById(R.id.fabSetting);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        adapter = new WeatherAdapter(listResult);
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

    private class LoadWeather extends AsyncTask<Void, Void, List<Result>> {

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
        protected List<Result> doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Result> objects) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
