package com.okason.prontoquotes.quotelist;

import android.content.Context;

import com.okason.prontoquotes.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Author;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Valentine on 2/17/2017.
 */

public interface AuthorListContract {
    public interface View{
        void showAuthors(List<Author> authors);
        Context getContext();
        Realm getRealmInstance();

    }

    public interface Actions{
        void loadAuthors();       

    }

    public interface Repository{
        List<Author> getAllAuthors(Realm passedInRealm);
        Author getAuthorById(long id);
        void deleteAsync(long id, OnDatabaseOperationCompleteListener listener);
        void saveAsync(Author author, OnDatabaseOperationCompleteListener listener);
        void updateAsync(Author author, OnDatabaseOperationCompleteListener listener);

    }


}
