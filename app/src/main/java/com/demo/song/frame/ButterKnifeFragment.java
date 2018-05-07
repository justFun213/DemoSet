package com.demo.song.frame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.demo.song.demoset.R;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButterKnifeFragment extends Fragment {


    @BindView(R.id.video_cache)
    LinearLayout videoCache;
    @BindView(R.id.recommend_friends)
    LinearLayout recommendFriends;
    @BindView(R.id.advices)
    LinearLayout advices;
    @BindView(R.id.user_protecol)
    LinearLayout userProtecol;
    @BindView(R.id.about_us)
    LinearLayout aboutUs;
    @BindView(R.id.exit)
    LinearLayout exit;
    @BindView(R.id.personal_data)
    LinearLayout personalData;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.activity_butter_knife, container, false);
        unbinder = butterknife.ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.about_us, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.about_us:
                break;
            case R.id.exit:
                break;
        }
    }
}
