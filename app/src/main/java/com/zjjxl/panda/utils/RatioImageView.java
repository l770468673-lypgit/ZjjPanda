package com.zjjxl.panda.utils;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by zhouxinliang on 18-7-2.
 */

public class RatioImageView extends AppCompatImageView {
    static final private float RATIO = 9/16f;
    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.round(width*RATIO);
        setMeasuredDimension(width,height);
    }
}
