package com.mercadolibre.android.andesui.font

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import android.widget.TextView

/**
 * This class is used as a wrapper for our custom font.
 * If you code create a View that supports typeface you should call one of this methods.
 *
 * @deprecated: instead you can use ResourceCompat (https://developer.android.com/reference/android/support/v4/content/res/ResourcesCompat#getfont)
 */
@Deprecated(message = "Font support will be removed, instead you can use ResourceCompat.getFont().")
object TypefaceHelper {

    private lateinit var typefaceSetter: TypefaceSetter

    /**
     * Attach a typeface setter to this helper class
     * @param typefaceSetter field
     */
    @JvmStatic
    fun attachTypefaceSetter(typefaceSetter: TypefaceSetter) {
        TypefaceHelper.typefaceSetter = typefaceSetter
    }

    /**
     * Sets the typeface to the given [T]
     * @param <T>   A generic for the textview
     * @param view The view to which apply the font
     * @param font The [Font] the text should have
     */
    @JvmStatic
    fun <T : TextView?> setTypeface(view: T, font: Font) {
        if (this::typefaceSetter.isInitialized) {
            typefaceSetter.setTypeface(view, font)
        } else {
            Log.i("Andes font", "TypefaceSetter was not initialized. font-configurator is configured?")
        }
    }

    /**
     * Sets the typeface to the given [Paint]
     * @param context A context to obtain the font
     * @param paint The paint to which apply the font
     * @param font The [Font] the text should have
     */
    @JvmStatic
    fun setTypeface(context: Context, paint: Paint, font: Font) {
        if (this::typefaceSetter.isInitialized) {
            typefaceSetter.setTypeface(context, paint, font)
        } else {
            Log.i("Andes font", "TypefaceSetter was not initialized. font-configurator is configured?")
        }
    }

    /**
     * Get a typeface associated to the font passed.
     * @param context to use
     * @param font to use
     * @return associated typeface
     */
    @JvmStatic
    fun getFontTypeface(context: Context, font: Font): Typeface? {
        return if (this::typefaceSetter.isInitialized) {
            typefaceSetter.getTypeface(context, font)
        } else {
            Log.i("Andes font", "TypefaceSetter was not initialized. font-configurator is configured?")
            null
        }
    }

    /**
     * Setter for typeface
     */
    interface TypefaceSetter {
        /**
         * Set a typeface to a view
         * @param view to set the typeface to
         * @param font to set
         * @param <T> extends TextView
         */
        fun <T : TextView?> setTypeface(view: T, font: Font)

        /**
         * Set a typeface to the paint
         *
         * @param context to use
         * @param paint to set the typeface
         * @param font to set
         */
        fun setTypeface(context: Context, paint: Paint, font: Font)

        /**
         * Return the typeface associated with the font
         * @param context to use
         * @param font to find the typeface
         * @return typeface associated
         */
        fun getTypeface(context: Context, font: Font): Typeface?
    }
}
