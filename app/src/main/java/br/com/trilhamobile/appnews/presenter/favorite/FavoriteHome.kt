package br.com.trilhamobile.appnews.presenter.favorite

import br.com.trilhamobile.appnews.model.Article

interface FavoriteHome {

    fun showArticles(articles: List<Article>)
}