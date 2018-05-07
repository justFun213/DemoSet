package com.demo.song.frame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.song.demoset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends AppCompatActivity {
    @BindView(R.id.about_us)
    LinearLayout mAboutUs;
    @BindView(R.id.exit)
    LinearLayout mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.about_us,R.id.exit,R.id.video_cache})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.about_us:
                Toast.makeText(this, "关于我们！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                Toast.makeText(this, "退出登录！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_cache:
                Toast.makeText(this, "视频缓存！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
