package com.example.dayfour;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ImageView animation;
    private TextView sj;
    private ConstraintLayout skip;
    private String ii= "一,二,三,四,五";
    private int i = 5;
    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 20) {
                if (i >= 0) {
                    sj.setText(i-- + "");
                } else {
                    timer.cancel();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        }
    };
    private Timer timer;
    private ObjectAnimator x;
    private ObjectAnimator y;
    private ObjectAnimator z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                han.sendEmptyMessage(20);
            }
        }, 1000, 1000);
    }

    private void initView() {
        button = (Button) findViewById(R.id.skip);
        animation = (ImageView) findViewById(R.id.animation);
        sj = (TextView) findViewById(R.id.sj);

        button.setOnClickListener(this);
        x = ObjectAnimator.ofFloat(animation, "translationX", 0f, 60f, 0f);
        y = ObjectAnimator.ofFloat(animation, "translationY", 0f, 60f, 0f);
        z = ObjectAnimator.ofFloat(animation, "alpha", 1f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(x).with(y).before(z);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip:
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                break;
        }
    }
}
