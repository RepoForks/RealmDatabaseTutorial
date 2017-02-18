package com.okason.prontoquotes.ui.authors;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okason.prontoquotes.R;
import com.okason.prontoquotes.models.Author;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorListFragment extends Fragment implements AuthorListContract.View{

    private final static String LOG_TAG = "AuthorListFragment";
    private AuthorListContract.Actions mPresenter;
    private AuthorListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;

    private View mRootView;
    @BindView(R.id.author_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyText;



    public AuthorListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_author_list, container, false);
        ButterKnife.bind(this, mRootView);
        realm = Realm.getDefaultInstance();
        mPresenter = new AuthorListPresenter(this);
        mAdapter = new AuthorListAdapter(new ArrayList<Author>(), getContext());

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadAuthors();

    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void showAuthors(List<Author> authors) {
        mAdapter.replaceData(authors);
    }

    @Override
    public void showDeleteAuthorPrompt(Author author) {

    }

    @Override
    public void showEmptyText() {

    }

    @Override
    public void hideEmptyText() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void scrollToAuthor(Author author) {

    }

    @Override
    public Realm getRealmInstance() {
        return realm;
    }
}
