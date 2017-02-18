package com.okason.prontoquotes.ui.authors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.okason.prontoquotes.R;
import com.okason.prontoquotes.models.Author;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Valentine on 2/17/2017.
 */

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorListAdapter.ViewHolder> {
    private List<Author> authorList;
    private final Context context;

    public AuthorListAdapter(List<Author> authorList, Context context) {
        this.authorList = authorList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_row_custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Author selectedAuthor = authorList.get(position);

        holder.authorName.setText(selectedAuthor.getAuthorName());
        String quoteText = selectedAuthor.getQuotes().size() > 1 ? " Quotes" : " Quote";
        holder.quoteCount.setText(selectedAuthor.getQuotes().size() + quoteText);
        String imagePath = TextUtils.isEmpty(selectedAuthor.getAuthorLocalImageUrl()) ?
                selectedAuthor.getAuthorCloudImageUrl() : selectedAuthor.getAuthorLocalImageUrl();

        Glide.with(context)
                .load(imagePath)
                .fallback(R.drawable.quote_fall_back_picture)
                .centerCrop()
                .into(holder.authorHeadShot);


    }

    public void replaceData(List<Author> authors) {
        authorList = authors;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return authorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_author_head_shot)ImageView authorHeadShot;
        @BindView(R.id.text_view_authors_name) TextView authorName;
        @BindView(R.id.text_view_authors_quote_count) TextView quoteCount;
        @BindView(R.id.image_view_expand) ImageView expandArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
