package com.pluto.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluto.bean.CategoryStrategyRecyclerViewInfo;
import com.pluto.gifttalk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/19.
 */
public class CategoryStrategyGridViewAdapter extends BaseAdapter {

    private List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoryStrategyGridViewAdapter(Context context, List<CategoryStrategyRecyclerViewInfo.DataEntity.ChannelGroupsEntity.ChannelsEntity> channelsEntityList) {
        this.context = context;
        this.channelsEntityList = channelsEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return channelsEntityList == null ? 0 : channelsEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return channelsEntityList == null ? null : channelsEntityList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.category_strategy_recycler_item , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Log.d("heyang", "getView: ----------------->" + channelsEntityList.get(position).getName());
        holder.tvName.setText(channelsEntityList.get(position).getName());
        Picasso.with(context).load(channelsEntityList.get(position).getIcon_url()).into(holder.ivIcon);
        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.iv_rv_category_strategy_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_rv_category_strategy_name)
        TextView tvName;

        public ViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }
}
