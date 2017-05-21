package com.architecture.standard.content.repository;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.database.dao.TransactionDao;
import com.architecture.standard.content.repository.models.Transaction;
import com.architecture.standard.content.repository.network.api.TransactionApi;

import java.net.HttpURLConnection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/* package */ class TransactionRepositoryImpl extends AbstractRepository
        implements TransactionRepository {

    @NonNull private final TransactionApi mTransaction;
    @NonNull private final TransactionDao mTransactionDao;

    /* package */ TransactionRepositoryImpl(@NonNull final TransactionApi transactionApi,
                                            @NonNull final TransactionDao transactionDao) {
        mTransaction = transactionApi;
        mTransactionDao = transactionDao;
    }

    @NonNull
    @Override
    public Single<List<Transaction>> getAllTransactions() {
        return mTransaction.getTransactions()
                           .map(this::checkResponseCode)
                           .observeOn(AndroidSchedulers.mainThread())
                           .onErrorReturn(throwable -> mTransactionDao.getAllTransaction()
                                                                      .blockingGet());
    }

    @NonNull
    @Override
    public Single<Transaction> createTransaction(@NonNull final Transaction transaction) {
        return mTransaction.createTransaction(transaction)
                           .map(response -> checkResponseCode(response,
                                   HttpURLConnection.HTTP_CREATED))
                           .observeOn(AndroidSchedulers.mainThread())
                           .doOnSuccess(mTransactionDao::addTransaction);
    }

    @NonNull
    @Override
    public Completable deleteTransaction(final int transactionId) {
        return mTransaction.deleteTransaction(transactionId)
                           .observeOn(AndroidSchedulers.mainThread())
                           .andThen(mTransactionDao.deleteTransaction(transactionId));
    }

}
