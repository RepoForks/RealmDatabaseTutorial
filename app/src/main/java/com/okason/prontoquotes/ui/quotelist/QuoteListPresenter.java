package com.okason.prontoquotes.ui.quotelist;

import com.okason.prontoquotes.data.QuoteRealmRepository;
import com.okason.prontoquotes.models.Quote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentine on 2/16/2017.
 */

public class QuoteListPresenter implements QuoteListContract.Actions {

    private final QuoteListContract.View mView;
    private final QuoteListContract.Repository mRepository;

    public QuoteListPresenter(QuoteListContract.View mView) {
        this.mView = mView;
        mRepository = new QuoteRealmRepository();
    }


    @Override
    public void loadQuotes() {
        List<Quote> quotes = getQuotes("quote", true);        mView.showQuotes(quotes);

        if (quotes != null && quotes.size() > 0){
            mView.showEmptyText(false);
            mView.showQuotes(quotes);

        }else {
            mView.showEmptyText(true);
            mView.showQuotes(new ArrayList<Quote>());
        }

    }

    @Override
    public void onAddNewQuoteButtonClicked() {

    }

    @Override
    public void openQuoteDetails(long quoteId) {

    }

    @Override
    public List<Quote> getQuotes(String sortColumn, boolean ascending) {
        return mRepository.getAllQuotes(mView.getRealmInstance(), sortColumn, ascending);
    }

    @Override
    public void onDeleteQuoteButtonClicked(Quote quote) {

    }

    @Override
    public void deleteQuote(Quote quote) {

    }

    @Override
    public void setLayoutMode(boolean dualScreen) {

    }

    @Override
    public void onFavoriteButtonClicked(long  quoteId) {
        if (mRepository.addFavorite(quoteId)){
            loadQuotes();
        }else {
            loadQuotes();
        }

    }
}
