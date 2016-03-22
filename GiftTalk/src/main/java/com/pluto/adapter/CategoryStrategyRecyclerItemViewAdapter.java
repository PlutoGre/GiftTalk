package com.pluto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluto.bean.CategoryStrategyRecyclerViewInfo;
import com.pluto.gifttalk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/18.
 */
public class CategoryStrategyRecyclerItemViewAdapter extends RecyclerView.Adapter<CategoryStrategyRecyclerItemViewAdapter.ViewHolder> {

    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoryStrategyRecyclerItemViewAdapter(Context context, List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntityList) {
        this.context = context;
        this.channelsEntityList = channelsEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.category_strategy_recycler_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(channelsEntityList.get(position).getName());
        Log.d("heyang", "onBindViewHolder: -------------->" + channelsEntityList.get(position).getIcon_url());
        Log.d("heyang", "onBindViewHolder: ------------------>" + holder.ivIcon);
        Picasso.with(context).load(channelsEntityList.get(position).getIcon_url()).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        Log.d("heyang", "getItemCount: ------------->" + channelsEntityList.size());
        return channelsEntityList == null ? 0 : channelsEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_rv_category_strategy_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_rv_category_strategy_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }
}
