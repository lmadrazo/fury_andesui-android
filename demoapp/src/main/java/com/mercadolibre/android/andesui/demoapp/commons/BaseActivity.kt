package com.mercadolibre.android.andesui.demoapp.commons

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var viewPager: CustomViewPager

    override fun onDestroy() {
        if (this::viewPager.isInitialized) {
            viewPager.tracking()
        }
        super.onDestroy()
    }

    override fun onStop() {
        if (this::viewPager.isInitialized) {
            viewPager.stopTracking()
        }
        super.onStop()
    }

    override fun onResume() {
        if (this::viewPager.isInitialized) {
            viewPager.resumeTracking()
        }
        super.onResume()
    }

}