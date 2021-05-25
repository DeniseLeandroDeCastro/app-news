package br.com.trilhamobile.appnews.presenter

import br.com.trilhamobile.appnews.model.Article

interface ViewHome {

    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(articles: List<Article>)
    }
}