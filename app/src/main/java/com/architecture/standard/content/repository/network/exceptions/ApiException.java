package com.architecture.standard.content.repository.network.exceptions;

import android.support.annotation.Nullable;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final int mErrorCode;
    @Nullable private final String mErrorMessage;

    public ApiException(final int errorCode, @Nullable final String errorMessage) {
        super(errorCode + ": " + errorMessage);
        mErrorCode = errorCode;
        mErrorMessage = errorMessage;
    }

}
