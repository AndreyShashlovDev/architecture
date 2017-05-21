package com.architecture.standard.content.managers.data;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.TransactionRepository;

public interface DataManager {

    @NonNull
    TransactionRepository getTransactions();

}
