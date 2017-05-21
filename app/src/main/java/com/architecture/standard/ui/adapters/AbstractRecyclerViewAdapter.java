package com.architecture.standard.ui.adapters;


import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.architecture.standard.ui.holders.AbstractHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRecyclerViewAdapter<D, T extends AbstractHolder> extends
        RecyclerView.Adapter<T> {

    @NonNull @IdRes private static final int[] CLICKABLE_LAYOUT_ITEMS = {AbstractHolder.NO_RES_ID};

    @NonNull private List<D> mData;
    @Nullable private IItemClickListener<D> mClickListener;
    private int mPosition;

    public AbstractRecyclerViewAdapter() {
        this(Collections.emptyList());
    }

    public AbstractRecyclerViewAdapter(@NonNull final List<D> data) {
        this.mData = Collections.unmodifiableList(data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final AbstractHolder mHolder = onInitViewHolder(parent, inflater, viewType);
        mHolder.setOnItemClick(mClickListener, getClickableItems());

        return (T) mHolder;
    }

    @NonNull
    @IdRes
    public int[] getClickableItems() {
        return CLICKABLE_LAYOUT_ITEMS;
    }

    protected abstract T onInitViewHolder(@NonNull final ViewGroup parent,
                                          @NonNull final LayoutInflater inflater,
                                          final int viewType);

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(T holder, int position) {
        this.mPosition = holder.getAdapterPosition();
        holder.setData(mData.get(this.mPosition));
    }

    public int getPosition() {
        return mPosition;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    public List<D> getData() {
        return mData;
    }

    public void setData(@NonNull final List<D> mData) {
        this.mData = Collections.unmodifiableList(mData);
        notifyDataSetChanged();
    }

    public void clear() {
        this.mData = Collections.emptyList();
        notifyDataSetChanged();
    }

    public void addAll(@NonNull final List<D> data) {
        final List<D> dList = new ArrayList<D>();
        data.addAll(mData);
        dList.addAll(data);
        this.mData = Collections.unmodifiableList(dList);
        notifyDataSetChanged();
    }

    public void setClickListener(@Nullable final IItemClickListener<D> mClickListener) {
        this.mClickListener = mClickListener;
    }

    @Override
    public void onViewRecycled(@NonNull final T holder) {
        holder.unbind();
        super.onViewRecycled(holder);
    }

}
