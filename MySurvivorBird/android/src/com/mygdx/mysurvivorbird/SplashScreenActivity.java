package com.mygdx.mysurvivorbird;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mygdx.mysurvivorbird.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CountDownTimer countDownTimer = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                    Intent i = new Intent(SplashScreenActivity.this,AndroidLauncher.class);
                    startActivity(i);

                finish();
            }
        }.start();
    }
}