package br.com.trilhamobile.appnews.activity.model

data class news(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)