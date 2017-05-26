package com.architecture.standard.ui.mvp.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.architecture.standard.ui.mvp.presenters.AbstractPresenter;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface BaseView<E> extends LifecycleProvider<E> {

    @NonNull
    AbstractPresenter onCreatePresenter();

    void setPresenter(@Nullable final AbstractPresenter presenter);

    @NonNull
    AbstractPresenter getPresenter() throws NullPointerException;

}
