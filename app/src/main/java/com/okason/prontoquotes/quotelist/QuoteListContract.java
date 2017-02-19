package com.okason.prontoquotes.quotelist;


import android.content.Context;

import com.okason.prontoquotes.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Quote;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Valentine on 5/26/2016.
 */
public interface QuoteListContract {

    interface View {

        void showQuotes(List<Quote> quotes);
        Context getContext();
        Realm getRealmInstance();

    }

    interface Actions {
        void loadQuotes();
        void onFavoriteButtonClicked(long quoteId);
    }

    interface Repository{
        void addAsync(Quote quote, String categoryName, String authorName, OnDatabaseOperationCompleteListener listener);
        boolean addFavorite(long quoteId);
        List<Quote> getAllQuotes(Realm realm);

    }
}
