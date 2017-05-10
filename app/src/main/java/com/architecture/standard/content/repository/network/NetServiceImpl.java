package com.architecture.standard.content.repository.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.architecture.standard.BuildConfig;
import com.architecture.standard.content.repository.network.api.ApiService;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* package */ class NetServiceImpl implements NetService {

    /* package */ NetServiceImpl() {
    }

    @NonNull
    @Override
    public ApiService getApiService() {
        return getServiceAdapter().create(ApiService.class);
    }

    private Retrofit getServiceAdapter() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_SERVICE_URL)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getClient())
                .build();
    }

    private Gson getGson() {
        return new GsonBuilder().create();
    }

    private OkHttpClient getClient() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

        builder.connectTimeout(BuildConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
               .readTimeout(BuildConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
               .writeTimeout(BuildConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        return builder.build();
    }

}
