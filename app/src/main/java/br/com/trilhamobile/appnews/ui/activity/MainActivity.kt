package br.com.trilhamobile.appnews.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.trilhamobile.appnews.R
import br.com.trilhamobile.appnews.adapter.MainAdapter
import br.com.trilhamobile.appnews.databinding.ActivityMainBinding
import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.data.NewsDataSource
import br.com.trilhamobile.appnews.presenter.ViewHome
import br.com.trilhamobile.appnews.presenter.news.NewsPresenter


class MainActivity : AppCompatActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: NewsPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataSource = NewsDataSource(this)
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycle()
        clickAdapter()
    }

    private fun configRecycle() {
        with(binding.rvNews) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            DividerItemDecoration(
                    this@MainActivity, DividerItemDecoration.VERTICAL
            )
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener {article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showProgressBar() {
        binding.rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, "", Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
           R.id.search_menu -> {
               Intent(this, SearchActivity::class.java).apply {
                   startActivity(this)
               }
           }
           R.id.favorite -> {
               Intent(this, FavoriteActivity::class.java).apply {
                   startActivity(this)
               }
           }
        }
        return super.onOptionsItemSelected(item)
    }
}