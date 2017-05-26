package com.architecture.standard.ui.mvp.presenters;

import android.support.annotation.NonNull;

import com.architecture.standard.R;
import com.architecture.standard.ui.mvp.routers.Router;
import com.architecture.standard.ui.mvp.views.SecondView;

public class SecondPresenter extends AbstractPresenter<SecondView, Router> {

    public SecondPresenter(@NonNull final SecondView view, @NonNull final Router router) {
        super(view, router);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated() {
        getView().setBackgroundColor(R.color.colorPrimary);
    }

}
