package com.architecture.standard.ui.mvp.controllers.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.architecture.standard.AppContext;
import com.architecture.standard.AppDelegate;
import com.architecture.standard.ui.mvp.presenters.AbstractPresenter;
import com.architecture.standard.ui.mvp.routers.Router;
import com.architecture.standard.ui.mvp.views.BaseView;
import com.architecture.standard.utils.BindLayout;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbstractActivity<E extends AbstractPresenter>
        extends RxAppCompatActivity implements BaseView<ActivityEvent>, Router {

    @Nullable private AppContext mAppContext;
    @Nullable private Unbinder mUnbinder;
    @Nullable private E mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppContext = ((AppDelegate) getApplicationContext()).getAppContext();

        final BindLayout layout = getClass().getAnnotation(BindLayout.class);
        if (layout != null) {
            setContentView(layout.value());
            mUnbinder = ButterKnife.bind(this, this);
        }
        mAppContext.getLifecycleHelper()
                   .onCreate(this, savedInstanceState);

        getPresenter().onViewCreated();
    }

    /* package */ void changeFragment(@NonNull final Fragment fragment, @NonNull final String tag) {
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void setPresenter(@Nullable AbstractPresenter presenter) {
        mPresenter = (E) presenter;
    }

    @NonNull
    @Override
    public abstract E onCreatePresenter();

    @NonNull
    @Override
    public E getPresenter() throws NullPointerException {
        if (mPresenter == null) {
            throw new NullPointerException();
        }

        return mPresenter;
    }

    @NonNull
    protected AppContext getAppContext() {
        if (mAppContext == null) {
            throw new IllegalStateException("Unable to get context before calling onCreate!");
        }
        return mAppContext;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getAppContext().getLifecycleHelper()
                       .onSaveInstanceState(this, outState);
    }

    @Override
    public void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        getAppContext().getLifecycleHelper()
                       .onDestroy(this);

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        final int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount < 2) {
            supportFinishAfterTransition();

        } else {
            super.onBackPressed();
        }
    }

}
