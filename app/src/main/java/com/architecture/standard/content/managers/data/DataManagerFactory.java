package com.architecture.standard.content.managers.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.RepositoryFactory;
import com.architecture.standard.content.repository.TransactionRepository;
import com.architecture.standard.content.repository.database.DataBase;
import com.architecture.standard.content.repository.database.DataBaseFactory;
import com.architecture.standard.content.repository.network.NetService;
import com.architecture.standard.content.repository.network.NetServiceFactory;
import com.architecture.standard.content.repository.preferences.Preferences;
import com.architecture.standard.content.repository.preferences.PreferencesFactory;

public class DataManagerFactory {

    @NonNull private final Context mContext;

    public DataManagerFactory(@NonNull final Context context) {
        mContext = context;
    }

    public DataManager createDefault() {
        final DataBase dataBase = new DataBaseFactory(mContext).createDefaultWithMockData();
        final NetService netService = new NetServiceFactory().createDefault();
        final Preferences preferences = new PreferencesFactory(mContext).createDefault();
        final TransactionRepository transactionRepository = RepositoryFactory.createTransactions(
                netService.getApiTransaction(), dataBase.getTransactionDao());

        return new DataManagerIml(transactionRepository);
    }

}
