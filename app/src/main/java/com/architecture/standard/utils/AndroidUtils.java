package com.architecture.standard.utils;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class AndroidUtils {

    private AndroidUtils() {
    }

    /**
     * Checks the incoming argument if it is null, if so, it returns an new array list {@link ArrayList}.
     * Otherwise, the incoming argument (original).
     *
     * @param value some string {@link List<E>} can be null.
     * @return always return not null array list {@link ArrayList<E>}.
     */
    public static <E> List<E> getNotNullList(@Nullable final List<E> value) {
        return value == null ? new ArrayList<>() : value;
    }

    /**
     * Checks the incoming argument if it is null, if so, it returns an empty string.
     * Otherwise, the incoming argument (original).
     *
     * @param value some string {@link String} can be null.
     * @return always return not null string {@link String}.
     */
    public static String getNotNullString(@Nullable final String value) {
        return value == null ? "" : value;
    }

}
