package project.etoury.com.eyepetizerdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class EyepetizerMenuAnimation implements View.OnClickListener{

    private  FrameLayout mFmAll;
    private View mEyepetizerMenuView;
    private ImageView mActionMenu;
    private Context mContext;

    private ObjectAnimator mMenuOpenAnimation;
    private ObjectAnimator mMenuCloseAnimation;
    private ObjectAnimator mActionMenuAnimation;

    private DecelerateInterpolator mInterpolator;

    public  boolean mIsMenuClose = true;
    private static final String ROTATION = "rotation";
    private static final String TRANSLATION = "translationY";
    private final ImageView mMenuMore;
    private final ImageView mUnFocus;
    private final ImageView mFocus;


    public EyepetizerMenuAnimation(EyepetizerMenuBuilder builder) {
        this.mContext = builder.context;
        this.mEyepetizerMenuView = builder.eyepetizerMenuView;
        this.mActionMenu = builder.actionMenu;
        this.mFmAll = builder.fm_all;
        mMenuMore = (ImageView) mFmAll.findViewById(R.id.iv_menu_more);
        mUnFocus = (ImageView) mFmAll.findViewById(R.id.iv_un_focus);
        mFocus = (ImageView) mFmAll.findViewById(R.id.iv_focus);
        this.mInterpolator = new DecelerateInterpolator();
        this.mMenuOpenAnimation = buildMenuOpenAnimation();
        this.mMenuCloseAnimation = buildMenuCloseAnimation();
        mActionMenu.setOnClickListener(this);
        if (mIsMenuClose) {
            mEyepetizerMenuView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        mActionMenuAnimation = ObjectAnimator.ofFloat(
                mActionMenu, ROTATION, mIsMenuClose ? 0 : 90, mIsMenuClose ? 90 : 0 );
        mActionMenuAnimation.setInterpolator(mInterpolator);
        mActionMenuAnimation.setDuration(350);
        if (mIsMenuClose) {
            initImageView(!mIsMenuClose);
            open();
        }else {
            initImageView(!mIsMenuClose);
            close();
        }
    }

    public void open() {

        mIsMenuClose = false;
        AnimatorSet set = new AnimatorSet();
        set.playTogether(mActionMenuAnimation, mMenuOpenAnimation);
        set.start();
    }

    public void close() {
        mIsMenuClose = true;
        AnimatorSet set = new AnimatorSet();
        set.playTogether(mActionMenuAnimation, mMenuCloseAnimation);
        set.start();
    }

    private ObjectAnimator buildMenuOpenAnimation() {
        ObjectAnimator menuOpenAnimation = ObjectAnimator.ofFloat(
                mEyepetizerMenuView, TRANSLATION, -ScreenUtils.getHeight(mContext), 0);
        menuOpenAnimation.setInterpolator(mInterpolator);
        menuOpenAnimation.setDuration(350);
        menuOpenAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mEyepetizerMenuView.setVisibility(View.VISIBLE);
            }
        });
        return menuOpenAnimation;
    }

    private ObjectAnimator buildMenuCloseAnimation() {
        ObjectAnimator menuCloseAnimation = ObjectAnimator.ofFloat(
                mEyepetizerMenuView, TRANSLATION, 0, -ScreenUtils.getHeight(mContext));
        menuCloseAnimation.setInterpolator(mInterpolator);
        menuCloseAnimation.setDuration(350);
        menuCloseAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mEyepetizerMenuView.setVisibility(View.GONE);
            }
        });
        return menuCloseAnimation;
    }

    public static class EyepetizerMenuBuilder {

        private FrameLayout fm_all;
        private View eyepetizerMenuView;
        private ImageView actionMenu;
        private Context context;

        public EyepetizerMenuBuilder(Context context, View eyepetizerMenuView, FrameLayout fm_all,ImageView actionMenu) {
            this.eyepetizerMenuView = eyepetizerMenuView;
            this.actionMenu = actionMenu;
            this.fm_all = fm_all;
            this.context = context;
        }

        public EyepetizerMenuAnimation build() {
            return new EyepetizerMenuAnimation(this);
        }

    }
    public void initImageView(boolean mIsMenuClose){
        if (mIsMenuClose){
            mUnFocus.setVisibility(View.VISIBLE);
            mFocus.setVisibility(View.GONE);
            mMenuMore.setVisibility(View.GONE);
        }else {
            mUnFocus.setVisibility(View.GONE);
            mFocus.setVisibility(View.GONE);
            mMenuMore.setVisibility(View.VISIBLE);
        }
    }

}
