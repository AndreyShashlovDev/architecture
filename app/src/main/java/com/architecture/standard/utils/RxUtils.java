package com.architecture.standard.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class RxUtils {

    public static final Scheduler SCHEDULER_SINGLE_THREAD = Schedulers.from(
            Executors.newSingleThreadExecutor());

    private RxUtils() {

    }

    public static <R> ObservableSource<R> async(@NonNull final Observable<R> observable) {
        return observable.subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread());
    }

    public static <R> SingleSource<R> async(@NonNull final Single<R> observable) {
        return observable.subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread());
    }

    public static <R> SingleSource<R> sync(@NonNull final Single<R> observable) {
        return observable.subscribeOn(SCHEDULER_SINGLE_THREAD)
                         .observeOn(AndroidSchedulers.mainThread());
    }

    public static CompletableSource async(@NonNull final Completable completable) {
        return completable.subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread());
    }

}
