package com.demo.song.animate;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.demo.song.demoset.R;

public class AnimateActivity extends AppCompatActivity {

    private ImageView runningMan;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animate_list);
        runningMan= (ImageView) findViewById(R.id.people_run);
        imageView= (ImageView) findViewById(R.id.animate_image);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable animationDrawable= (AnimationDrawable) runningMan.getDrawable();
        animationDrawable.start();
    }

    public void rotate(View view){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.rotate_test);
        imageView.startAnimation(animation);
    }

    public void alpha(View view){
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.alpha_test);
        imageView.startAnimation(animation);
    }

    public void translate(View view){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.translate_test);
        imageView.startAnimation(animation);
    }

    public void scale(View view){
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.scale_test);
        imageView.startAnimation(animation);
    }

}
