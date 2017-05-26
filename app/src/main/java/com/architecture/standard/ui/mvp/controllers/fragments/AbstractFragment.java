package com.architecture.standard.ui.mvp.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.standard.AppContext;
import com.architecture.standard.AppDelegate;
import com.architecture.standard.ui.mvp.presenters.AbstractPresenter;
import com.architecture.standard.ui.mvp.views.BaseView;
import com.architecture.standard.utils.BindLayout;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbstractFragment<E extends AbstractPresenter> extends RxFragment
        implements BaseView<FragmentEvent> {

    @Nullable private Unbinder mUnbinder;
    @Nullable private AppContext mAppContext;
    @Nullable private E mPresenter;

    @NonNull
    protected AppContext getAppContext() {
        if (mAppContext == null) {
            throw new IllegalStateException("Unable to get context before calling onCreateView!");
        }
        return mAppContext;
    }

    @NonNull
    public abstract E onCreatePresenter();

    @SuppressWarnings("unchecked")
    public final void setPresenter(@Nullable final AbstractPresenter presenter) {
        mPresenter = (E) presenter;
    }

    @NonNull
    public E getPresenter() throws NullPointerException {
        if (mPresenter == null) {
            throw new NullPointerException();
        }

        return mPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final AppDelegate appDelegate = (AppDelegate) getActivity().getApplicationContext();
        mAppContext = appDelegate.getAppContext();

        final BindLayout annotation = getInjectLayoutAnnotation();
        if (annotation != null) {
            return inflateAndInject(annotation.value(), inflater, container);

        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAppContext().getLifecycleHelper()
                       .onCreate(this, savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private BindLayout getInjectLayoutAnnotation() {
        BindLayout annotation;
        Class typeToLookUp = getClass();
        while (true) {
            annotation = (BindLayout) typeToLookUp.getAnnotation(BindLayout.class);
            if (annotation != null) {
                break;
            }
            typeToLookUp = typeToLookUp.getSuperclass();
            if (typeToLookUp == null) {
                break;
            }
        }

        return annotation;
    }

    @NonNull
    private View inflateAndInject(int layoutId, LayoutInflater inflater, ViewGroup container) {
        final View view = inflater.inflate(layoutId, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getAppContext().getLifecycleHelper()
                       .onSaveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        getAppContext().getLifecycleHelper()
                       .onDestroy(this);
        super.onDestroy();
    }

}
