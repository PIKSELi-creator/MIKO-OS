package com.miko.os.system

import android.graphics.drawable.Drawable

// Если вам не нужен AppInfo отдельно — можно удалить этот файл.
// Сейчас он дублирует AppModel; оставляю для совместимости.
data class AppInfo(
    val label: String,
    val packageName: String,
    val icon: Drawable
)
