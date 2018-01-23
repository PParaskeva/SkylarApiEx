package com.example.panagiotis.skylarapiex.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panagiotis.skylarapiex.R;
import com.example.panagiotis.skylarapiex.model.pojo.EpidoseDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Panagiotis on 22/01/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    ArrayList<EpidoseDetails> mEpidoseDetailsArrayList;

    public HomeAdapter(ArrayList<EpidoseDetails> mEpidoseDetailsArrayList) {
        this.mEpidoseDetailsArrayList = mEpidoseDetailsArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_view,parent,false);
        return new HomeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mEpidoseDetailsArrayList.get(position).getTitle());
        if(mEpidoseDetailsArrayList.get(position).getImageUrls()!=null
                && mEpidoseDetailsArrayList.get(position).getImageUrls().size()>0) {
            Picasso.with(holder.itemView.getContext())
                    .load(mEpidoseDetailsArrayList.get(position).getImageUrls().get(0).toString())
                    .placeholder(android.R.drawable.ic_lock_idle_alarm)
                    .into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mEpidoseDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_title)
        TextView mTitle;
        @BindView(R.id.imageview)
        ImageView mImageView;
        @BindView(R.id.favorite)
        ImageView mFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
