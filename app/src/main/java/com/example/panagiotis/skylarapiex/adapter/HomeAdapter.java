package com.example.panagiotis.skylarapiex.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.panagiotis.skylarapiex.MainActivity;
import com.example.panagiotis.skylarapiex.R;
import com.example.panagiotis.skylarapiex.fragments.SingleEpisodeViewFragment;
import com.example.panagiotis.skylarapiex.model.pojo.EpidoseDetails;
import com.example.panagiotis.skylarapiex.model.realm.CaschData;
import com.example.panagiotis.skylarapiex.model.realm.Favorites;
import com.example.panagiotis.skylarapiex.utils.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Panagiotis on 22/01/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Realm realm;
    RealmResults<CaschData> mEpidoseDetailsArrayList;
    MainActivity mMainActivity;

    public HomeAdapter(RealmResults<CaschData> mEpidoseDetailsArrayList, MainActivity mMainActivity) {
        this.mEpidoseDetailsArrayList = mEpidoseDetailsArrayList;
        this.mMainActivity = mMainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_view,parent,false);
        return new HomeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        realm = Realm.getDefaultInstance();
        final String title=mEpidoseDetailsArrayList.get(position).getName();
        final String uuid=mEpidoseDetailsArrayList.get(position).getUuid();
        String imageUrl="";
        holder.mTitle.setText(title);

        final RealmResults<Favorites> results = realm.where(Favorites.class)
                .equalTo("uuid", uuid).findAll();
        if(results.size()>0){
            Picasso.with(holder.itemView.getContext())
                    .load(android.R.drawable.btn_star_big_on)
                    .into(holder.mFavorite);
        }else{
            Picasso.with(holder.itemView.getContext())
                    .load(android.R.drawable.btn_star_big_off)
                    .into(holder.mFavorite);
        }

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    switch (view.getId()){
                        case R.id.titleLayout:
                        case R.id.imageview:
                            mMainActivity.fragmentTransaction(new SingleEpisodeViewFragment(),uuid);
                            break;
                    }
                    if(view.getId()==R.id.favorite) {
                        final RealmResults<Favorites> results = realm.where(Favorites.class)
                                .equalTo("uuid", uuid).findAll();
                        if (results.size() > 0) {

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    results.deleteFirstFromRealm();
                                }
                            });

                            Picasso.with(holder.itemView.getContext())
                                    .load(android.R.drawable.btn_star_big_off)
                                    .into(holder.mFavorite);
                        } else {
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    Favorites favorites = realm.createObject(Favorites.class);
                                    favorites.setName(title);
                                    favorites.setUuid(uuid);
                                }
                            });
                            Picasso.with(holder.itemView.getContext())
                                    .load(android.R.drawable.btn_star_big_on)
                                    .into(holder.mFavorite);
                        }
                        notifyItemChanged(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEpidoseDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.textview_title)
        TextView mTitle;
        @BindView(R.id.imageview)
        ImageView mImageView;
        @BindView(R.id.favorite)
        ImageView mFavorite;
        @BindView(R.id.titleLayout)
        LinearLayout mTitleLayout;

        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mFavorite.setOnClickListener(this);
            mTitleLayout.setOnClickListener(this);
            mImageView.setOnClickListener(this);

        }

        public void setClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getLayoutPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getLayoutPosition(), true);
            return true;
        }
    }
}
