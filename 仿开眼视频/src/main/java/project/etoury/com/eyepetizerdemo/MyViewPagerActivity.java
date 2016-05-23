package project.etoury.com.eyepetizerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;

/**
 * Created by shaoze on 2016/5/23.
 */
public class MyViewPagerActivity extends AppCompatActivity {

    private View mEyepetizerMenuView;
    private FrameLayout frameLayout;
    private EyepetizerMenuAnimation menuBuide;
    private FrameLayout fm_all;
    private ImageView mImageView;
    private ImageView mIvPic;
    private ExitActivityTransition exitTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_viewpager);
        setTitle("");
        Bundle extras = getIntent().getExtras();
        int picUrl = (int) extras.get("picUrl");
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        exitTransition = ActivityTransition.with(getIntent()).to(mIvPic).start(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setTheme(R.style.AppTheme_NoActionBar);
            }
        }, 1000);
        Glide.with(this).load(picUrl).into(mIvPic);
        frameLayout = (FrameLayout) findViewById(R.id.view_layout);
        fm_all = (FrameLayout) findViewById(R.id.fm_all);
        mImageView = (ImageView) findViewById(R.id.iv_action_menu);
        ImageView mUnFocus = (ImageView) findViewById(R.id.iv_un_focus);
        ImageView mFocus = (ImageView) findViewById(R.id.iv_focus);
        ImageView mMenuMore = (ImageView) findViewById(R.id.iv_menu_more);
        mUnFocus.setVisibility(View.GONE);
        mFocus.setVisibility(View.VISIBLE);
        mMenuMore.setVisibility(View.GONE);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEyepetizerMenuView = LayoutInflater.from(this).inflate(R.layout.setting_view, null);
        frameLayout.addView(mEyepetizerMenuView);
        menuBuide = new EyepetizerMenuAnimation.EyepetizerMenuBuilder(
                this, mEyepetizerMenuView, fm_all, mImageView)
                .build();
    }

    @Override
    public void onBackPressed() {
        if (menuBuide.mIsMenuClose){
            exitTransition.exit(this);
        }else {
            menuBuide.close();
        }
    }
}
