package com.architecture.standard.ui.mvp.views;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.models.Transaction;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

public interface TransactionsView extends BaseView<FragmentEvent> {

    void setTransactions(@NonNull final List<Transaction> list);

    void setBackgroundColor(@ColorRes final int backgroundColor);

}
