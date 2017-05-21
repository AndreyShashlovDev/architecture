package com.architecture.standard.content.repository;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.database.dao.TransactionDao;
import com.architecture.standard.content.repository.network.api.TransactionApi;

public class RepositoryFactory {

    public static TransactionRepository createTransactions(
            @NonNull final TransactionApi transactionApi,
            @NonNull final TransactionDao transactionDao) {
        return new TransactionRepositoryImpl(transactionApi, transactionDao);
    }

}
