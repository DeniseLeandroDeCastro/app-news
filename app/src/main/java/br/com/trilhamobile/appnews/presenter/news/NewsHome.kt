package br.com.trilhamobile.appnews.presenter.news

import br.com.trilhamobile.appnews.model.NewsResponse

interface NewsHome {

    interface Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}