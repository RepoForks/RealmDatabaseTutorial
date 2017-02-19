package com.okason.prontoquotes.data;

import com.okason.prontoquotes.ProntoQuoteApplication;
import com.okason.prontoquotes.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Author;
import com.okason.prontoquotes.models.Quote;
import com.okason.prontoquotes.quotelist.QuoteListContract;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Valentine on 2/16/2017.
 */

public class QuoteRealmRepository implements QuoteListContract.Repository {

    @Override
    public void addAsync(final Quote quote, final String categoryName, final String authorName, final OnDatabaseOperationCompleteListener listener) {
        final Realm insertRealm = Realm.getDefaultInstance();
        final long id = ProntoQuoteApplication.quotePrimaryKey.getAndIncrement();
        insertRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRealm) {
                final Author author = createOrGetAuthor(authorName, backgroundRealm);
                Quote savedQuote = backgroundRealm.createObject(Quote.class, id);
                savedQuote.setQuote(quote.getQuote());
                savedQuote.setAuthor(author);
                savedQuote.setQuoteBackgroundImageUrl(quote.getQuoteBackgroundImageUrl());
                savedQuote.setFavourite(quote.isFavourite());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSaveOperationSucceeded(id);
                insertRealm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override

            public void onError(Throwable error) {
                listener.onSaveOperationFailed(error.getMessage());
                insertRealm.close();
            }
        });


    }



    private Author createOrGetAuthor(String authorName, Realm insertRealm) {
        Author foundAuthor = insertRealm.where(Author.class).equalTo("authorName", authorName).findFirst();
        if(foundAuthor != null) {
            return foundAuthor;
        }else {
            final long authorId = ProntoQuoteApplication.authorPrimaryKey.getAndIncrement();
            Author author = insertRealm.createObject(Author.class, authorId);
            author.setAuthorName(authorName);
            return author;
        }
    }




    @Override
    public boolean addFavorite(long quoteId) {
        Realm realm = Realm.getDefaultInstance();
        final Quote affectedQuote = realm.where(Quote.class).equalTo("id", quoteId).findFirst();
        if (affectedQuote.isFavourite()){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    affectedQuote.setFavourite(false);
                    realm.copyToRealmOrUpdate(affectedQuote);
                }
            });
            return false;
        }else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    affectedQuote.setFavourite(true);
                    realm.copyToRealmOrUpdate(affectedQuote);
                }
            });
            return true;
        }

    }


    @Override
    public List<Quote> getAllQuotes(Realm passedInRealm) {
        RealmResults<Quote> result = passedInRealm.where(Quote.class).findAll();
        return result;
    }


}
