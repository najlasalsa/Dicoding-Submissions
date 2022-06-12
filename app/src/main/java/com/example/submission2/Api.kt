package com.example.submission2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(@Query("q") query: String): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(@Path("username") username: String?): Call<ItemUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(@Path("username") username: String?): Call<ArrayList<ItemUser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(@Path("username") username: String?): Call<ArrayList<ItemUser>>
}