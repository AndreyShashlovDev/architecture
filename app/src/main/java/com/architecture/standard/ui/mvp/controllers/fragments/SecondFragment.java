package com.architecture.standard.ui.mvp.controllers.fragments;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.architecture.standard.R;
import com.architecture.standard.ui.mvp.presenters.SecondPresenter;
import com.architecture.standard.ui.mvp.routers.Router;
import com.architecture.standard.ui.mvp.views.SecondView;
import com.architecture.standard.utils.BindLayout;

import butterknife.BindView;

@BindLayout(R.layout.fmt_transactions)
public class SecondFragment extends AbstractFragment<SecondPresenter> implements SecondView {

    public static final String FRAGMENT_TAG = SecondFragment.class.getSimpleName();

    @BindView(R.id.transactionBackground) View mBackground;

    public static Fragment newInstance() {
        return new SecondFragment();
    }

    @NonNull
    @Override
    public SecondPresenter onCreatePresenter() {
        return new SecondPresenter(this, (Router) getActivity());
    }

    @Override
    public void setBackgroundColor(@ColorRes int backgroundColor) {
        mBackground.setBackgroundColor(ContextCompat.getColor(getContext(), backgroundColor));
    }

}
