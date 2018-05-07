package com.demo.song.service.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG="com.demo.song.service.service";
    private MusicPlayer musicPlayer=new MusicPlayer();

    public MyService() {

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: MyService");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: MyService");
        final Handler handler=new Handler();
        new Runnable() {
            private int count=0;
            @Override
            public void run() {
                if(count>=10)
                {return;}
                handler.postDelayed(this, 1000);
                count++;
                Log.d(TAG, "run: 我在优雅的计数啊"+count);
            }
        }.run();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: MyService");
        super.onDestroy();
    }



    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: MyService");
        if(musicPlayer==null){
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }
        return musicPlayer;
    }

    public static class MusicPlayer extends Binder implements Runnable {
        private int musicTime;
        private Handler handler=new Handler();
        private MusicShowListener musicShow;

        @Override
        public void run() {
            Log.d(TAG, "bind run: 累计播放时间是:"+musicTime);
            musicShow.showMusic("累计播放时间是:" + musicTime);
            musicTime++;
            handler.postDelayed(this, 1000);
        }

        public void startMusic(Activity activity){
            if(!(activity instanceof MusicShowListener)){
                throw new RuntimeException(activity.toString()+"must  implent MusicShowListener");
            }
            musicShow= (MusicShowListener) activity;
            handler.removeCallbacks(this);
            Log.d(TAG, "startMusic: 开始音乐播放");
            musicShow.showMusic("开始音乐播放");
            run();
        }

        public void stopMusic(){
            Log.d(TAG, "stopMusic: 停止音乐播放");
            musicShow.showMusic("停止音乐播放");
            handler.removeCallbacks(this);
        }

        public void onDestroy(){
            musicShow=null;
        }
    }

    public static interface MusicShowListener {
        public void showMusic(String progress);
    }


}
