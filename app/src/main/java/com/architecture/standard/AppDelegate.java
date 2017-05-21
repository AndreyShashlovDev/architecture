package com.architecture.standard;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.architecture.standard.content.repository.TransactionRepository;
import com.architecture.standard.content.repository.models.Transaction;
import com.architecture.standard.utils.RxUtils;

import java.util.List;

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

        //for test
        final TransactionRepository transactions = mAppContext.getDataManager()
                                                              .getTransactions();
        transactions.getAllTransactions()
                    .compose(RxUtils::async)
                    .subscribe(this::onGetTransactions, this::onError);
        transactions.getAllTransactions()
                    .compose(RxUtils::async)
                    .subscribe(this::onGetTransactions, this::onError);
    }

    private void onGetTransactions(@NonNull final List<Transaction> transactions) {
        Timber.i("count of trans %d", transactions.size());
    }

    private void onError(@NonNull final Throwable throwable) {
        Timber.e(throwable, "getAllTransaction");
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
