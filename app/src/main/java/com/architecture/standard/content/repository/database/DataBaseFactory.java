package com.architecture.standard.content.repository.database;

import android.content.Context;
import android.support.annotation.NonNull;

public class DataBaseFactory {

    @NonNull private final Context mContext;

    public DataBaseFactory(@NonNull final Context context) {
        mContext = context;
    }

    public DataBase createDefault() {
        return new DataBaseImpl(mContext);
    }

}
