package com.dmitrysimakov.lifehackstudio.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.maps.SupportMapFragment

class MapFragment : SupportMapFragment() {

    private var onTouchListener: (() -> Unit)? = null

    override fun onCreateView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, savedInstance: Bundle?): View {
        val layout = super.onCreateView(layoutInflater, viewGroup, savedInstance) as ViewGroup

        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(TouchableWrapper(requireContext()), layoutParams)
        return layout
    }

    fun setOnTouchListener(onTouch: () -> Unit) {
        onTouchListener = onTouch
    }

    inner class TouchableWrapper(context: Context) : FrameLayout(context) {
        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_DOWN) {
                onTouchListener?.invoke()
            }
            return super.dispatchTouchEvent(event)
        }
    }
}