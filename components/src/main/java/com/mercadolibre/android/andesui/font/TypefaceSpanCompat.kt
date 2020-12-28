package com.mercadolibre.android.andesui.font

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

/**
 * This class replace the [CalligraphyTypefaceSpan]
 * (https://github.com/chrisjenx/Calligraphy/blob/master/calligraphy/src/main/
 * java/uk/co/chrisjenx/calligraphy/CalligraphyTypefaceSpan.java)
 * from [Calligraphy](https://github.com/chrisjenx/Calligraphy)
 * The motivation is to no depend on a library implementation
 *
 * @deprecated: instead you can use ResourceCompat
 * (https://developer.android.com/reference/android/support/v4/content/res/ResourcesCompat#getfont)
 */
@Deprecated(message = "Font support will be removed, instead you can use ResourceCompat.getFont().")
class TypefaceSpanCompat

    /**
     * Default constructor
     * @param typeFace typeface
     */
    /* default */ internal constructor(private var typeFace: Typeface) : TypefaceSpan("") {

    override fun updateDrawState(drawState: TextPaint) {
        apply(drawState)
    }

    override fun updateMeasureState(paint: TextPaint) {
        apply(paint)
    }

    private fun apply(paint: Paint) {
        val oldTypeface = paint.typeface
        val oldStyle = getOldStyle(oldTypeface)
        val fakeStyle = oldStyle and typeFace.style.inv()
        if (fakeStyle and Typeface.BOLD != 0) {
            paint.isFakeBoldText = true
        }
        if (fakeStyle and Typeface.ITALIC != 0) {
            paint.textSkewX = SKEW_X
        }
        paint.typeface = typeFace
    }

    private fun getOldStyle(oldTypeface: Typeface?): Int {
        return oldTypeface?.style ?: 0
    }

    companion object {
        private const val SKEW_X = -0.25f
    }
}
