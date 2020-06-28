package com.zjjxl.panda.utils;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.DrawableRes;

public class BGTextUtils {
    public static int statusDrawable;
    public static int toolbarBackgroundColor;
    public static int toolbarBackgroundDrawable;
    public static int backDrawable;
    public static boolean isStatusBarLight;

    public static void setStatusbarDrawable(@DrawableRes int statusDraw) {
        statusDrawable = statusDraw;
    }

    public static boolean isStatusBar() {
        return statusDrawable > 0;
    }

    public static void setToolbarDrawable(int toolbarBackgroundDrawable) {
        BGTextUtils.toolbarBackgroundDrawable = toolbarBackgroundDrawable;
    }

    public static void setBackDrawable(int backDrawable) {
        BGTextUtils.backDrawable = backDrawable;
    }

    public static void setIsStatusBarLight(boolean isStatusBarLight) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            BGTextUtils.statusDrawable = Color.parseColor("#33ffffff");
        }
        BGTextUtils.isStatusBarLight = isStatusBarLight;

    }
    }
