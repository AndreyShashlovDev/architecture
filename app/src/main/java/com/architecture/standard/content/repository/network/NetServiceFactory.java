package com.architecture.standard.content.repository.network;

public class NetServiceFactory {

    public NetService createDefault() {
        return new NetServiceImpl();
    }

}
