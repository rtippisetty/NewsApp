package com.greenley.technical.data.network

import com.greenley.technical.data.model.NewsResponse
import com.news.home.data.network.NewsApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit network client to handle network request
 *
 */
class NetworkClient {

    private val newsApi: NewsApi

    companion object {
        private const val API_KEY = "7019973f03494525b62199f2e92fe71f"
        private const val NEWS_BASE_URL = "https://newsapi.org/"
        private const val US = "us"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        newsApi = retrofit.create(NewsApi::class.java)
    }

    /**
     * Gets the news articles
     *
     * @return
     */
    fun getNews():Call<NewsResponse> {
        return newsApi.getNews(API_KEY, US)
    }
}