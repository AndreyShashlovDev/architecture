package com.architecture.standard.content.repository;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.models.Transaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TransactionRepository {

    @NonNull
    Single<List<Transaction>> getAllTransactions();

    @NonNull
    Single<Transaction> createTransaction(@NonNull Transaction transaction);

    @NonNull
    Completable deleteTransaction(int transactionId);

}
