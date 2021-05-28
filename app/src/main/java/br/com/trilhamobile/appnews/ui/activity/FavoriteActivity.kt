package br.com.trilhamobile.appnews.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.trilhamobile.appnews.R
import br.com.trilhamobile.appnews.adapter.MainAdapter
import br.com.trilhamobile.appnews.databinding.ActivityFavoriteBinding
import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.data.NewsDataSource
import br.com.trilhamobile.appnews.presenter.ViewHome
import br.com.trilhamobile.appnews.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity(), ViewHome.Favorite {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: FavoritePresenter
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getAll()
        configRecycle()
        clickAdapter()

        val itemTouchPerCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder):
                    Boolean {
                return true
            }

            override fun onSwiped(viewHolder:
                                  RecyclerView.ViewHolder,
                                  direction: Int) {
                val position = viewHolder.adapterPosition
                mainAdapter.differ.currentList[position]
                val article = mainAdapter.differ.currentList[position]
                presenter.deleteArticle(article)
                Snackbar.make(
                    viewHolder.itemView, R.string.article_delete_successful,
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(getString(R.string.undo)) {
                        presenter.saveArticle(article)
                        mainAdapter.notifyDataSetChanged()
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchPerCallback).apply {
            attachToRecyclerView(rvFavorite)
        }
        presenter.getAll()
    }

    private fun configRecycle() {
        with(binding.rvFavorite) {
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