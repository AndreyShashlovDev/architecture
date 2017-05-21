package com.architecture.standard.content.repository.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class DataBaseFactory {

    @NonNull private final Context mContext;

    public DataBaseFactory(@NonNull final Context context) {
        mContext = context;
    }

    public DataBase createDefault() {
        return new DataBaseImpl(mContext);
    }

    public DataBase createEncrypted(@NonNull final String secret) {
        return new DataBaseImpl(mContext, secret);
    }

    public DataBase createDefaultWithMockData() {
        DataBase dataBase = createDefault();
        final List<Transaction> list = new ArrayList<>();
        Transaction transaction;
        for (int i = 0; i < 100; i++) {
            transaction = new Transaction();
            transaction.setId(i);
            list.add(transaction);
        }
        dataBase.getTransactionDao()
                .addTransactions(list)
                .subscribe(collection -> Timber.i("add mock transactions"));

        return dataBase;
    }
}
