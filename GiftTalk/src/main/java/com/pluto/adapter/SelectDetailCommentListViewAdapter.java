package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluto.bean.SelectDetailCommentInfo;
import com.pluto.gifttalk.R;
import com.pluto.tools.DateFormatTool;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/23.
 */
public class SelectDetailCommentListViewAdapter extends BaseAdapter {

    private List<SelectDetailCommentInfo.DataEntity.CommentsEntity> commentsEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    public SelectDetailCommentListViewAdapter(Context context, List<SelectDetailCommentInfo.DataEntity.CommentsEntity> commentsEntityList) {
        this.context = context;
        this.commentsEntityList = commentsEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return commentsEntityList == null ? 0 : commentsEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentsEntityList == null ? null : commentsEntityList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.select_detail_comment_listview_item , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        SelectDetailCommentInfo.DataEntity.CommentsEntity commentsEntity = commentsEntityList.get(position);
        holder.tvUser.setText(commentsEntity.getUser().getNickname());
        String date = DateFormatTool.formatDate(commentsEntity.getCreated_at() * 1000l, "yyyy/MM/dd h:mm a");
        holder.tvTime.setText(date);

        if(commentsEntity.getReply_to_id() != 0){
            holder.tvContent.setText("回复" + commentsEntity.getReplied_user().getNickname() + ":" + commentsEntity.getContent());
        }else {
            holder.tvContent.setText(commentsEntity.getContent());
        }
        if(commentsEntity.getUser().getAvatar_url().indexOf("liwushuo") != -1){
            Picasso.with(context).load(commentsEntity.getUser().getAvatar_url()).into(holder.ivIcon);
        }else {
            holder.ivIcon.setImageResource(R.drawable.me_avatar_boy);
        }
        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.riv_select_detail_comment_lv_item_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_select_detail_comment_lv_item_user)
        TextView tvUser;
        @Bind(R.id.tv_select_detail_comment_lv_item_time)
        TextView tvTime;
        @Bind(R.id.tv_select_detail_comment_lv_item_content)
        TextView tvContent;

        public ViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }
}
