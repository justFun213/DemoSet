package com.demo.song.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.fragment.fragment.TestFragment;

public class FragmentChangeActivity extends AppCompatActivity implements View.OnClickListener,TestFragment.OnFragmentInteractionListener{

    private Button button1;
    private Button button2;
    private Button button3;
    private FrameLayout frameLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_change);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        title= (TextView) findViewById(R.id.fragment_title);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        TestFragment fragment=TestFragment.newInstance("朋友圈",null);
        transaction.add(R.id.fragment_container,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        String textContent=null;
        switch (v.getId()){
            case R.id.button1:
                textContent="朋友圈";
                break;
            case R.id.button2:
                textContent="联系人";
                break;
            case R.id.button3:
                textContent="动态";
                break;
            default:
                break;
        }
        Fragment fragment= TestFragment.newInstance(textContent,null);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(String content) {
        title.setText(content);
    }
}
