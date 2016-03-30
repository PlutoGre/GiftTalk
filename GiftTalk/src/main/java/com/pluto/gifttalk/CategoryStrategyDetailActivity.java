package com.pluto.gifttalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.pluto.adapter.HomeOthersListViewAdapter;
import com.pluto.bean.HomeOthersListVIewInfo;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;
import com.pluto.http.UrlConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryStrategyDetailActivity extends BaseActivity {

    @Bind(R.id.lv_category_strategy_detail)
    ListView mListView;
    private List<HomeOthersListVIewInfo.DataEntity.ItemsEntity> itemsEntityList = new ArrayList<>();
    private HomeOthersListViewAdapter listViewAdapter;

    @Bind(R.id.ib_category_strategy_back)
    ImageButton ibBack;
    @Bind(R.id.ib_ib_category_strategy_sort)
    ImageButton ibSort;
    @Bind(R.id.tv_category_strategy_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_strategy_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        tvTitle.setText(name);

        String url = UrlConfig.CATEGORY_STRATEGY_DETAIL_URL_1 + id + UrlConfig.CATEGORY_STRATEGY_DETAIL_URL_2;
        OkHttpTools.newInstance().okGet(url, HomeOthersListVIewInfo.class, new IOkCallBack<HomeOthersListVIewInfo>() {
            @Override
            public void onSuccess(HomeOthersListVIewInfo resultInfo, int requestCode) {
                List<HomeOthersListVIewInfo.DataEntity.ItemsEntity> items = resultInfo.getData().getItems();
                itemsEntityList.addAll(items);
                listViewAdapter.notifyDataSetChanged();
            }
        }, 1);

        listViewAdapter = new HomeOthersListViewAdapter(this , itemsEntityList);
        mListView.setAdapter(listViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoryStrategyDetailActivity.this, WebActivity.class);
                intent.putExtra("url", itemsEntityList.get(position).getContent_url());
                intent.putExtra("title", itemsEntityList.get(position).getTitle());
                intent.putExtra("image_url", itemsEntityList.get(position).getCover_webp_url());
                startActivity(intent);
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
