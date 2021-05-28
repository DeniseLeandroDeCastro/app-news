package br.com.trilhamobile.appnews.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import br.com.trilhamobile.appnews.R
import br.com.trilhamobile.appnews.databinding.ActivityArticleBinding
import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.data.NewsDataSource
import br.com.trilhamobile.appnews.presenter.ViewHome
import br.com.trilhamobile.appnews.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar


class ArticleActivity : AppCompatActivity(), ViewHome.Favorite {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let {url ->
                loadUrl(url)
            }
        }

        binding.fab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(it, R. string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>) {}
}