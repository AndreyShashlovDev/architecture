package com.architecture.standard.content.repository.preferences;

import android.content.Context;
import android.support.annotation.NonNull;

public class PreferencesFactory {

    @NonNull private final Context mContext;

    public PreferencesFactory(@NonNull final Context context) {
        mContext = context;
    }

    @NonNull
    public Preferences createDefault() {
        return new PreferencesImpl(mContext);
    }

}
