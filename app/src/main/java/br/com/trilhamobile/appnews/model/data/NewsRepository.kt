package br.com.trilhamobile.appnews.model.data

import br.com.trilhamobile.appnews.model.Article
import br.com.trilhamobile.appnews.model.db.ArticleDatabase

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

    fun getAll(): List<Article> = db.getArticleDao().getAll()

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)
}