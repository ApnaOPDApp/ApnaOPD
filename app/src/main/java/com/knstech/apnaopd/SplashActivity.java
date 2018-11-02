package com.knstech.apnaopd;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.knstech.apnaopd.Patient.HomeActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashActivity extends Activity implements Animation.AnimationListener{

    private CircleImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(Build.VERSION.SDK_INT < 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
        else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

       img = (CircleImageView) findViewById(R.id.img_splash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanimation);
        animation.setAnimationListener(this);
        img.setAnimation(animation);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
