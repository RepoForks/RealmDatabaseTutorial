package com.okason.prontoquotes.ui.quotelist;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okason.prontoquotes.R;
import com.okason.prontoquotes.core.listeners.QuoteActionListener;
import com.okason.prontoquotes.models.Quote;
import com.okason.prontoquotes.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
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

    private final int EXTERNAL_PERMISSION_REQUEST = 1;
    private Bitmap selectedQuoteBitmap = null;




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
        makeToast(message);
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
        View quoteView = mLayoutManager.findViewByPosition(quotePosition);
        selectedQuoteBitmap = getScreenShot(quoteView);
        if (isStoragePermissionGranted()){
            store(selectedQuoteBitmap);
        }
    }

    public static Bitmap getScreenShot(View screenView) {
       // View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void store(Bitmap bm){
        String fileName = "Pronto_Quote_" + FileUtils.getDatetimeSuffix(System.currentTimeMillis()) + ".png";
        final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Quotes";
        File dir = new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            shareQuote(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareQuote(File file){
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Quote"));
            mPresenter.loadQuotes();
        } catch (ActivityNotFoundException e) {
            makeToast("No App Available");
        }
    }


    //Checks whether the user has granted the app permission to
    //access external storage
    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(LOG_TAG,"Permission is granted");
                return true;
            } else {
                Log.v(LOG_TAG,"Permission is revoked");
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_PERMISSION_REQUEST);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(LOG_TAG,"Permission is granted  API < 23");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case EXTERNAL_PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    store(selectedQuoteBitmap);
                }else {
                    makeToast("External permission denied");
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
