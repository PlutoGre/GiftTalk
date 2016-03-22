package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pluto.bean.CategoryGiftInfo;
import com.pluto.gifttalk.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pluto on 2016/3/22.
 */
public class CategoryGiftRightListViewAdapter extends BaseAdapter {

    private List<CategoryGiftInfo.DataEntity.CategoriesEntity> categoriesEntityList;
    private Context context;
    private LayoutInflater layoutInflater;

    private CategoryGiftRightGridViewAdapter rightGridViewAdapter;

    public CategoryGiftRightListViewAdapter(Context context, List<CategoryGiftInfo.DataEntity.CategoriesEntity> categoriesEntityList) {
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
        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }else {
            convertView = layoutInflater.inflate(R.layout.category_gift_listview_detail_item , null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

//        if(position == 0 && categoriesEntityList.get(position).getName().equals("热门分类")){
//            holder.mLinearLayout.setVisibility(View.GONE);
//        }
        holder.tvName.setText(categoriesEntityList.get(position).getName());

        rightGridViewAdapter = new CategoryGiftRightGridViewAdapter(context , categoriesEntityList.get(position).getSubcategories());
        holder.gvDetail.setAdapter(rightGridViewAdapter);

        return convertView;
    }

    class ViewHolder{

        @Bind(R.id.tv_category_gift_lv_detail_item)
        TextView tvName;
        @Bind(R.id.gv_category_gift_detail)
        GridView gvDetail;

        @Bind(R.id.ll_category_gift_detail)
        LinearLayout mLinearLayout;

        public ViewHolder(View view) {
            ButterKnife.bind(this , view);
        }
    }
}
