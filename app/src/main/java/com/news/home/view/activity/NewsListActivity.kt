package com.greenley.technical.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.greenley.technical.R
import com.greenley.technical.action
import com.greenley.technical.data.model.Article
import com.greenley.technical.snack
import com.news.home.view.adapter.NewsListAdapter
import com.greenley.technical.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news_list.*

/**
 * Launcher activity.
 * Fetches the list of news articles and presents in a list
 */
class NewsListActivity : AppCompatActivity() {
    private var newsListAdapter = NewsListAdapter(mutableListOf(), ::onClickNewsArticle)
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "Start")
        setContentView(R.layout.activity_news_list)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsListRecyclerView.adapter = newsListAdapter
        loadNewsArticles()
    }

    /**
     * Helper method to fetch news articles from network
     *
     */
    private fun loadNewsArticles(){
        showProgress()
        newsViewModel.getLatestNews().observe(this, Observer { articles ->
            hideProgress()
            if(articles == null){ // Couldn't get articles due to error
                showErrorMessage()
            } else {
                newsListAdapter.setArticles(articles)
            }
        })
    }

    private fun showProgress() {
        loadingProgressBar.visibility = View.VISIBLE
        newsListRecyclerView.isEnabled = false
    }

    private fun hideProgress() {
        loadingProgressBar.visibility = View.GONE
        newsListRecyclerView.isEnabled = true
    }

    /**
     * Network error display UI where user can have option to retry the operation
     *
     */
    private fun showErrorMessage() {
        newsListActivity.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE) {
            action(getString(R.string.ok)) {
                loadNewsArticles()
            }
        }
    }

    /**
     * Navigation call back to move to news details web page
     *
     * @param article
     */
    private fun onClickNewsArticle(article: Article) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        startActivity(i)
    }
}
