package com.xt.doc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class AndroidUtils {
    public static int getMeasuredWidth(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredWidth();
    }

    public static BitmapFactory.Options getImageWidthHeight(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //返回的bitmap为空，options.outHeight已计算
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options); // 此时返回的bitmap为null
        //options.outHeight为原始图片宽高
        return options;
    }
}
