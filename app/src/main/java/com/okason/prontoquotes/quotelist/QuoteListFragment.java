package com.okason.prontoquotes.quotelist;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okason.prontoquotes.R;
import com.okason.prontoquotes.listeners.QuoteActionListener;
import com.okason.prontoquotes.models.Quote;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteListFragment extends Fragment implements QuoteListContract.View, QuoteActionListener {

    private final static String LOG_TAG = "QuoteListFragment";
    private QuoteListContract.Actions mPresenter;
    private QuoteListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadQuotes();

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
    public Realm getRealmInstance() {
        return realm;
    }

    //shows Toast messages using Snackbar
    private void makeToast(String message) {
        Snackbar snackbar = Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG);

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary));
        TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }


    @Override
    public void onFavoriteButtonClicked(long quoteId) {
        mPresenter.onFavoriteButtonClicked(quoteId);
    }

    @Override
    public void onShareButtonClicked(Quote selectedQuote, int quotePosition) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Quote by " + selectedQuote.getAuthor().getAuthorName());
        intent.putExtra(android.content.Intent.EXTRA_TEXT, selectedQuote.getQuote());
        try {
            startActivity(Intent.createChooser(intent, "Share Quote"));
            mPresenter.loadQuotes();
        } catch (ActivityNotFoundException e) {
            makeToast("No App Available");
        }
    }


}
