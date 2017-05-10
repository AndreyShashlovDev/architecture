package com.architecture.standard.content.repository.database;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.database.dao.TransactionDao;

public interface DataBase {

    @NonNull
    TransactionDao getTransactionDao();

}
