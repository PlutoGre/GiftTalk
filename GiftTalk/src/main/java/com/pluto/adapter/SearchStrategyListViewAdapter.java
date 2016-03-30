package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluto.bean.HomeOthersListVIewInfo;
import com.pluto.bean.SearchStrategyInfo;
import com.pluto.gifttalk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/24.
 */
public class SearchStrategyListViewAdapter extends BaseAdapter {

    private List<SearchStrategyInfo.DataEntity.PostsEntity> itemsEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    public SearchStrategyListViewAdapter(Context context, List<SearchStrategyInfo.DataEntity.PostsEntity> itemsEntityList) {
        this.context = context;
        this.itemsEntityList = itemsEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemsEntityList == null ? 0 : itemsEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsEntityList == null ? null : itemsEntityList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.hand_pick_listview_child_item , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        SearchStrategyInfo.DataEntity.PostsEntity itemsEntity = itemsEntityList.get(position);
        holder.tvTitle.setText(itemsEntity.getTitle());
        holder.tvCount.setText(itemsEntity.getLikes_count() + "");

        Picasso.with(context).load(itemsEntity.getCover_image_url()).into(holder.ivIcon);

        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.riv_hand_pick_list_view_child_item)
        ImageView ivIcon;
        @Bind(R.id.tv_hand_pick_list_view_child_item)
        TextView tvTitle;
        @Bind(R.id.tv_hand_pick_list_view_child_item_count)
        TextView tvCount;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
