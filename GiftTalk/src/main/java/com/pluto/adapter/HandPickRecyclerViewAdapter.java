package com.pluto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pluto.bean.HandPickRecyclerViewInfo;
import com.pluto.gifttalk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/17.
 */
public class HandPickRecyclerViewAdapter extends RecyclerView.Adapter<HandPickRecyclerViewAdapter.ViewHolder> {

    private List<HandPickRecyclerViewInfo.DataEntity.SecondaryBannersEntity> recyclerViewList;
    private Context context;
    private LayoutInflater layoutInflater;

    public HandPickRecyclerViewAdapter(Context context, List<HandPickRecyclerViewInfo.DataEntity.SecondaryBannersEntity> recyclerViewList) {
        this.context = context;
        this.recyclerViewList = recyclerViewList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hand_pick_recycler_item , null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(recyclerViewList.get(position).getImage_url()).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return recyclerViewList == null ? 0 : recyclerViewList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_hand_pick_recycler_item)
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }
}
