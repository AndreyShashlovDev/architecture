package com.architecture.standard.utils;

import android.view.View;

import lombok.NonNull;

public final class ViewUtils {

    private ViewUtils() {
    }

    public static void visibleIf(boolean condition, @NonNull View v) {
        v.setVisibility(condition ? View.VISIBLE : View.GONE);
    }

    public static void visibleIf(boolean condition, @NonNull View... v) {
        for (View view : v) {
            visibleIf(condition, view);
        }
    }

}
