package com.okason.prontoquotes.ui.authors;

import com.okason.prontoquotes.data.AuthorRealmRepository;
import com.okason.prontoquotes.models.Author;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentine on 2/18/2017.
 */

public class AuthorListPresenter implements AuthorListContract.Actions {
    private final AuthorListContract.View mView;
    private final AuthorListContract.Repository mRepository;

    public AuthorListPresenter(AuthorListContract.View mView) {
        this.mView = mView;
        this.mRepository = new AuthorRealmRepository(mView.getContext());
    }

    @Override
    public void loadAuthors() {
        List<Author> authors = mRepository.getAllAuthors(mView.getRealmInstance());
        if (authors != null && authors.size() > 0){
            mView.showEmptyText();
            mView.showAuthors(authors);

        }else {
            mView.showEmptyText();
            mView.showAuthors(new ArrayList<Author>());
        }

    }

    @Override
    public Author getAuthor(long id) {
        return null;
    }

    @Override
    public void onAuthorSelected(Author author) {

    }

    @Override
    public void onAddAuthorButtonClicked() {

    }

    @Override
    public void addAuthor(Author author) {

    }

    @Override
    public void onDeleteAuthorButtonClicked(Author author) {

    }

    @Override
    public void deleteAuthor(Author author) {

    }

    @Override
    public void onEditAuthorButtonClicked(Author author) {

    }

    @Override
    public void updateAuthor(Author author) {

    }
}
