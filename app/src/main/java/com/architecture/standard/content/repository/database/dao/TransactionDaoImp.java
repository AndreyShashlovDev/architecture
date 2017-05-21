package com.architecture.standard.content.repository.database.dao;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.models.Transaction;

import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;

public class TransactionDaoImp implements TransactionDao {

    @NonNull private final Realm mRealm;

    public TransactionDaoImp(@NonNull final Realm realm) {
        mRealm = realm;
    }

    @NonNull
    @Override
    public Completable addTransaction(@NonNull final Transaction transaction) {
        return Completable.create(completableEmitter -> {
            mRealm.beginTransaction();
            mRealm.copyToRealm(transaction);
            mRealm.commitTransaction();
            completableEmitter.onComplete();
        });
    }

    @NonNull
    @Override
    public Single<Collection<Transaction>> addTransactions(
            @NonNull Collection<Transaction> transactions) {
        return Single.fromCallable(() -> {
            mRealm.beginTransaction();
            mRealm.insertOrUpdate(transactions);
            mRealm.commitTransaction();

            return transactions;
        });
    }


    @NonNull
    @Override
    public Single<List<Transaction>> getAllTransaction() {
        return Single.fromCallable(() -> {
            final RealmResults<Transaction> transactions = mRealm.where(Transaction.class)
                                                                 .findAll();
            return mRealm.copyFromRealm(transactions);
        });
    }

    @NonNull
    @Override
    public Completable deleteTransaction(final int transactionId) {
        return Completable.create(completableEmitter -> {
            mRealm.beginTransaction();
            final Transaction transaction = mRealm.where(Transaction.class)
                                                  .equalTo(Transaction.Columns.ID, transactionId)
                                                  .findFirst();
            if (transaction != null) {
                transaction.deleteFromRealm();
            }

            completableEmitter.onComplete();
        });
    }

}
