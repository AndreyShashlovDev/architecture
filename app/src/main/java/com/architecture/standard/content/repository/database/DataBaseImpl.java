package com.architecture.standard.content.repository.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.architecture.standard.content.repository.database.dao.TransactionDao;
import com.architecture.standard.content.repository.models.Transaction;

import lombok.SneakyThrows;

/* package */ class DataBaseImpl implements DataBase {

    @NonNull private final DataBaseHelper mDataBaseHelper;
    @Nullable private TransactionDao mTransactionDao;

    /* package */ DataBaseImpl(@NonNull final Context context) {
        mDataBaseHelper = new DataBaseHelper(context, Transaction.class /* array of classes */);
    }

    @SneakyThrows
    @NonNull
    @Override
    public TransactionDao getTransactionDao() {
        if (mTransactionDao == null) {
            mTransactionDao = new TransactionDao(mDataBaseHelper.getConnectionSource());
        }

        return mTransactionDao;
    }

}
