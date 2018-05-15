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

    private Button button;
    private WebView webView;
    private final String TAG="WebviewTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_test);
        button= (Button) findViewById(R.id.test);
        webView= (WebView) findViewById(R.id.webview);
        initWebView();
        setListener();
    }

    private void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://www.baidu.com");
            }
        });
    }

    private void initWebView() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG,"onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG,"onPageFinished");
            }
        });
    }
}
