package com.architecture.standard.content.repository;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.network.exceptions.ApiException;

import java.net.HttpURLConnection;

import retrofit2.Response;
import timber.log.Timber;

/* package */ abstract class AbstractRepository {

    /* package */ <T> T checkResponseCode(@NonNull final Response<T> response) {
        return checkResponseCode(response, HttpURLConnection.HTTP_OK);
    }

    /* package */ <T> T checkResponseCode(@NonNull final Response<T> response,
                                          final int expectedCode) {
        if (response.code() != expectedCode) {
            final int code = response.code();
            final String message = getMessage(response);
            throw new ApiException(code, message);
        }

        return response.body();
    }

    @NonNull
    /* package */ <T> String getMessage(@NonNull final Response<T> response) {
        String message = response.message();
        try {
            // TODO: 19.05.2017 Get error message from response.
        } catch (final Exception e) {
            Timber.e(e.getMessage());
        }
        return message;
    }

}
