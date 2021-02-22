package com.mercadolibre.android.andesui.font

import android.graphics.Paint
import android.widget.TextView

/**
 * Sets the typeface to the given view
 * @param <T>   A generic for the textview
 * @param font The [Font] the text should have
 *
 * @deprecated: instead you can use ResourceCompat (https://developer.android.com/reference/android/support/v4/content/res/ResourcesCompat#getfont)
 */
@Deprecated(message = "Font support will be removed, instead you can use ResourceCompat.getFont().")
fun TextView.setTypeface(font: Font) {
    TypefaceHelper.setTypeface(this, font)
}

/**
 * Sets the typeface to the given [Paint]
 * @param paint The paint to which apply the font
 * @param font The [Font] the text should have

 * @deprecated: instead you can use ResourceCompat (https://developer.android.com/reference/android/support/v4/content/res/ResourcesCompat#getfont)
 */
@Deprecated(message = "Font support will be removed, instead you can use ResourceCompat.getFont().")
fun TextView.setTypeface(paint: Paint, font: Font) {
    TypefaceHelper.setTypeface(context, paint, font)
}
