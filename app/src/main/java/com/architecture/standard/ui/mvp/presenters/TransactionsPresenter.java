package com.architecture.standard.ui.mvp.presenters;

import android.support.annotation.NonNull;

import com.architecture.standard.R;
import com.architecture.standard.ui.mvp.routers.MainRouter;
import com.architecture.standard.ui.mvp.views.TransactionsView;

public class TransactionsPresenter extends AbstractPresenter<TransactionsView, MainRouter> {

    public TransactionsPresenter(@NonNull final TransactionsView view,
                                 @NonNull final MainRouter router) {
        super(view, router);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated() {
        applyBackgroundColor();
        getRouter().openSecondScreen();
    }

    @Override
    void onRestore() {
        applyBackgroundColor();
        super.onRestore();
    }

    private void applyBackgroundColor() {
        getView().setBackgroundColor(R.color.colorAccent);
    }

}
