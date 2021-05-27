package br.com.trilhamobile.appnews.presenter.favorite

import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.data.NewsDataSource

class FavoritePresenter(private val dataSource: NewsDataSource) {

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }

}