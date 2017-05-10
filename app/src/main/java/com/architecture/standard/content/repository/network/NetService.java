package com.architecture.standard.content.repository.network;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.network.api.ApiService;

public interface NetService {

    @NonNull
    ApiService getApiService();

}
