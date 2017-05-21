package com.architecture.standard.content.repository.network;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.network.api.SomeOtherApi;
import com.architecture.standard.content.repository.network.api.TransactionApi;

public interface NetService {

    @NonNull
    SomeOtherApi getApiService();

    @NonNull
    TransactionApi getApiTransaction();

}
