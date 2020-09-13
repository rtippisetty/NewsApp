package com.greenley.technical.data.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * News article model received from server
 *
 * @property source
 * @property id
 * @property title
 * @property description
 * @property url
 * @property urlToImage
 * @property publishedAt
 * @property content
 */
@Entity
data class Article (
    @SerializedName("source")
    @Expose
    val source:Source?=null,

    @SerializedName("author")
    @Expose
    val id:String?=null,

    @SerializedName("title")
    @Expose
    val title:String?=null,

    @SerializedName("description")
    @Expose
    val description:String?=null,

    @SerializedName("url")
    @Expose
    val url:String?=null,

    @SerializedName("urlToImage")
    @Expose
    val urlToImage:String?=null,

    @SerializedName("publishedAt")
    @Expose
    val publishedAt:String?=null,

    @SerializedName("content")
    @Expose
    val content:String?=null

)

@Entity
data class Source(
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null
)