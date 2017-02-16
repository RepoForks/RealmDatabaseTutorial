package com.okason.prontoquotes.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Valentine on 2/16/2017.
 */

public class Category extends RealmObject{
    @PrimaryKey
    private long id;
    private String guid;
    private String categoryName;
    private String categoryLocalImageUrl;
    private String categoryCloudImageUrl;
    private RealmList<Quote> quotes;


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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryLocalImageUrl() {
        return categoryLocalImageUrl;
    }

    public void setCategoryLocalImageUrl(String categoryLocalImageUrl) {
        this.categoryLocalImageUrl = categoryLocalImageUrl;
    }

    public String getCategoryCloudImageUrl() {
        return categoryCloudImageUrl;
    }

    public void setCategoryCloudImageUrl(String categoryCloudImageUrl) {
        this.categoryCloudImageUrl = categoryCloudImageUrl;
    }

    public RealmList<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(RealmList<Quote> quotes) {
        this.quotes = quotes;
    }
}
