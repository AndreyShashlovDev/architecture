package com.architecture.standard.ui.mvp.views;

import android.support.annotation.ColorRes;

import com.trello.rxlifecycle2.android.FragmentEvent;

public interface SecondView extends BaseView<FragmentEvent> {

    void setBackgroundColor(@ColorRes final int backgroundColor);

}
