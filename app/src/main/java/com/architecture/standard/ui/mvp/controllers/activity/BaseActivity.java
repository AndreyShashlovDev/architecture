package com.architecture.standard.ui.mvp.controllers.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.RxActivity;
import com.architecture.standard.AppContext;
import com.architecture.standard.AppDelegate;
import com.architecture.standard.ui.mvp.routers.Router;
import com.architecture.standard.utils.BindLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxActivity implements Router {

    @Nullable private AppContext mAppContext;
    @Nullable private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppContext = ((AppDelegate) getApplicationContext()).getAppContext();

        final BindLayout layout = getClass().getAnnotation(BindLayout.class);
        if (layout != null) {
            setContentView(layout.value());
            mUnbinder = ButterKnife.bind(this, this);
        }
    }

    @NonNull
    protected AppContext getAppContext() {
        if (mAppContext == null) {
            throw new IllegalStateException("Unable to get context before calling onCreate!");
        }
        return mAppContext;
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

}
