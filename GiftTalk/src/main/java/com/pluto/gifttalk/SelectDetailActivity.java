package com.pluto.gifttalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.pluto.adapter.CategoryFragmentPagerAdapter;
import com.pluto.bean.SelectDetailInfo;
import com.pluto.fragment.SelectDetailCommentFragment;
import com.pluto.fragment.SelectDetailIntroduceFragment;
import com.pluto.http.IOkCallBack;
import com.pluto.http.OkHttpTools;
import com.pluto.http.UrlConfig;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectDetailActivity extends BaseActivity {

    @Bind(R.id.cb_select_detail)
    ConvenientBanner mConvenientBanner;
    private List<String> convenientBannerImgUrlList = new ArrayList<>();

    @Bind(R.id.tv_select_detail_name)
    TextView tvName;
    @Bind(R.id.tv_select_detail_price)
    TextView tvPrice;
    @Bind(R.id.tv_select_detail_description)
    TextView tvDescription;

    @Bind(R.id.rg_select_detail)
    RadioGroup mRadioGroup;

    @Bind(R.id.tv_select_detail_introduce_line)
    TextView tvIntroduce;
    @Bind(R.id.tv_select_detail_comment_line)
    TextView tvComment;

    @Bind(R.id.vp_select_detail)
    ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private CategoryFragmentPagerAdapter fragmentPagerAdapter;

    @Bind(R.id.sv_select_detail)
    NestedScrollView mScrollView;
    @Bind(R.id.fl_select_detail_toolbar)
    FrameLayout mFrameLayout;

    @Bind(R.id.ib_select_detail_back)
    ImageButton ibBack;
    @Bind(R.id.ib_select_detail_share)
    ImageButton ibShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = UrlConfig.SELECT_DETAIL_URL + id;

        OkHttpTools.newInstance().okGet(url, SelectDetailInfo.class
                , new IOkCallBack<SelectDetailInfo>() {
            @Override
            public void onSuccess(SelectDetailInfo resultInfo, int requestCode) {
                convenientBannerImgUrlList.addAll(resultInfo.getData().getWebp_urls());
                mConvenientBanner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new BannerViewHolder();
                    }
                }, convenientBannerImgUrlList)
                        .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

                tvName.setText(resultInfo.getData().getName());
                tvPrice.setText("￥" + resultInfo.getData().getPrice());
                tvDescription.setText(resultInfo.getData().getDescription());

                Log.d("heyang2", "onSuccess: ----------------->" + resultInfo.getData().getDetail_html());
                fragmentList.add(SelectDetailIntroduceFragment.newInstance(resultInfo.getData().getDetail_html(), null));
                fragmentList.add(SelectDetailCommentFragment.newInstance(resultInfo.getData().getId() + "" , null));
                fragmentPagerAdapter.notifyDataSetChanged();

            }
        }, 1);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_select_detail_introduce:
                        tvIntroduce.setVisibility(View.VISIBLE);
                        tvComment.setVisibility(View.INVISIBLE);
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_select_detail_comment:
                        tvComment.setVisibility(View.VISIBLE);
                        tvIntroduce.setVisibility(View.INVISIBLE);
                        mViewPager.setCurrentItem(1);
                        break;
                }
            }
        });

        fragmentPagerAdapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager() , fragmentList);
        mViewPager.setAdapter(fragmentPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton) mRadioGroup.getChildAt(2)).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mFrameLayout.setBackgroundColor(0xffff2d47);
                float scroll = scrollY / 600f;
                mFrameLayout.setAlpha(scroll);
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
    protected void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }



    class BannerViewHolder implements Holder<String> {

        ImageView mBannerImageView;

        @Override
        public View createView(Context context) {
            mBannerImageView = new ImageView(SelectDetailActivity.this);
            mBannerImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return mBannerImageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(context).load(data).into(mBannerImageView);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
