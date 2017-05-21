package com.architecture.standard.content.repository.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.architecture.standard.content.repository.database.dao.TransactionDao;
import com.architecture.standard.content.repository.database.dao.TransactionDaoImp;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/* package */ class DataBaseImpl implements DataBase {

    @NonNull private final TransactionDao mTransactionDao;

    /* package */ DataBaseImpl(@NonNull final Context context) {
        this(context, null);
    }

    /* package */ DataBaseImpl(@NonNull final Context context, @Nullable final String secret) {
        Realm.init(context);
        final RealmConfiguration.Builder builder = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded();

        if (!TextUtils.isEmpty(secret)) {
            builder.encryptionKey(secret.getBytes());
        }

        Realm.setDefaultConfiguration(builder.build());

        final Realm realm = Realm.getDefaultInstance();

        mTransactionDao = new TransactionDaoImp(realm);
    }

    @NonNull
    @Override
    public TransactionDao getTransactionDao() {
        return mTransactionDao;
    }

}
