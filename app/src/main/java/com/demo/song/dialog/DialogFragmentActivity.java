package com.demo.song.dialog;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demo.song.demoset.R;
import com.demo.song.dialog.dialog.InputBottomDialog;

public class DialogFragmentActivity extends AppCompatActivity implements InputBottomDialog.InputDialogListener {

    private TextView mContent;
    private InputBottomDialog dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        mContent= (TextView) findViewById(R.id.tv_showtext);
        findViewById(R.id.btn_showdialogfragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogFragment == null) {
                    dialogFragment = InputBottomDialog.newInstance("回复鸣人");
                }
                dialogFragment.show(getFragmentManager(), "");
            }
        });
    }

    @Override
    public void sendMeassage(String message) {
        mContent.setText(message);
        dialogFragment.dismiss();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogFragment.hideProgressDialog();
            }
        }, 1000);
    }
}
