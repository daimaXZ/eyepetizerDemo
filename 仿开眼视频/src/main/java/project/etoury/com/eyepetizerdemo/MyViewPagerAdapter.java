package project.etoury.com.eyepetizerdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

import java.util.ArrayList;

/**
 * Created by shaoze on 2016/5/23.
 */
public class MyViewPagerAdapter extends RecyclerView.Adapter<MyViewPagerAdapter.MyViewHolder>{

    private final Context context;
    private final ArrayList<MainActivity.ItemObj> list;

    public MyViewPagerAdapter(Context context, ArrayList<MainActivity.ItemObj> list) {
         this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rev_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewPagerAdapter.MyViewHolder holder, int position) {
        MainActivity.ItemObj itemObj = list.get(position);
        final int iv = itemObj.iv;
        String name = itemObj.name;
        Glide.with(context).load(iv).into(holder.mIvItem);
        holder.mTvName.setText(name);
//        holder.mRlAll.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction()==event.ACTION_DOWN){
//                    ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(holder.mRlAll, "alpha", 1.0f, 0.0f);
//                    alphaAnim.setDuration(800).start();
//                }
//                return false;
//            }
//        });
        holder.mRlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(context, MyViewPagerActivity.class);
                 intent.putExtra("picUrl",iv);
                ActivityTransitionLauncher.with((MainActivity)context).from(holder.mIvItem).launch(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private  ImageView mIvItem;
        private final TextView mTvName;
        private final RelativeLayout mRlAll;
        private final RelativeLayout mRlParent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIvItem = (ImageView) itemView.findViewById(R.id.im_item);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mRlAll = (RelativeLayout) itemView.findViewById(R.id.rl_all);
            mRlParent = (RelativeLayout) itemView.findViewById(R.id.rl_parent);
        }
    }
}
