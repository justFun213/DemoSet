package com.demo.song.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.demo.song.demoset.R;

public class WebviewTestActivity extends AppCompatActivity {

    private Button mButton;
    private WebView mWebView;
    private final String TAG="WebviewTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_test);
        mButton= (Button) findViewById(R.id.test);
        mWebView= (WebView) findViewById(R.id.webview);
        initWebView();
        setListener();
    }

    private void setListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("http://www.bilibili.com");
            }
        });
    }

    private void initWebView() {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG,"onPageStarted开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG,"onPageFinished加载完成");
            }
        });
    }
}
