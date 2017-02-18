package com.okason.prontoquotes.data;

import android.content.Context;

import com.okason.prontoquotes.R;
import com.okason.prontoquotes.core.ProntoQuoteApplication;
import com.okason.prontoquotes.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Author;
import com.okason.prontoquotes.ui.authors.AuthorListContract;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Valentine on 2/17/2017.
 */

public class AuthorRealmRepository implements AuthorListContract.Repository {
    private final Context mContext;

    public AuthorRealmRepository(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public List<Author> getAllAuthors(Realm passedInRealm) {
        RealmResults<Author> authors = passedInRealm.where(Author.class).findAll();
        return authors;
    }

    @Override
    public Author getAuthorById(long id) {
        Realm realm = Realm.getDefaultInstance();
        Author inMemoryAuthor = null;
        try {
            Author author = realm.where(Author.class).equalTo("id", id).findFirst();
            inMemoryAuthor = realm.copyFromRealm(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();
        return inMemoryAuthor;
    }

    @Override
    public void deleteAsync(final long id, final OnDatabaseOperationCompleteListener listener) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRealm) {
                RealmResults<Author> clients = backgroundRealm.where(Author.class).findAll();
                Author clientToBeDeleted = clients.where().equalTo("id", id).findFirst();
                clientToBeDeleted.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
                listener.onDeleteOperationCompleted(mContext.getString(R.string.deleted));

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                realm.close();
                listener.onDeleteOperationFailed(error.getMessage());
            }
        });

    }

    @Override
    public void saveAsync(final Author author, final OnDatabaseOperationCompleteListener listener) {
        final Realm insertRealm = Realm.getDefaultInstance();
        final long id = ProntoQuoteApplication.authorPrimaryKey.incrementAndGet();
        insertRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRealm) {
                Author author1 = backgroundRealm.createObject(Author.class, id);
                author1.updateToRealm(author);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                insertRealm.close();
                listener.onSaveOperationSucceeded(id);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                insertRealm.close();
                listener.onSaveOperationFailed(error.getMessage());
            }
        });

    }

    @Override
    public void updateAsync(final Author author, final OnDatabaseOperationCompleteListener listener) {
        final Realm updateRealm = Realm.getDefaultInstance();
        updateRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRealm) {
                Author author1 = backgroundRealm.where(Author.class).equalTo("id",author.getId()).findFirst();
                author1.updateToRealm(author);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                updateRealm.close();
                listener.onUpdateOperationCompleted(mContext.getString(R.string.updated));
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                updateRealm.close();
                listener.onUpdateOperationFailed(error.getMessage());
            }
        });

    }

}
