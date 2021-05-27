package br.com.trilhamobile.appnews.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.trilhamobile.appnews.R
import br.com.trilhamobile.appnews.adapter.MainAdapter
import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.data.NewsDataSource
import br.com.trilhamobile.appnews.presenter.ViewHome
import br.com.trilhamobile.appnews.presenter.favorite.FavoritePresenter
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rvNews

class FavoriteActivity : AbstractActivity(), ViewHome.Favorite {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_favorite

    override fun onInject() {

        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getAll()
        configRecycle()
        clickAdapter()

    }

    private fun configRecycle() {
        with(rvFavorite) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            addItemDecoration(DividerItemDecoration(
                    this@FavoriteActivity,
                    DividerItemDecoration.VERTICAL
            ))
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener {article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }
}