package com.okason.prontoquotes.ui.quotelist;


import android.content.Context;

import com.okason.prontoquotes.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Quote;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Valentine on 5/26/2016.
 */
public interface QuoteListContract {

    interface View {

        void showQuotes(List<Quote> quotes);
        void showAddQuote();
        Context getContext();
        void showSingleDetailUi(long quoteId);
        void showDualDetailUi(Quote quote);
        void showEmptyText(boolean showText);
        void showDeleteConfirmation(Quote quote);
        void showMessage(String message);
        Realm getRealmInstance();

    }

    interface Actions {
        void loadQuotes();
        void onAddNewQuoteButtonClicked();
        void openQuoteDetails(long quoteId);
        List<Quote> getQuotes(String sortColumn, boolean ascending);
        void onDeleteQuoteButtonClicked(Quote quote);
        void deleteQuote(Quote quote);
        void setLayoutMode(boolean dualScreen);
        void onFavoriteButtonClicked(long quoteId);
    }

    interface Repository{
        void addAsync(Quote quote, String categoryName, String authorName, OnDatabaseOperationCompleteListener listener);
        void updateAsync(Quote quote, OnDatabaseOperationCompleteListener listener);
        void deleteAsync(Quote quote, OnDatabaseOperationCompleteListener listener);
        boolean addFavorite(long quoteId);
        List<Quote> getAllQuotes(Realm realm, String sortOption, boolean sortOrder);
        Quote getQuoteById(long id);

    }
}
