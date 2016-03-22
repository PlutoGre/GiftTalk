package com.pluto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.pluto.bean.CategoryStrategyRecyclerViewInfo;
import com.pluto.gifttalk.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/18.
 */
public class CategoryStrategyRecyclerViewAdapter extends RecyclerView.Adapter<CategoryStrategyRecyclerViewAdapter.ViewHolder> {

    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity> channelGroupsEntityList;
    private Context context;
    private LayoutInflater layoutInflater;
    private View headView;

    //    private CategoryStrategyRecyclerItemViewAdapter recyclerItemViewAdapter;
    private CategoryStrategyGridViewAdapter strategyGridViewAdapter;
    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntityList = new ArrayList<>();

    public CategoryStrategyRecyclerViewAdapter(Context context,View headView
            , List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity> channelGroupsEntityList) {
        this.context = context;
        this.channelGroupsEntityList = channelGroupsEntityList;
        layoutInflater = LayoutInflater.from(context);
        this.headView = headView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1){
            return new ViewHolder(headView);
        }
        View view = layoutInflater.inflate(R.layout.category_strategy_recycler_group, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == 0){
            return;
        }
        holder.tvName.setText(channelGroupsEntityList.get(position - 1).getName());
        List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channels = channelGroupsEntityList.get(position - 1).getChannels();
        channelsEntityList.clear();
        //?????????????????????????????????????????????
        for (int j = 0; j < channels.size(); j++) {
            channelsEntityList.add(channels.get(j));
        }
        strategyGridViewAdapter = new CategoryStrategyGridViewAdapter(context, channelsEntityList);
        holder.gridViewGroup.setAdapter(strategyGridViewAdapter);

    }


    @Override
    public int getItemCount() {
        return channelGroupsEntityList == null ? 0 : channelGroupsEntityList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        }
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

//        @Bind(R.id.tv_category_strategy_recycler_group)
        TextView tvName;
//        @Bind(R.id.gv_category_strategy_recycler_group)
        GridView gridViewGroup;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            gridViewGroup = (GridView) itemView.findViewById(R.id.gv_category_strategy_recycler_group);
            tvName = (TextView) itemView.findViewById(R.id.tv_category_strategy_recycler_group);
        }
    }
}
