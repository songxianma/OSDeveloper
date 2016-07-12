package com.liteng.dev.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liteng on 16/7/11.
 */
public class BaseActivity extends AppCompatActivity {

    ProgressDialog dialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected  void showLoading(){
        if(dialog == null){
            dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }
    }

    protected  void dismissLoading(){
        if(dialog != null){
            dialog.dismiss();
        }
    }

}
