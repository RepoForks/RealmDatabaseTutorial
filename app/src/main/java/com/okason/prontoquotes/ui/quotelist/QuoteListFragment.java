package com.okason.prontoquotes.ui.quotelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okason.prontoquotes.R;
import com.okason.prontoquotes.core.listeners.QuoteActionListener;
import com.okason.prontoquotes.models.Quote;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteListFragment extends Fragment implements QuoteListContract.View, QuoteActionListener {
    private QuoteListContract.Actions mPresenter;
    private QuoteListAdapter mAdapter;
    private Realm realm;

    private View mRootView;
    @BindView(R.id.quote_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyText;




    public QuoteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_quote_list, container, false);
        ButterKnife.bind(this, mRootView);
        realm = Realm.getDefaultInstance();
        mPresenter = new QuoteListPresenter(this);
        mAdapter = new QuoteListAdapter(new ArrayList<Quote>(), getContext(), this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
       // mPresenter.loadQuotes();
        RealmResults<Quote> result = realm.where(Quote.class).findAll();
        mAdapter.replaceData(result);
    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void showQuotes(List<Quote> quotes) {
        mAdapter.replaceData(quotes);
    }

    @Override
    public void showAddQuote() {

    }

    @Override
    public void showSingleDetailUi(long quoteId) {

    }

    @Override
    public void showDualDetailUi(Quote quote) {

    }

    @Override
    public void showEmptyText(boolean showText) {

    }

    @Override
    public void showDeleteConfirmation(Quote quote) {

    }

    @Override
    public void showMessage(String message) {

    }
}
