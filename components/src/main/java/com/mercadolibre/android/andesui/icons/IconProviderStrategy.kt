package com.mercadolibre.android.andesui.icons

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import android.util.Log
import java.io.FileNotFoundException

/**
 * This interface defines the way to load an icon with a string name.
 */
interface IconProviderStrategy {
    fun loadIcon(name: String): Drawable?
}

/**
 * This class has the responsibility of providing the icon which is inside of the library.
 */
class OfflineIconProviderStrategy(private val context: Context) : IconProviderStrategy {

    override fun loadIcon(name: String): Drawable? {
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return try {
            ContextCompat.getDrawable(context, resId)
        } catch (e: FileNotFoundException) {
            Log.e("Andes UI", "File $name was not found.", e)
            null
        }
    }
}
