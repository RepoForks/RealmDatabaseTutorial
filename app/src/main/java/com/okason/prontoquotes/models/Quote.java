package com.okason.prontoquotes.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Valentine on 2/16/2017.
 */

public class Quote extends RealmObject{
    @PrimaryKey
    private long id;
    private String quote;
    private Author author;
    private String quoteBackgroundImageUrl;
    private boolean isFavourite;

    public long getId() {
        return id;
    }

    public void updateToRealm(Quote selectedQuote) {
        quote = selectedQuote.getQuote();
        author = selectedQuote.getAuthor();
        quoteBackgroundImageUrl = selectedQuote.getQuoteBackgroundImageUrl();
        isFavourite = selectedQuote.isFavourite;

    }


    public void setId(long id) {
        this.id = id;
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

    public String getQuoteBackgroundImageUrl() {
        return quoteBackgroundImageUrl;
    }

    public void setQuoteBackgroundImageUrl(String quoteBackgroundImageUrl) {
        this.quoteBackgroundImageUrl = quoteBackgroundImageUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
