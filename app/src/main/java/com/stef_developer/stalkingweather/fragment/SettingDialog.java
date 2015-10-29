package com.stef_developer.stalkingweather.fragment;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.Spinner;

import com.stef_developer.stalkingweather.activities.MainActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stef_ang on 10/29/2015.
 */
public class SettingDialog extends DialogFragment {

    ISettingDialog listener;
    MainActivity activity;
    EditText etCity;
    Spinner spinner;
    List<Integer> listDay = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

    public interface ISettingDialog {
        void afterSetting(String city, int day);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
        this.listener = (ISettingDialog) activity;
    }
}
