package com.architecture.standard.ui.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface IItemClickListener<D> {

    void onItemClick(@NonNull final D data, @NonNull final View view, @Nullable final String tag);

}
