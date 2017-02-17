package com.okason.prontoquotes.core.listeners;

import com.okason.prontoquotes.models.Quote;

/**
 * Created by Valentine on 2/16/2017.
 */

public interface QuoteActionListener {
    void onFavoriteButtonClicked(long  quoteId);
    void onShareButtonClicked(Quote selectedQuote, int quotePosition);
}
