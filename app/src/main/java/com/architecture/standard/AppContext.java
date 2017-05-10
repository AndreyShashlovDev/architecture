package com.architecture.standard;

import android.support.annotation.NonNull;

import com.architecture.standard.content.managers.data.DataManager;

public interface AppContext {

    @NonNull
    DataManager getDataManager();

}
