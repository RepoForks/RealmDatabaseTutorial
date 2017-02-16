package com.okason.prontoquotes.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Valentine on 2/16/2017.
 */

public class Quote extends RealmObject{
    @PrimaryKey
    private long id;
    private String guid;
    private String quote;
    private Author author;
    private Category category;
    private String quoteLocalImageUrl;
    private String quoteCloudImageUrl;
    private boolean isFavourite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQuoteLocalImageUrl() {
        return quoteLocalImageUrl;
    }

    public void setQuoteLocalImageUrl(String quoteLocalImageUrl) {
        this.quoteLocalImageUrl = quoteLocalImageUrl;
    }

    public String getQuoteCloudImageUrl() {
        return quoteCloudImageUrl;
    }

    public void setQuoteCloudImageUrl(String quoteCloudImageUrl) {
        this.quoteCloudImageUrl = quoteCloudImageUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
