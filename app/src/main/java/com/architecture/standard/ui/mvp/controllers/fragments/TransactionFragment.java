package com.architecture.standard.ui.mvp.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.architecture.standard.R;
import com.architecture.standard.content.repository.models.Transaction;
import com.architecture.standard.ui.mvp.presenters.TransactionsPresenter;
import com.architecture.standard.ui.mvp.routers.MainRouter;
import com.architecture.standard.ui.mvp.views.TransactionsView;
import com.architecture.standard.utils.BindLayout;

import java.util.List;

import butterknife.BindView;

@BindLayout(R.layout.fmt_transactions)
public class TransactionFragment extends AbstractFragment<TransactionsPresenter>
        implements TransactionsView {

    public static final String FRAGMENT_TAG = TransactionFragment.class.getSimpleName();

    @BindView(R.id.transactionBackground) View mBackground;

    public static TransactionFragment newInstance() {
        return new TransactionFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public TransactionsPresenter onCreatePresenter() {
        return new TransactionsPresenter(this, (MainRouter) getActivity());
    }

    @Override
    public void setTransactions(@NonNull final List<Transaction> list) {

    }

    @Override
    public void setBackgroundColor(@ColorRes int backgroundColor) {
        mBackground.setBackgroundColor(ContextCompat.getColor(getContext(), backgroundColor));
    }

}
