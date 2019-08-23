package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.ReviewModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ReviewItemBinding;
import com.example.myapplication.databinding.TrailerItemsBinding;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    Context mContext;
    List<ReviewModel.ResultsBean> mReviews;
    public ReviewAdapter(Context context, List<ReviewModel.ResultsBean> reviews){
        mContext=context;
        mReviews=reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ReviewItemBinding reviewItemBinding = DataBindingUtil.inflate(inflater, R.layout.review_item,parent,false);
        return new ReviewViewHolder( reviewItemBinding );
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.reviewItemBinding.movieReviewAuthor.setText( mReviews.get( position ).getAuthor() );
        holder.reviewItemBinding.movieReviewContent.setText( mReviews.get( position ).getContent() );

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        ReviewItemBinding reviewItemBinding;
        public ReviewViewHolder(ReviewItemBinding binding) {
            super( binding.getRoot() );
            reviewItemBinding=binding;
        }
    }
}

