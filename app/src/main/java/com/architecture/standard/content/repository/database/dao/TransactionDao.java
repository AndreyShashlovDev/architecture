package com.architecture.standard.content.repository.database.dao;

import com.j256.ormlite.support.ConnectionSource;
import com.architecture.standard.content.repository.models.Transaction;

import java.sql.SQLException;

public class TransactionDao extends AbstractDao<Transaction, Long> {

    public TransactionDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Transaction.class);
    }

}
