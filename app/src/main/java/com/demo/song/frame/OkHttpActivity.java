package com.demo.song.frame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.model.Photos;
import com.demo.song.model.TitleAndPhoto;
import com.demo.song.utils.Constant;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = "com.demo.song.frame.OkHttpActivity";
    @BindView(R.id.get_content)
    Button getContent;
    @BindView(R.id.image_list)
    LinearLayout imageList;
    @BindView(R.id.get_content_cache)
    Button getContentCache;
    private Observable<Photos> observable;
    @BindView(R.id.content)
    TextView mContent;
    private Photos photos;
    private OkHttpClient client;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Log.e(TAG, "networkInterceptors-size:"+new OkHttpClient.Builder().networkInterceptors().size());  //0
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(new Cache(new File(getExternalFilesDir(
                        Environment.DIRECTORY_PICTURES), "OkHttpCache"), 10 * 10 * 1024))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response;
                    }
                });

        Log.e(TAG, "networkInterceptors-size: "+builder.networkInterceptors().size());      //0

        builder=builder.addNetworkInterceptor(new CacheInterceptor());

        Log.e(TAG, "networkInterceptors-size:"+builder.networkInterceptors().size()+"  interceptors-size:"+builder.interceptors().size());       //1q

        client=builder.build();
        observable = Observable.create(new Observable.OnSubscribe<Photos>() {
            @Override
            public void call(Subscriber<? super Photos> subscriber) {
                printThreadLog("call(Subscriber<? super Photos> subscriber)");
                Request request = new Request.Builder()
                        .url(Constant.URL + "/demo/photos/photos.json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.e(TAG, "lala cacheResponse " + response.cacheResponse());
                    Log.e(TAG, "lala networkResponse " + response.networkResponse());
                    printHeadrs(response);
                    Gson gson = new Gson();
                    Photos photos = gson.fromJson(response.body().string(), Photos.class);
                    subscriber.onNext(photos);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "请求photos.json失败！");
                }
            }
        });
    }

    @OnClick(R.id.get_content)
    public void onViewClicked() {
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Func1<Photos, Observable<Photos.Photo>>() {
                    @Override
                    public Observable<Photos.Photo> call(final Photos photos) {
                        printThreadLog("flatMap");
                        return Observable.from(photos.getData());
                    }
                })
                .map(new Func1<Photos.Photo, TitleAndPhoto>() {
                    @Override
                    public TitleAndPhoto call(Photos.Photo photo) {
                        printThreadLog("map");
                        Request request = new Request.Builder()
                                .url(Constant.URL + photo.getUrl())
                                .build();
                        Response response = null;
                        try {
                            response = client.newCall(request).execute();
                        /*    Log.e(TAG, "lala cacheResponse " + response.cacheResponse());
                            Log.e(TAG, "lala networkResponse " + response.networkResponse());
                            printHeadrs(response);*/
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "请求图片失败！");
                        }
                        Bitmap bitmap = response == null ? null : BitmapFactory.decodeStream(response.body().byteStream());
                        TitleAndPhoto temp = new TitleAndPhoto(photo.getTitle(), bitmap);
                        return temp;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TitleAndPhoto>() {
                    @Override
                    public void call(TitleAndPhoto titleAndPhoto) {
                        printThreadLog("onNext(TitleAndPhoto titleAndPhoto)");
                        Log.e(TAG, "计算了" + count + "次");
                        count++;
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                        TextView textView = new TextView(OkHttpActivity.this);
                        textView.setLayoutParams(layoutParams);
                        ImageView imageView = new ImageView(OkHttpActivity.this);
                        imageView.setLayoutParams(layoutParams);
                        textView.setText(titleAndPhoto.getTitle());
                        imageView.setImageBitmap(titleAndPhoto.getPhoto());
                        imageList.addView(textView);
                        imageList.addView(imageView);
                    }
                });
    }

    public static void printThreadLog(String methodTag) {
        Log.e(TAG, methodTag + " current thread:" + Thread.currentThread().getName());
    }

    public static void printHeadrs(Response response){
        Headers headers=response.headers();
        Log.e(TAG,  "lalal: "+response.toString());
        for(int i=0;i<headers.size();i++){
            Log.e(TAG,  "lalal: "+headers.name(i)+":" + headers.value(i));
        }
    }


    @OnClick(R.id.get_content_cache)
    public void onViewClick() {
        final Request request=new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Response response=client.newCall(request).execute();
                    Log.e(TAG, "lala cacheResponse " + response.cacheResponse());
                    Log.e(TAG, "lala networkResponse " + response.networkResponse());
                    printHeadrs(response);
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        TextView textView = new TextView(OkHttpActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        textView.setLayoutParams(layoutParams);
                        textView.setText(s);
                        imageList.addView(textView);
                    }
                });
    }

    static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "max-age=" + 3600)
                    .build();
            return response1;
        }
    }

}
