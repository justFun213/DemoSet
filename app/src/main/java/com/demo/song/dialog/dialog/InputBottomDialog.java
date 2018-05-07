package com.demo.song.dialog.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.song.demoset.R;

import java.util.IllegalFormatException;

/**
 * Created by song on 2017/6/20.
 */
public class InputBottomDialog extends DialogFragment {
    public static final String PARAM_NAME="replyname";
    private EditText mContent;
    private TextView mSend;
    private InputDialogListener mListener;
    private ProgressDialog progressDialog;
    private String name;
    private String tempContent;

    public interface InputDialogListener{
        public void sendMeassage(String message);
    }

    public static InputBottomDialog newInstance(String userName){
        Bundle bundle=new Bundle();
        bundle.putString(PARAM_NAME,userName);
        InputBottomDialog dialog=new InputBottomDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            name=getArguments().getString(PARAM_NAME);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input_bottom,null);
        mContent= (EditText) view.findViewById(R.id.input_edit);
        mSend= (TextView) view.findViewById(R.id.send);
        Dialog dialog=new Dialog(getActivity(),R.style.BottomDialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        lp.alpha = 1;
        lp.dimAmount = 0.5f;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContent.setHint(name == null ? "" :name);
        getListener();
        setViewListener();
        if(tempContent!=null&&tempContent.length()>0)       //缓存的文字
        {
            mContent.setText(tempContent);
            mContent.setSelection(tempContent.length());
        }
        return dialog;
    }

    private void setViewListener() {
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tempContent=s.toString();
                if (s.length() > 0) {
                    mSend.setBackgroundResource(R.drawable.send_full);
                } else {
                    mSend.setBackgroundResource(R.drawable.send_normal);
                }
            }
        });
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempContent==null||tempContent.length()==0){
                    Toast.makeText(getActivity(),"当前内容为空~",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog=new ProgressDialog(getActivity());
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();
                mListener.sendMeassage(tempContent);
                tempContent=null;
                hideSoftkeyboard();
            }
        });
    }

    public void hideSoftkeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {

        }
    }

    public void hideProgressDialog(){
        progressDialog.cancel();
    }

    private void getListener() {
        if(getActivity() instanceof InputDialogListener){
            mListener= (InputDialogListener) getActivity();
        }else{
            throw new RuntimeException(getActivity()+" must implent InputBottomDialog.InputDialogListener");
        }
    }
}
