package com.demo.song.fragment;

import android.app.ActivityThread;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.fragment.fragment.TestFragment;

public class FragmentDemoActivity extends AppCompatActivity implements TestFragment.OnFragmentInteractionListener {

    private TestFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        mFragment=TestFragment.newInstance("活活","哈哈");
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mFragment);
        transaction.commit();
    }

    @Override
     public void onFragmentInteraction(String content) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
