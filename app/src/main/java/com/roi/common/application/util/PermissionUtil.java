package com.roi.common.application.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * 퍼미션 관리
 */

public class PermissionUtil {

    public static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static String PERMISSIONS_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public static final int REQUEST_STORAGE = 1000;

    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        //>> 권한 체크 모든 경우에 있어서 확인이 안되므로 사용하지 않고 자체 다이얼로그를 띄우도록 한다.
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, PermissionUtil.PERMISSIONS_WRITE_EXTERNAL_STORAGE)) {
            // 사용자가 다시 보지 않기에 체크를 하지 않고, 권한 설정을 거절한 이력이 있는 경우
        } else {
            // 사용자가 다시 보지 않기에 체크를 하고, 권한 설정을 거절한 이력이 있는 경우
        }
        //<<
        // 사용자에게 접근 권한 설정을 요구하는 다이얼로그를 띄운다.
        // 만약 사용자가 다시 보지 않기에 체크를 했을 경우엔 권한 설정 다이얼로그가 뜨지 않고, 곧바로 OnRequestPermissionResult 가 실행된다.
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * Permission Check
     * @param context
     * @param permission
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasSelfPermission(Context context, String permission) {
        if(!isMarshmallow()) {
            return true;
        }
        return context.checkSelfPermission(permission) == PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasSelfPermission(Context context, String[] permissions) {
        if (!isMarshmallow()) {
            return true;
        }

        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Marshmallow
     * @return
     */
    public static boolean isMarshmallow() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return false;
        }
        return true;
    }

    public static void showRationalDialog(Context context, int message) {
        showRationalDialog(context, context.getString(message));
    }

    public static void showRationalDialog(final Context context, String message) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(context);
        alertDlg.setMessage(message);
        alertDlg.setPositiveButton("확 인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                    ((Activity)context).finish();
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        alertDlg.setNegativeButton("취 소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((Activity)context).finish();
            }
        });
        alertDlg.show();

    }
}
