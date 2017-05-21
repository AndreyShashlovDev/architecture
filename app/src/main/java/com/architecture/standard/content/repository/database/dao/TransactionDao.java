package com.architecture.standard.content.repository.database.dao;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.models.Transaction;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TransactionDao {

    @NonNull
    Completable addTransaction(@NonNull Transaction transaction);

    @NonNull
    Single<Collection<Transaction>> addTransactions(@NonNull Collection<Transaction> transactions);

    @NonNull
    Single<List<Transaction>> getAllTransaction();

    @NonNull
    Completable deleteTransaction(int transactionId);

}
