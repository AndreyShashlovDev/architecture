package com.architecture.standard.content.repository.network.api;

import android.support.annotation.NonNull;

import com.architecture.standard.content.repository.models.Transaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransactionApi {

    @GET("transaction/")
    Single<Response<List<Transaction>>> getTransactions();

    @POST("transaction/")
    Single<Response<Transaction>> createTransaction(@NonNull @Body Transaction transaction);

    @DELETE("transaction/{id}")
    Completable deleteTransaction(@Path("id") int transactionId);

}
