package com.mercadolibre.android.andesui.demoapp.commons

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.Date
import kotlin.collections.HashMap

class CustomViewPager(context: Context?, attrs: AttributeSet?) : ViewPager(context!!, attrs) {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object {
        const val DEFAULT_POSITION = 0
    }

    private lateinit var screenName: String
    private val screensTime: HashMap<Int, Long> = hashMapOf()
    private var currentPosition = DEFAULT_POSITION
    private var startTime: Long = 0

    init {
        this.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                finishTime()
                currentPosition = position
                startTime()
            }
        })

        startTime()

        configureFirebase()
    }

    private fun configureFirebase() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    private fun startTime() {
        if (!screensTime.containsKey(currentPosition)) {
            screensTime[currentPosition] = 0
        }
        startTime = Date().time
    }

    private fun finishTime() {
        val diff = Date().time - startTime
        if (screensTime.containsKey(currentPosition)) {
            screensTime[currentPosition] = screensTime[currentPosition]!! + diff
        }
        startTime = 0
    }

    fun setScreenName(screenName: String) {
        this.screenName = screenName
    }

    fun stopTracking() {
        finishTime()
    }

    fun resumeTracking() {
        startTime()
    }

    fun finishTracking() {
        if (startTime > 0) {
            finishTime()
        }

        if (this::screenName.isInitialized) {
            val screen = screenName.replace(" ", "")
            val bundle = Bundle()
            for ((key, value) in screensTime) {
                val title = "Screen ${key + 1}"
                bundle.putLong(title, value)
            }
            firebaseAnalytics.logEvent(screen, bundle)
        }
    }
}