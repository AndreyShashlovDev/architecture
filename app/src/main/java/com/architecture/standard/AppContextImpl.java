package com.architecture.standard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.architecture.standard.content.managers.data.DataManager;
import com.architecture.standard.content.managers.data.DataManagerFactory;
import com.architecture.standard.ui.mvp.presenters.PresenterLifecycleHelper;

/* package */ class AppContextImpl implements AppContext {

    @NonNull private final DataManager mDataManager;
    @NonNull private final PresenterLifecycleHelper mLifecycleHelper;

    /* package */ AppContextImpl(@NonNull final Context context) {
        mDataManager = new DataManagerFactory(context).createDefault();
        mLifecycleHelper = new PresenterLifecycleHelper();
    }

    @NonNull
    @Override
    public DataManager getDataManager() {
        return mDataManager;
    }

    public PresenterLifecycleHelper getLifecycleHelper() {
        return mLifecycleHelper;
    }

}
