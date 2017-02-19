package com.okason.prontoquotes.quotelist;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.okason.prontoquotes.R;
import com.okason.prontoquotes.listeners.QuoteActionListener;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Quote selectedQuote = quoteList.get(position);
        holder.quoteText.setText(selectedQuote.getQuote());
        holder.authorName.setText(selectedQuote.getAuthor().getAuthorName());
        if (selectedQuote.isFavourite()){
            holder.favoriteText.setTextColor(ContextCompat.getColor(context, R.color.primary));
        }else {
            holder.favoriteText.setTextColor(ContextCompat.getColor(context, R.color.primary_text));
        }

        Glide.with(context)
                .load(selectedQuote.getQuoteBackgroundImageUrl())
                .fallback(R.drawable.quote_fall_back_picture)
                .centerCrop()
                .into(holder.quoteImage);

        holder.shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.shareText.setVisibility(View.GONE);
                holder.favoriteText.setVisibility(View.GONE);
                listener.onShareButtonClicked(selectedQuote, holder.getLayoutPosition());
            }
        });


    }

    public void replaceData(List<Quote> quotes) {
      //  quoteList.clear();
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

        @BindView(R.id.text_view_favourite)
        TextView favoriteText;

        @BindView(R.id.text_view_share)
        TextView shareText;




        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            favoriteText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Quote selectedQuote = quoteList.get(getLayoutPosition());
                    listener.onFavoriteButtonClicked(selectedQuote.getId());
                }
            });
        }
    }
}
