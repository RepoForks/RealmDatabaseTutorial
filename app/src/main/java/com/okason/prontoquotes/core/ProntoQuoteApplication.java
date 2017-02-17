package com.okason.prontoquotes.core;

import android.app.Application;

import com.okason.prontoquotes.models.Author;
import com.okason.prontoquotes.models.Category;
import com.okason.prontoquotes.models.Quote;
import com.okason.prontoquotes.utils.Constants;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Valentine on 2/16/2017.
 */

public class ProntoQuoteApplication extends Application {

    public static AtomicLong quotePrimaryKey;
    public static AtomicLong categoryPrimaryKey;
    public static AtomicLong authorPrimaryKey;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }


    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration  = new RealmConfiguration.Builder()
                .name(Constants.REALM_DATABASE)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        //Now set this config as the default config for your app
        //This way you can call Realm.getDefaultInstance elsewhere

        Realm.setDefaultConfiguration(configuration);

        //Get the instance of this Realm that you just instantiated
        //And use it to get the Primary Key for the Quote and Category Tables
        Realm realm = Realm.getInstance(configuration);



        try {
            //Attempt to get the last id of the last entry in the Quote class and use that as the
            //Starting point of your primary key. If your Quote table is not created yet, then this
            //attempt will fail, and then in the catch clause you want to create a table
            quotePrimaryKey = new AtomicLong(realm.where(Quote.class).max("id").longValue() + 1);
        } catch (Exception e) {
            //All write transaction should happen within a transaction, this code block
            //Should only be called the first time your app runs
            realm.beginTransaction();

            //Create temp Quote so as to create the table
            Quote quote = realm.createObject(Quote.class, 0);

            //Now set the primary key again
            quotePrimaryKey = new AtomicLong(realm.where(Quote.class).max("id").longValue() + 1);

            //remove temp quote
            RealmResults<Quote> results = realm.where(Quote.class).equalTo("id", 0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }

        try {
            //Attempt to get the last id of the last entry in the Author class and use that as the
            //Starting point of your primary key. If your Author table is not created yet, then this
            //attempt will fail, and then in the catch clause you want to create a table
            authorPrimaryKey = new AtomicLong(realm.where(Author.class).max("id").longValue() + 1);
        } catch (Exception e) {
            //All write transaction should happen within a transaction, this code block
            //Should only be called the first time your app runs
            realm.beginTransaction();

            //Create temp Author so as to create the table
            Author author = realm.createObject(Author.class, 0);

            //Now set the primary key again
            authorPrimaryKey = new AtomicLong(realm.where(Author.class).max("id").longValue() + 1);

            //remove temp author
            RealmResults<Author> results = realm.where(Author.class).equalTo("id", 0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }

        try {
            //Do the same for the Category table
            categoryPrimaryKey = new AtomicLong(realm.where(Category.class).max("id").longValue() + 1);
        } catch (Exception e) {
            realm.beginTransaction();

            Category category = realm.createObject(Category.class, 0);


            //Now set the primary key again
            categoryPrimaryKey = new AtomicLong(realm.where(Category.class).max("id").longValue() + 1);

            //remove temp category
            RealmResults<Category> categoryResult = realm.where(Category.class).equalTo("id", 0).findAll();
            categoryResult.deleteAllFromRealm();
            realm.commitTransaction();

        } finally {
            //Close realm once you are done
            realm.close();
        }

    }
}
