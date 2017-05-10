package com.architecture.standard;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import butterknife.ButterKnife;
import timber.log.Timber;

public class AppDelegate extends Application {

    private AppContext mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            ButterKnife.setDebug(BuildConfig.DEBUG);
            StrictMode.enableDefaults();
        }

        mAppContext = new AppContextFactory(getApplicationContext()).createDefault();
    }

    @NonNull
    public AppContext getAppContext() {
        return mAppContext;
    }

    @Override
    protected void attachBaseContext(@NonNull final Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
