package project.etoury.com.eyepetizerdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class SplashActivity extends AppCompatActivity {

    private ImageView iv_bk;
    private ScaleAnimation ScaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = this.getWindow();
        window.setFlags(flag, flag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitivty);
        iv_bk = (ImageView) findViewById(R.id.iv_bk);
        ScaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ScaleAnimation.setDuration(2000);//设置动画持续时间
        ScaleAnimation.setFillAfter(true); // 动画结束后停留在结束的位置
        ScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_left);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        GlideDrawableImageViewTarget glideDrawableImageViewTarget = new GlideDrawableImageViewTarget(iv_bk){
            @Override
            public void onResourceReady(GlideDrawable resource, final GlideAnimation<? super GlideDrawable> animation) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        iv_bk.startAnimation(ScaleAnimation);
                    }

                }, 1000);
                super.onResourceReady(resource, animation);
            }
        };
        Glide.with(this).load(R.drawable.dui).centerCrop().into(glideDrawableImageViewTarget);
    }
}
