package com.demo.mody.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.mody.popularmovies.R;
import com.demo.mody.popularmovies.models.Review;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud El-Metainy on 21-Dec-15.
 */
public class RecyclerReviewsAdapter extends RecyclerView.Adapter<RecyclerReviewsAdapter.MyViewHolder> {

    private Context context;
    private List<Review> reviews = Collections.emptyList();

    public RecyclerReviewsAdapter(Context context, List<Review> reviews) {

        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_reviews, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        final Review review = reviews.get(position);
        holder.txtAuthor.setText(review.getAuthor());
        holder.txtContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {

        return reviews.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_recyclerReviews_author) TextView txtAuthor;
        @Bind(R.id.expand_text_view) ExpandableTextView txtContent;

        public MyViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
