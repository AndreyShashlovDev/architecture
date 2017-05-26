package com.architecture.standard.ui.mvp.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.architecture.standard.ui.mvp.views.BaseView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class PresenterLifecycleHelper {

    private static final String BUNDLE_KEY_PRESENTER_LINK = "BUNDLE_KEY_PRESENTER_LINK";

    private final Map<String, AbstractPresenter> mPresenters;

    public PresenterLifecycleHelper() {
        mPresenters = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void onCreate(@NonNull final BaseView view, @Nullable final Bundle savedInstanceState) {
        AbstractPresenter presenter = null;

        if (savedInstanceState == null) {
            try {
                presenter = view.getPresenter();
                mPresenters.values()
                           .remove(presenter);
            } catch (final NullPointerException e) {
                // silent
            }

        } else {
            final String key = savedInstanceState.getString(BUNDLE_KEY_PRESENTER_LINK);
            presenter = mPresenters.remove(key);
        }


        if (presenter != null) {
            presenter.setView(view);
            view.setPresenter(presenter);
            presenter.onRestore();

        } else {
            presenter = view.onCreatePresenter();
            view.setPresenter(presenter);
            presenter.onViewCreated();
        }

    }

    private void setToCachePresenter(@NonNull final AbstractPresenter presenter,
                                     @NonNull final BaseView view, @Nullable Bundle outState) {
        final String generateKey = getKeyByPresenterAndView(presenter, view);

        if (presenter.isRetainInstance() && !TextUtils.isEmpty(generateKey)) {
            mPresenters.put(generateKey, presenter);

            if (outState != null) {
                outState.putString(BUNDLE_KEY_PRESENTER_LINK, generateKey);
            }
        }
    }

    public void onSaveInstanceState(@NonNull final BaseView view, @NonNull final Bundle outState) {
        final AbstractPresenter presenter = view.getPresenter();
        setToCachePresenter(presenter, view, outState);
    }

    @NonNull
    private String getKeyByPresenterAndView(@NonNull final AbstractPresenter presenter,
                                            @NonNull final BaseView view) {
        return generateKey(presenter, view);
    }

    @NonNull
    private String generateKey(@NonNull final Object... args) {
        final StringBuilder builder = new StringBuilder();

        for (final Object arg : args) {
            builder.append(System.identityHashCode(arg));
        }

        return builder.toString();
    }

    @SuppressWarnings("unchecked")
    public void onDestroy(@NonNull final BaseView view) {
        final AbstractPresenter presenter = view.getPresenter();
        final Collection<AbstractPresenter> presenters = mPresenters.values();

        if (!presenter.isRetainInstance() || !presenters.contains(presenter)) {
            presenter.onDestroy();
            presenters.remove(presenter);
        }
        view.setPresenter(null);
        presenter.setView(null);
    }

}
