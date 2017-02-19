package com.okason.prontoquotes.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Valentine on 2/16/2017.
 */

public class Author extends RealmObject{
    @PrimaryKey
    private long id;
    private String authorName;
    private String authorImageUrl;
    private RealmList<Quote> quotes;

    public void updateToRealm(Author author) {
        authorName = author.getAuthorName();
        authorImageUrl = author.authorImageUrl;
        quotes = getQuotes();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public void setAuthorImageUrl(String authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }

    public RealmList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(RealmList<Quote> quotes) {
        this.quotes = quotes;
    }
}
