package id.co.iak.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mochadwi on 5/4/17.
 */

public class SplashActivity extends AppCompatActivity {

	// Views Binding
	@BindView(R.id.img_splash)
	ImageView imgSplash;
	@BindView(R.id.txt_welcome)
	TextView txtWelcome;

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ButterKnife.bind(this);

		Glide.with(this).load(R.drawable.splash).into(imgSplash);

		new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				startActivity(new Intent(SplashActivity.this, MainActivity.class));

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
