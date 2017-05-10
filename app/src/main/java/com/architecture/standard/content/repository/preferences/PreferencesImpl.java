package com.architecture.standard.content.repository.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import lombok.NonNull;

/* package */ class PreferencesImpl implements Preferences {

    private final SharedPreferences mPreferences;
    private final Gson mGson;

    /* package */ PreferencesImpl(@NonNull final Context context) {
        mPreferences = android.preference.PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        mGson = new GsonBuilder().create();
    }

    private void put(@NonNull final String key, @NonNull final List<Long> set) {
        if (mPreferences.contains(key)) {
            mPreferences.edit()
                        .remove(key)
                        .apply();
        }

        final String value = mGson.toJson(set);
        mPreferences.edit()
                    .putString(key, value)
                    .apply();
    }

    private void put(@NonNull final String key, final int val) {
        mPreferences.edit()
                    .putInt(key, val)
                    .apply();
    }

    private void put(@NonNull final String key, @NonNull final String val) {
        mPreferences.edit()
                    .putString(key, val)
                    .apply();
    }

    private void put(@NonNull final String key, final boolean val) {
        mPreferences.edit()
                    .putBoolean(key, val)
                    .apply();
    }

    private void remove(String key) {
        mPreferences.edit()
                    .remove(key)
                    .apply();
    }

}
