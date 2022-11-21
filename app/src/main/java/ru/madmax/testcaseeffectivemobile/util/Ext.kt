package ru.madmax.testcaseeffectivemobile.util

import android.app.Activity
import androidx.annotation.ColorInt

fun Activity.setNavigationColor(@ColorInt color: Int) {
    window.navigationBarColor = color
}