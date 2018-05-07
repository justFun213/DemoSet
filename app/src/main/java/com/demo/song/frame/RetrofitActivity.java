package com.demo.song.frame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.network.api.TestService;
import com.demo.song.utils.Constant;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {

    private final String TAG = "com.demo.song.frame.RetrofitActivity";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.content)
    TextView content;
    private Observable<ResponseBody> observable;
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL+"/demo/photos/")
                .build();
        observable = Observable.create(new Observable.OnSubscribe<ResponseBody>() {
            @Override
            public void call(Subscriber<? super ResponseBody> subscriber) {
                TestService testService = mRetrofit.create(TestService.class);
                Call<ResponseBody> call = testService.getTest();
                try {
                    subscriber.onNext(call.execute().body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void onClickButton1() {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String contentString=responseBody.string();
                            content.setText(contentString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @OnClick(R.id.button1)
    public void onViewClicked() {
        onClickButton1();
    }
}
