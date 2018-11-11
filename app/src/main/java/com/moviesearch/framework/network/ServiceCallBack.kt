package com.moviesearch.framework.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class ServiceCallBack<T> : Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>?) {

        if (response != null) {

            if (response.isSuccessful) {
                onSuccess(response)
            } else {
                onFail(response.message())
            }
        }

        println(call?.request()?.url())
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFail(t?.message as String)
    }

    abstract fun onSuccess(response: Response<T>?)

    abstract fun onFail(message: String)
}