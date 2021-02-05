package com.dmitrysimakov.lifehackstudio

import android.view.View

const val BASE_URL = "https://lifehack.studio/test_task/"

fun View.visibleOrGone(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}