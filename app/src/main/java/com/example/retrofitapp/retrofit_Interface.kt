package com.example.retrofitapp

import retrofit2.Call
import retrofit2.http.*

interface retrofit_Interface {

    fun getdata(): Call<List<RetroFitDataItem>>

    @POST("posts")
    fun putdata(@Body data:RetroFitDataItem):Call<RetroFitDataItem>

    @DELETE("posts/{id}")
    fun delete(
        @Path("id")id:Int
    ):Call<Unit>

    @PUT("postss/{id}")
    fun update(@Path("id")id:Int,
        @Body user:RetroFitDataItem
    ):Call<RetroFitDataItem>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patch(@Path("id")id:Int,@Field ("id")ids:Int):Call<Unit>

}