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
    private String guid;
    private String authorName;
    private String authorLocalImageUrl;
    private String authorCloudImageUrl;
    private RealmList<Quote> quotes;

    public void updateToRealm(Author author) {
        guid = author.getGuid();
        authorName = author.getAuthorName();
        authorLocalImageUrl = author.authorLocalImageUrl;
        authorCloudImageUrl = author.getAuthorCloudImageUrl();
        quotes = getQuotes();

    }


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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLocalImageUrl() {
        return authorLocalImageUrl;
    }

    public void setAuthorLocalImageUrl(String authorLocalImageUrl) {
        this.authorLocalImageUrl = authorLocalImageUrl;
    }

    public String getAuthorCloudImageUrl() {
        return authorCloudImageUrl;
    }

    public void setAuthorCloudImageUrl(String authorCloudImageUrl) {
        this.authorCloudImageUrl = authorCloudImageUrl;
    }

    public RealmList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(RealmList<Quote> quotes) {
        this.quotes = quotes;
    }
}
