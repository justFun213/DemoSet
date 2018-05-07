package com.demo.song.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.song.demoset.R;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }

    public void showDialog(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("哈哈，就测试一下").
                setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("AlertDialogActivity","确认");
                        Toast.makeText(AlertDialogActivity.this,"点了确认",Toast.LENGTH_SHORT).show();
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("AlertDialogActivity","取消");
                        Toast.makeText(AlertDialogActivity.this,"点了取消",Toast.LENGTH_LONG).show();
                    }
                }).
                create().
                show();
    }

    public void showProgressDialog(View view){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("登陆中...");
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(AlertDialogActivity.this, "登录成功!", Toast.LENGTH_LONG).show();
            }
        }, 2000);
    }



    public void showContentViewDialog(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final View contentView= LayoutInflater.from(this).inflate(R.layout.dialog_sign_up,null);
        final AlertDialog signUpDialog=builder.setView(contentView)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText userName= (EditText) contentView.findViewById(R.id.user_name);
                        EditText passWord= (EditText) contentView.findViewById(R.id.pass_word);
                        Toast.makeText(AlertDialogActivity.this,userName.getText().toString()+":"+
                        passWord.getText().toString(),Toast.LENGTH_SHORT).show();
                        showProgressDialog(null);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        signUpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        signUpDialog.show();
    }

    public void showSelectDialog(View view){
        final boolean[] selects={false,false,true,false};
        final String[] citys={"北京", "上海", "深圳", "广州"};
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("当前城市")
                .setItems(citys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selects[which] = true;
                        Toast.makeText(AlertDialogActivity.this, citys[which] + "被选中了", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        alertDialog.show();
    }

    public void showSigngleChoiceDialog(View view){
        final int[] select ={2};
        final String[] fruits={"苹果", "香蕉", "雪梨", "西瓜"};
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("请选择你喜欢的水果")
                .setSingleChoiceItems(fruits, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select[0] =which;
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, fruits[select[0]] + "被选中了", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        alertDialog.show();
    }

    public void showMultiDialog(View view){
        final boolean[] selects={false,false,true,false};
        final String[] citys={"苹果", "香蕉", "雪梨", "西瓜"};
        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("请选择你喜欢的水果")
                .setMultiChoiceItems(citys, selects, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selects[which]=isChecked;
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < selects.length; i++) {
                            if(selects[i]==true){
                                stringBuilder.append(citys[i]).append("、");
                            }
                        }
                        Toast.makeText(AlertDialogActivity.this,
                                stringBuilder.toString().substring(0,stringBuilder.length())+"被你选中了"
                        ,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        alertDialog.show();
    }

}
