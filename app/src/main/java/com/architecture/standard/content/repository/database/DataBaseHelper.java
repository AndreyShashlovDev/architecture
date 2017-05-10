package com.architecture.standard.content.repository.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.architecture.standard.content.repository.models.DaoModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;

/* package */ class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "architecture.standard.db";
    private static final int DATABASE_VERSION = 1;

    private final List<Class<? extends DaoModel>> mTablesClasses = new ArrayList<>();

    @SafeVarargs
    /* package */ DataBaseHelper(@NonNull final Context context,
                                 Class<? extends DaoModel>... classes) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mTablesClasses.addAll(Arrays.asList(classes));
    }

    @SneakyThrows
    public void dropAllSavedData() {
        for (Class<? extends DaoModel> tablesClass : mTablesClasses) {
            TableUtils.clearTable(connectionSource, tablesClass);
        }
    }

    @SneakyThrows
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        for (Class<?> tablesClass : mTablesClasses) {
            TableUtils.createTable(connectionSource, tablesClass);
        }
    }

    @SneakyThrows
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i,
                          int i2) {
        for (Class<?> tablesClass : mTablesClasses) {
            TableUtils.dropTable(connectionSource, tablesClass, true);
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

}
