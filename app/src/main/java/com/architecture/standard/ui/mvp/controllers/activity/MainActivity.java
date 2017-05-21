package com.architecture.standard.ui.mvp.controllers.activity;

import android.os.Bundle;

import com.architecture.standard.R;
import com.architecture.standard.ui.mvp.presenters.MainPresenter;
import com.architecture.standard.ui.mvp.routers.MainRouter;
import com.architecture.standard.ui.mvp.views.MainView;
import com.architecture.standard.utils.BindLayout;

@BindLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainView, MainRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MainPresenter presenter = new MainPresenter(this, this);
        presenter.openNextScreen();
    }

    @Override
    public void openFirstScreen() {
        // TODO: 03.05.2017 here open needed fragment of activity. this only for example.
    }

}
