package com.greenley.technical.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * News articles response as a whole
 *
 * @property status
 * @property totalResults
 * @property articles
 */
class NewsResponse(
        @SerializedName("status")
        @Expose
        val status: String? = null,

        @SerializedName("totalResults")
        @Expose
        val totalResults: Int? = null,

        @SerializedName("articles")
        @Expose
        val articles: List<Article>? = null
)
