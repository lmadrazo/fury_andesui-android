package com.mercadolibre.android.andesui.font

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.style.TypefaceSpan

/**
 * This class wrap the TypefaceSpan creation to split according to the Android API version
 *
 * @deprecated: instead you can use ResourceCompat (https://developer.android.com/reference/android/support/v4/content/res/ResourcesCompat#getfont)
 */
@Deprecated(message = "Font support will be removed, instead you can use ResourceCompat.getFont().")
object TypefaceSpanFactory {

    /**
     * Create TypefaceSpan with the given [Typeface]
     * @param typeface The [Typeface] the typefaceSpan should have
     */
    @JvmStatic
    fun create(typeface: Typeface): TypefaceSpan {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            TypefaceSpan(typeface)
        } else TypefaceSpanCompat(typeface)
    }

    /**
     * Create TypefaceSpan with the given [font]
     * @param context A context to obtain the font
     * @param font The [Font] the text should have
     */
    @JvmStatic
    fun create(context: Context, font: Font): TypefaceSpan? {
        val typeface = TypefaceHelper.getFontTypeface(context, font)
        return if (typeface != null) {
            create(typeface)
        } else null
    }
}
