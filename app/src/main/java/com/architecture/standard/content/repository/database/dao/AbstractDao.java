package com.architecture.standard.content.repository.database.dao;

import android.support.annotation.NonNull;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.architecture.standard.utils.AndroidUtils;

import java.sql.SQLException;
import java.util.List;

import io.reactivex.Single;

/* package */ abstract class AbstractDao<T, ID> extends BaseDaoImpl<T, ID> {

    /* package */ AbstractDao(ConnectionSource connectionSource, Class<T> dataClass)
            throws SQLException {
        super(connectionSource, dataClass);
    }

    public Single<List<T>> rxQueryForAll() {
        return Single.fromCallable(() -> AndroidUtils.getNotNullList(super.queryForAll()));
    }

    public Single<Integer> rxCreate(final T data) {
        return Single.fromCallable(() -> super.create(data));
    }

    public Single<Integer> rxUpdate(T data) {
        return Single.fromCallable(() -> super.update(data));
    }

    public Single<Integer> rxDelete(T data) {
        return Single.fromCallable(() -> super.delete(data));
    }

    public Single<Long> rxCountOf() {
        return Single.fromCallable(AbstractDao.super::countOf);

    }

    public Single<Integer> rxDeleteById(@NonNull final ID id, int v) {
        return Single.fromCallable(() -> super.deleteById(id));
    }

    public Single<T> rxQueryForId(@NonNull final ID id) {
        return Single.fromCallable(() -> AbstractDao.super.queryForId(id));
    }

    public Single<Integer> rxRemoveAll() {
        return Single.fromCallable(() -> delete(deleteBuilder().prepare()));
    }

}
