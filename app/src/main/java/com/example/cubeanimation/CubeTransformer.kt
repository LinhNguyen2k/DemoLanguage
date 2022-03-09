package com.example.cubeanimation

import android.view.View
import androidx.viewpager.widget.ViewPager

class CubeTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val deltaY = 1.5F
        page.pivotX = if (position > 0F) page.width.toFloat() else 0F
        page.pivotY = page.height * deltaY
        page.rotationY = -45F * position
    }
}