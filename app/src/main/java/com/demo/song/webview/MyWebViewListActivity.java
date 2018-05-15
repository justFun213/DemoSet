package com.demo.song.webview;

import com.demo.song.demoset.list.BaseListActivity;

public class MyWebViewListActivity extends BaseListActivity {
    @Override
    public String[] getData() {
        return new String[]{"WebviewTestActivity"};
    }

    @Override
    public Class[] getClasses() {
        return new Class[]{WebviewTestActivity.class};
    }
}
