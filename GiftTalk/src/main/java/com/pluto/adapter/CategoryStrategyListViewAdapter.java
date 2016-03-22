package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.pluto.bean.CategoryStrategyRecyclerViewInfo;
import com.pluto.gifttalk.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/22.
 */
public class CategoryStrategyListViewAdapter extends BaseAdapter {

    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity> channelGroupsEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    private CategoryStrategyGridViewAdapter strategyGridViewAdapter;
    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntityList = new ArrayList<>();

    public CategoryStrategyListViewAdapter(Context context, List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity> channelGroupsEntityList) {
        this.context = context;
        this.channelGroupsEntityList = channelGroupsEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return channelGroupsEntityList == null ? 0 : channelGroupsEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return channelGroupsEntityList == null ? null : channelGroupsEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }else {
            convertView = layoutInflater.inflate(R.layout.category_strategy_recycler_group , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvName.setText(channelGroupsEntityList.get(position).getName());
        List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channels = channelGroupsEntityList.get(position).getChannels();
        channelsEntityList.clear();
        for (int j = 0; j < channels.size(); j++) {
            channelsEntityList.add(channels.get(j));
        }
        strategyGridViewAdapter = new CategoryStrategyGridViewAdapter(context, channelsEntityList);
        holder.gvGroup.setAdapter(strategyGridViewAdapter);

        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.tv_category_strategy_recycler_group)
        TextView tvName;
        @Bind(R.id.gv_category_strategy_recycler_group)
        GridView gvGroup;

        public ViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }
}
