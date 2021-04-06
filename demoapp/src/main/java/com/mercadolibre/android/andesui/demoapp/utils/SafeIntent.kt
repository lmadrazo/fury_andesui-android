package com.mercadolibre.android.andesui.demoapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class SafeIntent(context: Context, uri: String) : Intent(ACTION_VIEW, (Uri.parse(uri))) {
    init {
        setPackage(context.applicationContext.packageName)
    }
}
