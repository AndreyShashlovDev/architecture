package com.architecture.standard.ui.mvp.controllers.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.architecture.standard.R;
import com.architecture.standard.ui.mvp.controllers.fragments.SecondFragment;
import com.architecture.standard.ui.mvp.controllers.fragments.TransactionFragment;
import com.architecture.standard.ui.mvp.presenters.MainPresenter;
import com.architecture.standard.ui.mvp.routers.MainRouter;
import com.architecture.standard.ui.mvp.views.MainView;
import com.architecture.standard.utils.BindLayout;

@BindLayout(R.layout.act_main)
public class MainActivity extends AbstractActivity<MainPresenter> implements MainView, MainRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getPresenter().openNextScreen();
        }
    }

    @NonNull
    @Override
    public MainPresenter onCreatePresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    public void openTransactionsScreen() {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, TransactionFragment.newInstance(),
                                           TransactionFragment.FRAGMENT_TAG)
                                   .addToBackStack(TransactionFragment.FRAGMENT_TAG)
                                   .commitAllowingStateLoss();
    }

    @Override
    public void openSecondScreen() {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, SecondFragment.newInstance(),
                                           SecondFragment.FRAGMENT_TAG)
                                   .addToBackStack(SecondFragment.FRAGMENT_TAG)
                                   .commitAllowingStateLoss();
    }

}
