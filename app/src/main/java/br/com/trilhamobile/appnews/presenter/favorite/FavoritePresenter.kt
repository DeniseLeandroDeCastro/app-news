package br.com.trilhamobile.appnews.presenter.favorite

import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.data.NewsDataSource
import br.com.trilhamobile.appnews.presenter.ViewHome

class FavoritePresenter(
        val view: ViewHome.Favorite,
        private val dataSource: NewsDataSource) : FavoriteHome.Presenter {

        fun saveArticle(article: Article) {
            this.dataSource.saveArticle(article)
        }

        override fun onSuccess(articles: List<Article>) {
            this.view.showArticles(articles)
        }
}