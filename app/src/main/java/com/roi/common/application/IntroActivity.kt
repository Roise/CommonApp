package com.roi.common.application

import android.os.Handler
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roi.common.application.util.NavigationUtil
import com.roi.common.application.util.PermissionUtil

class IntroActivity : AppCompatActivity() {
    private var mTimer: Handler? = null
    private var mIsTimerOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        mTimer = Handler()

        if (PermissionUtil.hasSelfPermission(this, PermissionUtil.PERMISSIONS_STORAGE)) {
            // Success
            roadingTimer()
        } else {
            PermissionUtil.requestPermissions(this, PermissionUtil.PERMISSIONS_STORAGE, PermissionUtil.REQUEST_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionUtil.REQUEST_STORAGE) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                NavigationUtil.gotoMain(applicationContext)
                finish()
            } else {
                PermissionUtil.showRationalDialog(this, getString(R.string.permission_request))
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        mIsTimerOn = false
        mTimer = null
    }

    private fun roadingTimer() {
        if (mTimer != null) {
            mIsTimerOn = true
            mTimer!!.postDelayed({
                try {
                    if (mIsTimerOn) {
                        // 메인 이동
                        NavigationUtil.gotoMain(applicationContext)
                    }
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 500)
        }
    }
}
