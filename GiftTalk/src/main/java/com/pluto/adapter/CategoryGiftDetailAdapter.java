package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluto.bean.CategoryGiftDetailInfo;
import com.pluto.bean.CategoryGiftInfo;
import com.pluto.bean.SelectGridViewInfo;
import com.pluto.gifttalk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/24.
 */
public class CategoryGiftDetailAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CategoryGiftDetailInfo.DataEntity.ItemsEntity> dataEntity1List;

    public CategoryGiftDetailAdapter(Context context, List<CategoryGiftDetailInfo.DataEntity.ItemsEntity> dataEntity1List) {
        this.context = context;
        this.dataEntity1List = dataEntity1List;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataEntity1List == null ? 0 : dataEntity1List.size();
    }

    @Override
    public Object getItem(int position) {
        return dataEntity1List == null ? null : dataEntity1List.get(position);
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
            convertView = layoutInflater.inflate(R.layout.gridview_fg_select_item , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        CategoryGiftDetailInfo.DataEntity.ItemsEntity dataEntity1 = dataEntity1List.get(position);

        holder.tvContent.setText(dataEntity1.getName());
        holder.tvPrice.setText(dataEntity1.getPrice());
        int favoritesCount = dataEntity1.getFavorites_count() / 100;
        holder.tvLikeCount.setText(favoritesCount/10f + "k");

        Picasso.with(context).load(dataEntity1.getCover_image_url()).into(holder.ivIcon);

        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.iv_gv_item_card_view_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_gv_item_card_view_content)
        TextView tvContent;
        @Bind(R.id.tv_gv_item_card_view_price)
        TextView tvPrice;
        @Bind(R.id.tv_gv_item_card_view_like_count)
        TextView tvLikeCount;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
