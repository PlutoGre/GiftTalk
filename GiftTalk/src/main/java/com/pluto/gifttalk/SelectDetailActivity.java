package com.pluto.gifttalk;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;

import butterknife.ButterKnife;

public class SelectDetailActivity extends BaseActivity {

    Toolbar mToolbar;

    ConvenientBanner mConvenientBanner;

    TextView tvName;
    TextView tvPrice;
    TextView tvDescription;

    RadioGroup mRadioGroup;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_detail);
        ButterKnife.bind(this);
    }
}
