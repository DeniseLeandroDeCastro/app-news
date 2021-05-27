package br.com.trilhamobile.appnews.presenter.favorite

import br.com.trilhamobile.appnews.model.Article

interface FavoriteHome {

    interface Presenter {
        fun onSuccess(articles: List<Article>)
    }
}