package com.okason.prontoquotes.ui.quotelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.okason.prontoquotes.R;
import com.okason.prontoquotes.core.listeners.QuoteActionListener;
import com.okason.prontoquotes.models.Quote;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Valentine on 2/16/2017.
 */

public class QuoteListAdapter extends RecyclerView.Adapter<QuoteListAdapter.ViewHolder> {
    private List<Quote> quoteList;
    private final Context context;
    private final QuoteActionListener listener;

    public QuoteListAdapter(List<Quote> quoteList, Context context, QuoteActionListener listener) {
        this.quoteList = quoteList;
        this.context = context;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View quoteView = inflater.inflate(R.layout.quote_row_custom_layout, parent, false);
        return new ViewHolder(quoteView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quote selectedQuote = quoteList.get(position);
        holder.quoteText.setText(selectedQuote.getQuote());
        holder.authorName.setText(selectedQuote.getAuthor().getAuthorName());

        Glide.with(context)
                .load(selectedQuote.getQuoteCloudImageUrl())
                .fallback(R.drawable.quote_fall_back_picture)
                .centerCrop()
                .into(holder.quoteImage);


    }

    public void replaceData(List<Quote> quotes) {
        quoteList = quotes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_view_quote_image)
        ImageView quoteImage;

        @BindView(R.id.text_view_quote_name)
        TextView quoteText;

        @BindView(R.id.text_view_author_name)
        TextView authorName;




        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
