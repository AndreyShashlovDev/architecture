package com.architecture.standard.content.repository.network;

import android.support.annotation.NonNull;

public class NetServiceFactory {

    @NonNull
    public NetService createDefault() {
        return new NetServiceImpl();
    }

}
