package com.architecture.standard;

import android.content.Context;
import android.support.annotation.NonNull;

/* package */ class AppContextFactory {

    @NonNull private final Context mContext;

    /* package */ AppContextFactory(@NonNull final Context context) {
        mContext = context;
    }

    /* package */ AppContext createDefault() {
        return new AppContextImpl(mContext);
    }

}
