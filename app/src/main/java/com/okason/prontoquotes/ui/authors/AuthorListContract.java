package com.okason.prontoquotes.ui.authors;

import android.content.Context;

import com.okason.prontoquotes.core.listeners.OnDatabaseOperationCompleteListener;
import com.okason.prontoquotes.models.Author;

import java.util.List;

/**
 * Created by Valentine on 2/17/2017.
 */

public interface AuthorListContract {
    public interface View{
        void showAuthors(List<Author> authors);
        void showDeleteAuthorPrompt(Author author);
        void showEmptyText();
        void hideEmptyText();
        void showMessage(String message);
        void scrollToAuthor(Author author);
        Context getContext();

    }

    public interface Actions{
        void loadAuthors();
        Author getAuthor(long id);
        void onAuthorSelected(Author author);
        void onAddAuthorButtonClicked();
        void checkStatus(long id);
        void addAuthor(Author author);
        void onDeleteAuthorButtonClicked(Author author);
        void deleteAuthor(Author author);
        void onEditAuthorButtonClicked(Author author);
        void updateAuthor(Author author);
        void onAddAuthorManualluButtonClicked();

    }

    public interface Repository{
        List<Author> getAllAuthors();
        Author getAuthorById(long id);
        void deleteAsync(long id, OnDatabaseOperationCompleteListener listener);
        void saveAsync(Author author, OnDatabaseOperationCompleteListener listener);
        void updateAsync(Author author, OnDatabaseOperationCompleteListener listener);

    }


}
