package com.example.nyt; // <============= CHANGE ME

import com.google.gson.annotations.SerializedName;

import java.util.List;

/***
 * Model class for news articles. This should be 100% familiar to you.
 */
public class Article {

    // unique identifier of an article
    @SerializedName("id")
    private long articleID;

    // Title of the article
    @SerializedName("title")
    private String headline;
    // Name of the author
    @SerializedName("byline")
    private String author;
    // Short text to be displayed on main page
    @SerializedName("abstract")
    private String summary;
    // Full text of the article
    @SerializedName("url")
    private String content;
    // Resource ID of associated image (e.g. R.drawable.my_image)

    @SerializedName("media")
    private List<Multimedia> media;


//    public Article(int articleID, String headline, String author, String summary, String content) {
//        this.articleID = articleID;
//        this.headline = headline;
//        this.author = author;
//        this.summary = summary;
//        this.content = content;
//
//        // Added this line for temporary way of showing images
//    }

    public long getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }


    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return this.media.get(0).image.get(2).getUrl();
    }

    private class Multimedia {
        @SerializedName("media-metadata")
        private List<Images> image;
    }

    private class Images {
        private String url;
        private String format;

        public String getUrl() {
            return url;
        }
    }
}
