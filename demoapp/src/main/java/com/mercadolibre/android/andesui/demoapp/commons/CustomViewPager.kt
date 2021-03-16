package com.mercadolibre.android.andesui.demoapp.commons

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context?, attrs: AttributeSet?) : ViewPager(context!!, attrs) {

    init {
        this.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // Nothing to do
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Nothing to do
            }

            override fun onPageSelected(position: Int) {
                // Nothing to do
            }
        })
    }
}