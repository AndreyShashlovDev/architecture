package com.architecture.standard.ui.holders;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.architecture.standard.ui.adapters.IItemClickListener;

import butterknife.ButterKnife;

public abstract class AbstractHolder<D> extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    public static final int NO_RES_ID = -1;

    protected D mModel;
    protected IItemClickListener<D> mClickListener;

    public AbstractHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind();

    /**
     * Prepares view holder to be recycled.
     */
    public void unbind() {
        // Could be abstract if will be useful
    }

    public void setData(D model) {
        this.mModel = model;
        bind();
    }

    private void setClickableItems(@NonNull @IdRes final int[] items) {
        final View.OnClickListener listener = mClickListener == null ? null : this;

        for (int id : items) {
            final View view = id == NO_RES_ID ? itemView : ButterKnife.findById(itemView, id);
            if (view != null) {
                view.setOnClickListener(listener);
                view.setClickable(true);
                view.setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null) {
            mClickListener.onItemClick(mModel, view, null);
        }
    }

    public void setOnItemClick(final IItemClickListener<D> clickListener,
                               @NonNull @IdRes final int[] items) {
        mClickListener = clickListener;
        setClickableItems(items);
    }

}
