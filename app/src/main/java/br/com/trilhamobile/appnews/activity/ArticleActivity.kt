package br.com.trilhamobile.appnews.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.trilhamobile.appnews.R

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
    }
}