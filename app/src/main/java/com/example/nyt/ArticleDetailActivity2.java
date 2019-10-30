package com.example.nyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ArticleDetailActivity2 extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail2);

        webView = findViewById(R.id.articleWebView);
        // Get the intent that was used to travel to this activity
        Intent intent = getIntent();

        // Get the extra identified by "ArticleID" that was put into the intent at the origin.
        long articleID = intent.getLongExtra("ArticleID", 0);

        // Now that we were passed the ID of the clicked item, we can get the rest of the data
        // from the "database". Imagine this could be a real SQL database.
        Article article = FakeDatabase.getJsonArticleById(articleID);
        webView.loadUrl(article.getContent());
    }
}
