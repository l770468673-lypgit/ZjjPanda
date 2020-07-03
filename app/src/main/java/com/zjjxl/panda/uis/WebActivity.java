package com.zjjxl.panda.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zjjxl.panda.R;
import com.zjjxl.panda.apps.XLBaseActivity;
import com.zjjxl.panda.utils.LUtils;

public class WebActivity extends XLBaseActivity {

    private WebView mWeb_textword;
    private String TAG = "WebActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_web);
        int lodaweb = getIntent().getIntExtra("lodaweb", 1);
        initView();
        loadDate(lodaweb);
    }

    private void initView() {
        mWeb_textword = findViewById(R.id.web_textword);
        WebSettings setting = mWeb_textword.getSettings();
        setting.setJavaScriptEnabled(true);//支持Js
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);//缓存模式
        //是否支持画面缩放，默认不支持
        setting.setBuiltInZoomControls(true);
        setting.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setting.setDomStorageEnabled(true);
        setting.setUserAgentString("true");
        //是否显示缩放图标，默认显示
        setting.setDisplayZoomControls(false);
        //设置网页内容自适应屏幕大小 LayoutAlgorithm. AUTOSIZING
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);//注意网上例程很多的是.SINGLE_COLUMN，但调试时发现移动版网站会错位，所以改成									       //SINGLE_COLUMN
        }
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);

    }

    private void loadDate(int lodaweb) {
        if (lodaweb == 1) {
            mWeb_textword.loadUrl("http://panda.stone3a.com/foreign/toPrivacyPolicyPage.jspx");
        } else if (lodaweb == 2) {
            mWeb_textword.loadUrl("http://panda.stone3a.com/foreign/toRegisterAgreementPage.jspx");
        }


        //        mFragment_travel_webview.loadUrl(getArguments().getString("MTrippic"));
        mWeb_textword.setWebViewClient(new WebViewClient());
        mWeb_textword.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress != 100) {
                    //mProgressBar.setProgress(newProgress);
                } else {
                    //mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWeb_textword.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                LUtils.i(TAG, "onReceivedError 证书问题");
            }
        });

    }
}
