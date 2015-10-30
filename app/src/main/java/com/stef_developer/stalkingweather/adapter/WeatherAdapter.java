package com.stef_developer.stalkingweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stef_developer.stalkingweather.R;
import com.stef_developer.stalkingweather.models.DayResult;

import java.util.List;

/**
 * Created by stef_ang on 10/29/2015.
 */
public class WeatherAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    String city;
    List<DayResult> items;
    Context context;

    public WeatherAdapter(List<DayResult> items, String city) {
        this.items = items;
        this.city = city;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        context = parent.getContext();
        if (viewType == TYPE_HEADER) {
            v = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
            return new WeatherAdapter.HeaderHolder(v);
        } else if (viewType == TYPE_ITEM) {
            v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new WeatherAdapter.ItemHolder(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ImageLoader imageLoader = ImageLoader.getInstance();
        final DayResult item = items.get(position);

        if (holder instanceof WeatherAdapter.HeaderHolder) {
            HeaderHolder hh = (HeaderHolder) holder;
            hh.tvTitleCity.setText(city);
            // ih.tvToday.setText , timenya berupa millies
            hh.tvTemp1.setText(item.getTemp().getDay() + "°");
            hh.tvTemp2.setText(item.getTemp().getMin() + "°");
            hh.tvTemp3.setText(item.getTemp().getMax() + "°");
//            ih.img.setImageResource();
            hh.tvStatus.setText(item.getWeather().get(0).getMain().toUpperCase());
        } else if (holder instanceof WeatherAdapter.ItemHolder) {
            ItemHolder ih = (ItemHolder) holder;
            //    ih.img.setImageResource();
//            ih.tvDate.setText();  , timenya berupa millies
            ih.tvStatus.setText(item.getWeather().get(0).getMain().toUpperCase());
            ih.tvTemp.setText(item.getTemp().getDay() + "°");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvDate;
        TextView tvStatus;
        TextView tvTemp;

        public ItemHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            tvTemp = (TextView) itemView.findViewById(R.id.tvTemp);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        TextView tvTitleCity;
        TextView tvToday;
        TextView tvTemp1, tvTemp2, tvTemp3;
        ImageView img;
        TextView tvStatus;

        public HeaderHolder(View itemView) {
            super(itemView);

            tvTitleCity = (TextView) itemView.findViewById(R.id.tvTitleCity);
            tvToday = (TextView) itemView.findViewById(R.id.tvToday);
            tvTemp1 = (TextView) itemView.findViewById(R.id.tvTemp1);
            tvTemp2 = (TextView) itemView.findViewById(R.id.tvTemp2);
            tvTemp3 = (TextView) itemView.findViewById(R.id.tvTemp3);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
