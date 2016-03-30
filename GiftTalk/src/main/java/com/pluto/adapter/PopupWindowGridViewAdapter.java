package com.pluto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pluto.gifttalk.R;

import java.util.List;

/**
 * Created by Pluto on 2016/3/29.
 */
public class PopupWindowGridViewAdapter extends BaseAdapter {

    private List<String> titleList;
    private Context context;
    private LayoutInflater layoutInflater;

    public PopupWindowGridViewAdapter(Context context, List<String> titleList) {
        this.context = context;
        this.titleList = titleList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titleList == null ? 0 : titleList.size();
    }

    @Override
    public Object getItem(int position) {
        return titleList == null ? null : titleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.popup_window_item, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_popup_window_title);
        textView.setText(titleList.get(position));
        return convertView;
    }
}
