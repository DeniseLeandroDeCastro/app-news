package br.com.trilhamobile.appnews.presenter.news

import br.com.trilhamobile.appnews.model.NewsResponse
import br.com.trilhamobile.appnews.model.data.NewsDataSource
import br.com.trilhamobile.appnews.presenter.ViewHome

class NewsPresenter(
        val view: ViewHome.View,
        private val dataSource: NewsDataSource
        ) : NewsHome.Presenter{
    override fun requestAll() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}