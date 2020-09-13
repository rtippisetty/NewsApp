package com.news.home.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.greenley.technical.R
import com.greenley.technical.data.model.Article
import com.greenley.technical.millisPastFromCurrentTime
import com.greenley.technical.plusAssign
import com.greenley.technical.toDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item_view.view.*
import java.util.concurrent.TimeUnit

/**
 * News list adapter to hold list of news articles
 *
 * @property articles list of news articles to display
 * @property listener onClick of the listener will land the user on news details view
 */
class NewsListAdapter(private val articles: MutableList<Article>,
                      private var listener: (Article) -> Unit): RecyclerView.Adapter<NewsListAdapter.ArticleViewHolder>() {

    companion object {
        private val TAG = NewsListAdapter.javaClass.simpleName;
    }
    //Create Article View Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_view ,parent, false)

        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position], position)
    }

    /**
     * Accessor function for Activity to set/reset news articles
     *
     * @param newsList
     */
    fun setArticles(newsList: List<Article>) {
        this.articles.clear()
        this.articles.addAll(newsList)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        /**
         * bind function to bind the view holder views with values
         *
         * @param article indicates news article
         * @param position of in the list of articles
         */
        fun bind(article: Article, position: Int) = with(view) {
            articleTitle.text = article.title
            articleDesc.text = article.description

            //How old the article is?
            setTimeDescription(article)


            view.setOnClickListener { listener(articles?.get(position)) }

            //Set articleImageView with downloaded image
            if (article.urlToImage != null) {
                Log.d(TAG, "image url : ${article.urlToImage}")
                Picasso.get().load(article.urlToImage).into(articleImageView)
            } else { //Set default local image if download fails
                articleImageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable
                    .ic_image_black_24dp, null))
            }
        }

        private fun setTimeDescription(article: Article) = with(view){
            var upTime = article.publishedAt?.toDate()?.millisPastFromCurrentTime()

            if(upTime != null) {
                val days = TimeUnit.MILLISECONDS.toDays(upTime)
                upTime -= TimeUnit.DAYS.toMillis(days)

                val hours = TimeUnit.MILLISECONDS.toHours(upTime)
                upTime -= TimeUnit.HOURS.toMillis(hours)

                val minutes = TimeUnit.MILLISECONDS.toMinutes(upTime)
                upTime -= TimeUnit.MINUTES.toMillis(minutes)

                val seconds = TimeUnit.MILLISECONDS.toSeconds(upTime)

                articleDate.text = when(days.toInt()){
                    0 -> ""
                    else -> "${days}d "
                }

                articleDate += when(hours.toInt()){
                    0 -> ""
                    else -> "${hours}hr "
                }

                articleDate += when(minutes.toInt()){
                    0 -> ""
                    else -> "${minutes}m "
                }

                articleDate += when(seconds.toInt()){
                    0 -> ""
                    else -> "${seconds}s "
                }
                articleDate += "ago"

            } else {
                articleDate.text = resources.getString(R.string.now)
            }
        }
    }

}