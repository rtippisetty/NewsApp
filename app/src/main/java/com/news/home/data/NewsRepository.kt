package com.greenley.technical.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.greenley.technical.data.model.Article
import com.greenley.technical.data.model.NewsResponse
import com.greenley.technical.data.network.NetworkClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

/**
 * Central News repository. Acts as bridge between VewModel and model
 * It provides functions to fetch required data asked by ViewModel object
 */
class NewsRepository {
    private val networkClient = NetworkClient()

    /**
     * fetches news articles
     *
     * @return list of new Articles
     */
    fun getNews(): LiveData<List<Article>?>{

        val newsList = MutableLiveData<List<Article>>()

        networkClient.getNews().enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d(this.javaClass.simpleName, "Article couldn't be fetched")
                newsList.value = null
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d(this.javaClass.simpleName, "Response : ${response.body()?.articles}")
                newsList.value = response.body()?.articles
            }

        })
        return newsList
    }
}