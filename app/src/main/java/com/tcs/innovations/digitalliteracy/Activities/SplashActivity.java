package com.tcs.innovations.digitalliteracy.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcs.innovations.digitalliteracy.R;

public class SplashActivity extends Activity {

    ImageView splash;
    TextView title, sub_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash = (ImageView) findViewById(R.id.splash);
        title = (TextView) findViewById(R.id.title);
        sub_title = (TextView) findViewById(R.id.sub_title);

        Typeface title_type = Typeface.createFromAsset(getAssets(), "tech.ttf");
        title.setTypeface(title_type);
        sub_title.setTypeface(title_type);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}
