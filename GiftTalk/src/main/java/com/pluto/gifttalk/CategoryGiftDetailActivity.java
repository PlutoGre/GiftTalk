package com.pluto.gifttalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pluto.adapter.CategoryGiftDetailAdapter;
import com.pluto.adapter.SelectGridViewAdapter;
import com.pluto.bean.CategoryGiftDetailInfo;
import com.pluto.bean.SelectGridViewInfo;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;
import com.pluto.http.UrlConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryGiftDetailActivity extends BaseActivity {

    @Bind(R.id.gv_category_gift_click_detail)
    GridView mGridView;
    private List<CategoryGiftDetailInfo.DataEntity.ItemsEntity> dataEntity1List =
            new ArrayList<>();
    private CategoryGiftDetailAdapter gridViewAdapter;

    @Bind(R.id.ib_category_gift_back)
    ImageButton ibBack;
    @Bind(R.id.ib_ib_category_gift_sort)
    ImageButton ibSort;
    @Bind(R.id.tv_category_gift_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_gift_detail);
        ButterKnife.bind(this);

        gridViewAdapter = new CategoryGiftDetailAdapter(this , dataEntity1List);
        mGridView.setAdapter(gridViewAdapter);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        tvTitle.setText(name);
        String url = UrlConfig.CATEGORY_GIFT_DETAIL_URL_1 + id + UrlConfig.CATEGORY_GIFT_DETAIL_URL_2;

        Log.d("heyang3", "onCreate: url----------->" + url);
        OkHttpTools.newInstance().okGet(url, CategoryGiftDetailInfo.class
                , new IOkCallBack<CategoryGiftDetailInfo>() {
            @Override
            public void onSuccess(CategoryGiftDetailInfo resultInfo, int requestCode) {
                List<CategoryGiftDetailInfo.DataEntity.ItemsEntity> items = resultInfo.getData().getItems();
                dataEntity1List.addAll(items);
                gridViewAdapter.notifyDataSetChanged();
            }
        }, 1);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CategoryGiftDetailActivity.this, SelectDetailActivity.class);
                intent.putExtra("id", dataEntity1List.get(position).getId() + "");
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
