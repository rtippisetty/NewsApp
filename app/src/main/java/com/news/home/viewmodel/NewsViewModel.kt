package com.greenley.technical.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greenley.technical.data.NewsRepository
import com.greenley.technical.data.model.Article

/**
 * simple ViewModel class exposing latest news articles available
 *
 * @property repository
 */
class NewsViewModel(private val repository: NewsRepository = NewsRepository()): ViewModel() {
    /**
     * Get top headlines
     *
     * @return
     */
    fun getLatestNews(): LiveData<List<Article>?>{
        return repository.getNews()
    }
}