package project.etoury.com.eyepetizerdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
public class ScreenUtils {

    public static int getWidth(Context context) {

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.widthPixels;
    }

    public static int getHeight(Context context) {

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.heightPixels;
    }
}
