package com.pluto.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pluto.bean.CategoryGiftInfo;
import com.pluto.gifttalk.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/19.
 */
public class CategoryGiftTitleListViewAdapter extends BaseAdapter {

    private List<CategoryGiftInfo.DataEntity.CategoriesEntity> categoriesEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    private int selectPosition;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public CategoryGiftTitleListViewAdapter(Context context, List<CategoryGiftInfo.DataEntity.CategoriesEntity> categoriesEntityList) {
        this.context = context;
        this.categoriesEntityList = categoriesEntityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoriesEntityList == null ? 0 : categoriesEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesEntityList == null ? null : categoriesEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = layoutInflater.inflate(R.layout.category_gift_listview_title_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        if (selectPosition == position) {
            convertView.setBackgroundColor(Color.WHITE);
            holder.tvChecked.setVisibility(View.VISIBLE);
            holder.tvUnChecked.setVisibility(View.GONE);
        }else {
            convertView.setBackgroundColor(0xfff2f2f2);
            holder.tvChecked.setVisibility(View.GONE);
            holder.tvUnChecked.setVisibility(View.VISIBLE);
        }
        holder.tvChecked.setText(categoriesEntityList.get(position).getName());
        holder.tvUnChecked.setText(categoriesEntityList.get(position).getName());

        return convertView;
    }

    class ViewHolder {

        @Bind(R.id.tv_category_gift_title_item_checked)
        TextView tvChecked;
        @Bind(R.id.tv_category_gift_title_item_unchecked)
        TextView tvUnChecked;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
