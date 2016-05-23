package project.etoury.com.eyepetizerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ImageView mImageView;
    private View mEyepetizerMenuView;
    private FrameLayout fm_all;
    private EyepetizerMenuAnimation menuBuide;
    private RecyclerView rcv;
    private ArrayList<ItemObj> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        frameLayout = (FrameLayout) findViewById(R.id.view_layout);
        initData();
        mImageView = (ImageView) findViewById(R.id.iv_action_menu);
        ImageView mUnFocus = (ImageView) findViewById(R.id.iv_un_focus);
        ImageView mFocus = (ImageView) findViewById(R.id.iv_focus);
        ImageView mMenuMore = (ImageView) findViewById(R.id.iv_menu_more);
        mUnFocus.setVisibility(View.VISIBLE);
        mFocus.setVisibility(View.GONE);
        mMenuMore.setVisibility(View.GONE);

        rcv = (RecyclerView) findViewById(R.id.rcv);
        fm_all = (FrameLayout) findViewById(R.id.fm_all);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEyepetizerMenuView = LayoutInflater.from(this).inflate(R.layout.setting_view, null);
        frameLayout.addView(mEyepetizerMenuView);
        menuBuide = new EyepetizerMenuAnimation.EyepetizerMenuBuilder(
                this, mEyepetizerMenuView, fm_all, mImageView)
                .build();
        rcv.setLayoutManager(new LinearLayoutManager(this));
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this, myList);
        rcv.setAdapter(myViewPagerAdapter);
    }

    private void initData() {
        ItemObj itemObj = new ItemObj();
        itemObj.name =  "复仇者联盟";
        itemObj.iv = R.drawable.quanjia;
        myList.add(itemObj);

        ItemObj itemObj1 = new ItemObj();
        itemObj1.name =  "美国队长";
        itemObj1.iv = R.drawable.duizhang;
        myList.add(itemObj1);

        ItemObj itemObj2 = new ItemObj();
        itemObj2.name =  "钢铁侠";
        itemObj2.iv = R.drawable.gangtie;
        myList.add(itemObj2);


        ItemObj itemObj3 = new ItemObj();
        itemObj3.name =  "黑寡妇";
        itemObj3.iv = R.drawable.guafu;
        myList.add(itemObj3);


        ItemObj itemObj4 = new ItemObj();
        itemObj4.name =  "无敌浩克";
        itemObj4.iv = R.drawable.haoke;
        myList.add(itemObj4);
        ItemObj itemObj5 = new ItemObj();
        itemObj5.name =  "绿箭侠";
        itemObj5.iv = R.drawable.lvjian;
        myList.add(itemObj5);
        ItemObj itemObj6 = new ItemObj();
        itemObj6.name =  "雷神托尔";
        itemObj6.iv = R.drawable.leishen;
        myList.add(itemObj6);
    }

    @Override
    public void onBackPressed() {
        if (menuBuide.mIsMenuClose){
               finish();
        }else {
            menuBuide.close();
        }
    }
    public class ItemObj{
        public int iv;
        public String name;
    }
}
