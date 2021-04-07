package com.mercadolibre.android.andesui.demoapp

import android.app.Application
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.multidex.MultiDex
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener

/**
 * Main Application class that extends from Application to execute the start method only once.
 */
class MainApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        // No need for productFlavors, as proguard will remove all multidex related code in non-debug builds.
        if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
            MultiDex.install(this)
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Settings.System.putInt(base.contentResolver, "show_touches", 1)
        }
    }

    override fun onCreate() {
        super.onCreate()

        val requestListeners = setOf<RequestListener>(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(this)
                // other setters
                .setRequestListeners(requestListeners)
                .build()
        Fresco.initialize(this, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
    }
}
