package com.pluto.gifttalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    @Bind(R.id.web_view_detail)
    WebView mWebView;
    @Bind(R.id.iv_web_view_icon)
    ImageView mImageView;
    @Bind(R.id.tv_web_view)
    TextView mTextView;

    @Bind(R.id.ib_web_view_back)
    ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String imageUrl = intent.getStringExtra("image_url");
        String title = intent.getStringExtra("title");
        mWebView.loadUrl(url);
        mTextView.setText(title);
        Picasso.with(this).load(imageUrl).into(mImageView);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(true);

        mImageButton.setOnClickListener(new View.OnClickListener() {
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
