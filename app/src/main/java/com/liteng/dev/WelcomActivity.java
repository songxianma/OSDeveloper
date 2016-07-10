package com.liteng.dev;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.liteng.dev.base.AuthoriseActivity;

public class WelcomActivity extends AppCompatActivity {

    private RelativeLayout welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        welcome = (RelativeLayout) this.findViewById(R.id.welcome);


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,0.9f);
        alphaAnimation.setDuration(3000);
        welcome.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomActivity.this);
                builder.setTitle("注意");
                builder.setMessage("本应用采用开源中国(OS China)的开放接口,所以请先使用开源中国账号登陆");
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(WelcomActivity.this,AuthoriseActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.create().show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
