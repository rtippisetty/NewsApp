package com.news.home.data.network

import com.greenley.technical.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Network API contracts
 *
 */
interface NewsApi {
    /**
     * top-headlines api
     *
     * @param apiKey
     * @param country
     * @return top-headline articles
     */
    @GET("v2/top-headlines")
    fun getNews(@Query("apiKey") apiKey: String,
                @Query("country") country: String): Call<NewsResponse>

}