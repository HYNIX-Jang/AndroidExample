package com.example.preftest3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences pref;

    ListPreference listPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference); //XML 가져오기

        listPreference = (ListPreference) findPreference("network_list");

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!pref.getString("network_list", "").equals("")) { //null이 아니면
            listPreference.setSummary(pref.getString("network_list", "LTE(권장)")); //SoundList Summary Set
        }
        pref.registerOnSharedPreferenceChangeListener(prefListener);

    } //onCreate()

    SharedPreferences.OnSharedPreferenceChangeListener prefListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (key.equals("network_list")) {
                        listPreference.setSummary(pref.getString("network_list", "기본"));
                    }
                }
            };

}
