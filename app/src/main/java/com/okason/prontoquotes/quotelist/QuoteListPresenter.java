package com.okason.prontoquotes.quotelist;

import com.okason.prontoquotes.data.QuoteRealmRepository;
import com.okason.prontoquotes.models.Quote;

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
        List<Quote> quotes = mRepository.getAllQuotes(mView.getRealmInstance());
        mView.showQuotes(quotes);

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
