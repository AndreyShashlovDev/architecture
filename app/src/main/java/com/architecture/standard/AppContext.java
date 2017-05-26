package com.architecture.standard;

import android.support.annotation.NonNull;

import com.architecture.standard.content.managers.data.DataManager;
import com.architecture.standard.ui.mvp.presenters.PresenterLifecycleHelper;

public interface AppContext {

    @NonNull
    DataManager getDataManager();

    @NonNull
    PresenterLifecycleHelper getLifecycleHelper();

}
