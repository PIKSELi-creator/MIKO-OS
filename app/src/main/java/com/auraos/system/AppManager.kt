package com.MIKO-OS.system

import android.content.Context
import android.content.Intent

object AppManager {

    // Возвращаем List<AppModel> — согласовано с MainActivity и адаптером
    fun getInstalledApps(context: Context): List<AppModel> {
        val packageManager = context.packageManager

        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfoList = packageManager.queryIntentActivities(mainIntent, 0)
        val appsList = mutableListOf<AppModel>()

        for (resolveInfo in resolveInfoList) {
            val appLabel = resolveInfo.loadLabel(packageManager).toString()
            val packageName = resolveInfo.activityInfo.packageName
            val appIcon = resolveInfo.loadIcon(packageManager)

            appsList.add(AppModel(appLabel, packageName, appIcon))
        }

        return appsList.sortedBy { it.label.lowercase() }
    }
}
