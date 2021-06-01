package com.example.myapplication.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager

class PermissionUtils {
    companion object {
        fun checkStoragePermissions(activity: Activity) {
            if (!isStoragePermissionGranted(activity)) {
                activity.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 3)
            }
        }

        fun isStoragePermissionGranted(activity: Activity): Boolean {
            return activity.applicationContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }
}