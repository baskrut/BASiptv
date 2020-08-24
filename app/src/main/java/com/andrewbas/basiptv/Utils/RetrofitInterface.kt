package com.andrewbas.basiptv.Utils

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url
import rx.Observable


interface RetrofitInterface {
    @Streaming
    @GET
    fun downloadFileByUrl(@Url fileUrl: String): Call<ResponseBody>

    @Streaming
    @GET
    fun downloadFileByUrlRx(@Url fileUrl: String): Observable<Response<ResponseBody>>
}