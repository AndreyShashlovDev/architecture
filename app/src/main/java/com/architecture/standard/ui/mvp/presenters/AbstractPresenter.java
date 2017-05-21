package com.architecture.standard.ui.mvp.presenters;

import android.support.annotation.NonNull;

import com.architecture.standard.ui.mvp.routers.Router;
import com.architecture.standard.ui.mvp.views.BaseView;

/* package */ class AbstractPresenter<E extends BaseView, R extends Router> {

    @NonNull private final E mView;
    @NonNull private final R mRouter;

    /* package */ AbstractPresenter(@NonNull final E view, @NonNull final R router) {
        mView = view;
        mRouter = router;
    }

    @NonNull
    protected E getView() {
        return mView;
    }

    @NonNull
    protected R getRouter() {
        return mRouter;
    }

}
