package com.roi.common.application.util;

import android.content.Context;
import android.content.Intent;

import com.roi.common.application.MainActivity;

/**
 * 화면 이동
 */
public class NavigationUtil {

    public static void gotoMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void gotoSettingViewer(Context context) {
//        Intent intent = new Intent(context, SettingMainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
    }

}