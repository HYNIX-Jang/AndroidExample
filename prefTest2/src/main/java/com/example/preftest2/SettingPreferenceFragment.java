package com.example.preftest2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences pref;

    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference); //XML 가져오기

        soundPreference = (ListPreference) findPreference("sound_list");
        keywordSoundPreference = (ListPreference) findPreference("keyword_sound_list");
        keywordScreen = (PreferenceScreen) findPreference("keyword_screen");

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!pref.getString("sound_list", "").equals("")) { //null이 아니면
            soundPreference.setSummary(pref.getString("sound_list", "기본")); //SoundList Summary Set
        }
        if (!pref.getString("keyword_sound_list", "").equals("")) { //null이 아니면
            keywordSoundPreference.setSummary(pref.getString("keyword_sound_list", "기본")); //KeywordSoundList Summary Set
        }
        if (pref.getBoolean("keyword", false)) {
            keywordScreen.setSummary("사용");
        }
        pref.registerOnSharedPreferenceChangeListener(prefListener);
    } //onCreate()

    SharedPreferences.OnSharedPreferenceChangeListener prefListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (key.equals("sound_list")) {
                        soundPreference.setSummary(pref.getString("sound_list", "기본"));
                    }
                    if (key.equals("keyword_sound_list")) {
                        keywordSoundPreference.setSummary(pref.getString("keyword_sound_list", "기본"));
                    }
                }
            };

}
