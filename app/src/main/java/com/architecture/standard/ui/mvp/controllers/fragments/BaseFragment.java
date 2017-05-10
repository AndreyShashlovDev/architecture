package com.architecture.standard.ui.mvp.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.architecture.standard.AppContext;
import com.architecture.standard.AppDelegate;
import com.architecture.standard.utils.BindLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment {

    @Nullable private Unbinder mUnbinder;
    @Nullable private AppContext mAppContext;

    @NonNull
    protected AppContext getAppContext() {
        if (mAppContext == null) {
            throw new IllegalStateException("Unable to get context before calling onCreateView!");
        }
        return mAppContext;
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
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

}
