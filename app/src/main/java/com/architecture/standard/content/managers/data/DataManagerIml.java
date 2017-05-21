package com.architecture.standard.content.managers.data;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.TransactionRepository;

/* package */ class DataManagerIml implements DataManager {

    @NonNull private final TransactionRepository mTransactionRepository;

    /* package */ DataManagerIml(@NonNull final TransactionRepository transactionRepository) {
        mTransactionRepository = transactionRepository;
    }

    @NonNull
    @Override
    public TransactionRepository getTransactions() {
        return mTransactionRepository;
    }

}
