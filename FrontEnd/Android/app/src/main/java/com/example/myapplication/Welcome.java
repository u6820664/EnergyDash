package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class Welcome extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_welcome, null);
        setContentView(view);
        //setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//hide title bar
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(Welcome.this,MainActivity.class);
                Intent intent = new Intent(Welcome.this,Login.class);
                Welcome.this.startActivity(intent);
                Welcome.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
        AlphaAnimation aa = new AlphaAnimation(0.7f,1.0f);
        //动画持续时间
        aa.setDuration(2000);
        //使用View的startAnimation开始执行动画
        view.startAnimation(aa);

        /*aa.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation arg0) {
                startActivity(new Intent(Welcome.this, Login.class));
                finish();
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}

        });

         */
    }

}
