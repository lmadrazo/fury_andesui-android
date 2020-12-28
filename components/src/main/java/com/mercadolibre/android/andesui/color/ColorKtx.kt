package com.mercadolibre.android.andesui.color

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

@ColorInt
internal fun Int.toColor(context: Context): Int {
    return ContextCompat.getColor(context, this)
}

internal fun Int.toAndesColor(): AndesColor = AndesColor(this)

internal fun Int.toAndesColorWithAlpha(alpha: Float) = AndesColor(this, alpha)
