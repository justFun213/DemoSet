package com.demo.song.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.service.service.MyService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener,MyService.MusicShowListener {

    private Button startService;
    private Button bindService;
    private Button unBindService;
    private Button startMusic;
    private Button stopMusic;
    private Button stopService;
    private Button clearView;
    private MyService.MusicPlayer musicPlayer;
    private boolean isBind=false;
    private int contentCount;
    private LinearLayout contentShow;
    private TextView temp;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("ServiceActivity", "onServiceConnected"+name);
            musicPlayer= (MyService.MusicPlayer) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("ServiceActivity", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startService= (Button) findViewById(R.id.start_service);
        bindService= (Button) findViewById(R.id.bind_service);
        unBindService= (Button) findViewById(R.id.unbind_service);
        stopMusic=(Button) findViewById(R.id.stop_music);
        startMusic=(Button) findViewById(R.id.start_music);
        stopService=(Button)findViewById(R.id.stop_service);
        clearView= (Button) findViewById(R.id.clear_view);
        contentShow= (LinearLayout) findViewById(R.id.content_display);
        temp= (TextView) findViewById(R.id.temp_test);
        startService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unBindService.setOnClickListener(this);
        startMusic.setOnClickListener(this);
        stopMusic.setOnClickListener(this);
        stopService.setOnClickListener(this);
        clearView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, MyService.class);
        switch (v.getId()){
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                isBind=bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                if(isBind){
                musicPlayer.stopMusic();
                unbindService(serviceConnection);
                isBind=false;
                musicPlayer.onDestroy();
                musicPlayer=null;
                }
                break;
            case R.id.start_music:
                initShowData();
                if(musicPlayer!=null){
                    musicPlayer.startMusic(this);
                }
                break;
            case R.id.stop_music:
                if(musicPlayer!=null){
                    musicPlayer.stopMusic();
                }
                break;
            case R.id.clear_view:
                contentShow.removeAllViews();
                break;
            default:
                break;
        }
    }

    private void initShowData() {
        float totalHeight=contentShow.getHeight();
        contentCount= (int) (totalHeight/temp.getMeasuredHeight());
        contentShow.removeAllViews();
        Log.d("ServiceActivity", "contentCount: "+contentCount);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ServiceActivity","onDestroy");
        if(musicPlayer!=null){
            musicPlayer.stopMusic();
            unbindService(serviceConnection);
        }
    }

    @Override
    public void showMusic(String msg) {
        if(contentShow.getChildCount()<contentCount){
            contentShow.addView(getTextView(msg));
        }else{
            contentShow.removeViewAt(0);
            contentShow.addView(getTextView(msg));
        }
    }

    public TextView getTextView(String msg){
        TextView temp=new TextView(this);
        temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        temp.setText(msg);
        return temp;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
