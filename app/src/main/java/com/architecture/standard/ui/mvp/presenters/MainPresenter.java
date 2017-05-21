package com.architecture.standard.ui.mvp.presenters;

import android.support.annotation.NonNull;

import com.architecture.standard.ui.mvp.routers.MainRouter;
import com.architecture.standard.ui.mvp.views.MainView;

public class MainPresenter extends AbstractPresenter<MainView, MainRouter> {

    public MainPresenter(@NonNull final MainView view, @NonNull final MainRouter router) {
        super(view, router);
    }

    public void openNextScreen() {
        getRouter().openFirstScreen();
    }

}
