package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluto.bean.CategoryGiftInfo;
import com.pluto.gifttalk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/22.
 */
public class CategoryGiftRightGridViewAdapter extends BaseAdapter {

    private List<CategoryGiftInfo.DataEntity.CategoriesEntity.SubcategoriesEntity> subcategoriesEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoryGiftRightGridViewAdapter(Context context, List<CategoryGiftInfo.DataEntity.CategoriesEntity.SubcategoriesEntity> subcategoriesEntityList) {
        this.context = context;
        this.subcategoriesEntityList = subcategoriesEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return subcategoriesEntityList == null ? 0 : subcategoriesEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return subcategoriesEntityList == null ? null : subcategoriesEntityList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.category_gift_right_grid_view_item , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvName.setText(subcategoriesEntityList.get(position).getName());

        Picasso.with(context).load(subcategoriesEntityList.get(position).getIcon_url()).into(holder.ivIcon);

        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.tv_gv_category_gift_right_name)
        TextView tvName;
        @Bind(R.id.iv_gv_category_gift_right_icon)
        ImageView ivIcon;

        public ViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }
}
