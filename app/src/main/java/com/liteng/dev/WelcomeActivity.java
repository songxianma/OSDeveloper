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
import com.liteng.dev.home.MainActivity;
import com.liteng.dev.utils.SPUtils;

public class WelcomeActivity extends AppCompatActivity {


    private final String FIRST_ENTER = "first_enter";
    private RelativeLayout welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = (RelativeLayout) this.findViewById(R.id.welcome);


        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,0.9f);
        alphaAnimation.setDuration(2000);
        welcome.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!SPUtils.getSp().getBoolean(FIRST_ENTER)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                    builder.setTitle("注意");
                    builder.setMessage("本应用采用开源中国(OS China)的开放接口,所以请先使用开源中国账号登陆");
                    builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (!SPUtils.getSp().getBoolean(FIRST_ENTER)) {
                                startActivity(new Intent(WelcomeActivity.this, AuthoriseActivity.class));
                            } else {
                                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            }

                            SPUtils.getSp().saveTrue(FIRST_ENTER);

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
                }else{
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
