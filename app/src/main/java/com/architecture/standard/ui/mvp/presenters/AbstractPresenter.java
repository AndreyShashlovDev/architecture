package com.architecture.standard.ui.mvp.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.architecture.standard.ui.mvp.routers.Router;
import com.architecture.standard.ui.mvp.views.BaseView;

import java.lang.ref.WeakReference;

public abstract class AbstractPresenter<E extends BaseView, R extends Router> {

    @NonNull private WeakReference<E> mWeakViewReference;
    @NonNull private final R mRouter;
    private boolean mRetainInstance;

    /* package */ AbstractPresenter(@NonNull final E view, @NonNull final R router) {
        mWeakViewReference = new WeakReference<E>(view);
        mRouter = router;
    }

    public abstract void onViewCreated();


    /* package */ void onRestore() {
    }

    /* package */ void onDestroy() {
    }

    @SuppressWarnings("unchecked")
    public void setView(@Nullable final E view) {
        mWeakViewReference.clear();
        mWeakViewReference = new WeakReference<E>(view);
    }

    /* package */ void setRetainInstance(final boolean retainInstance) {
        mRetainInstance = retainInstance;
    }

    /* package */ boolean isRetainInstance() {
        return mRetainInstance;
    }

    @NonNull
    protected E getView() throws NullPointerException {
        final E view = mWeakViewReference.get();

        if (view == null) {
            throw new NullPointerException();
        }

        return view;
    }

    @NonNull
    /* package */ R getRouter() {
        return mRouter;
    }

}
