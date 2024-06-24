package com.vrushabhhirap.quickcart;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        animate_fadeIn_moveX();

        //this below code is setting the splash screen for the 3 secs
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                SplashScreenActivity.this.startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }

    private void animate_fadeIn_moveX() {
        // Find the cart logo ImageView
        View cartlogo = findViewById(R.id.QuickCartLogo);
        View splashscreen = findViewById(R.id.SplashScreen);


        // Create ObjectAnimators for translationX and translationY
        ObjectAnimator move_x_axis = ObjectAnimator.ofFloat(cartlogo,"TranslationX",400f,0f);
        move_x_axis.setDuration(1000);
        move_x_axis.setInterpolator(new AccelerateDecelerateInterpolator());


        // Create ObjectAnimators for fade in effect
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(splashscreen,"fadeIn",0f,1f);
        fadeIn.setDuration(1000);
        fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());

        // Combine animations into an AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(move_x_axis,fadeIn);
        animatorSet.start();

    }
}