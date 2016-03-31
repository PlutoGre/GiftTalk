package com.pluto.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.pluto.bean.HandPickContentProductInfo;
import com.pluto.gifttalk.R;
import com.pluto.tools.LogTool;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/15.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Map<String ,List<HandPickContentProductInfo.DataEntity.ItemsEntity>> itemsMap;
    private List<String> groupTitleList;

    public ExpandAdapter(Context context, Map<String
            , List<HandPickContentProductInfo.DataEntity.ItemsEntity>> itemsMap, List<String> groupTitleList) {
        this.context = context;
        this.itemsMap = itemsMap;
        this.groupTitleList = groupTitleList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupTitleList == null ? 0 : groupTitleList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (itemsMap != null && groupTitleList != null
                && groupTitleList.size() > groupPosition && itemsMap.get(groupTitleList.get(groupPosition)) != null) {
            return itemsMap.get(groupTitleList.get(groupPosition)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if(convertView != null){
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }else {
            convertView = layoutInflater.inflate(R.layout.hand_pick_listview_group_item , null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }
        groupViewHolder.tvGroupTitle.setText(groupTitleList.get(groupPosition));
        return convertView;
    }

    class GroupViewHolder{

        @Bind(R.id.tv_hand_pick_list_view_group_item)
        TextView tvGroupTitle;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView != null){
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }else {
            convertView = layoutInflater.inflate(R.layout.hand_pick_listview_child_item , null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }
        HandPickContentProductInfo.DataEntity.ItemsEntity itemsEntity = itemsMap.get(
                groupTitleList.get(groupPosition)).get(childPosition);
        childViewHolder.tvTitle.setText(itemsEntity.getTitle());
        childViewHolder.tvLikeCount.setText(itemsEntity.getLikes_count() + "");

        Log.d("heyang", "getChildView: ----------->" + itemsEntity.getCover_image_url());
        //使用Picasso图片请求框架加载图片
        Picasso.with(context).load(itemsEntity.getCover_image_url()).into(childViewHolder.rivIcon);
        return convertView;
    }

    class ChildViewHolder{

        @Bind(R.id.riv_hand_pick_list_view_child_item)
        RoundedImageView rivIcon;
        @Bind(R.id.tv_hand_pick_list_view_child_item)
        TextView tvTitle;
        @Bind(R.id.tv_hand_pick_list_view_child_item_count)
        TextView tvLikeCount;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }

    /**
     * ExpandableListView 如果子条目需要响应click事件,必需返回true
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
