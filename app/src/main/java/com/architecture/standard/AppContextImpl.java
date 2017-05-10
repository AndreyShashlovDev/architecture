package com.architecture.standard;

import android.content.Context;
import android.support.annotation.NonNull;

import com.architecture.standard.content.managers.data.DataManager;
import com.architecture.standard.content.managers.data.DataManagerFactory;

/* package */ class AppContextImpl implements AppContext {

    @NonNull private final DataManager mDataManager;

    /* package */ AppContextImpl(@NonNull final Context context) {
        mDataManager = new DataManagerFactory(context).createDefault();
    }

    @NonNull
    @Override
    public DataManager getDataManager() {
        return mDataManager;
    }

}
