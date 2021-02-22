package com.mercadolibre.android.andesui.radiobutton.status

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor

internal interface AndesRadioButtonStatusInterface {
    /**
     * Returns a [Drawable] that contains the radiobutton icon.
     *
     * @return a [Drawable] that contains the radiobutton icon.
     */
    fun icon(context: Context, color: AndesColor): GradientDrawable?
}

internal object AndesRadioButtonStatusSelected : AndesRadioButtonStatusInterface {
    override fun icon(context: Context, color: AndesColor): GradientDrawable? {
        val shape = GradientDrawable()
        shape.cornerRadius = context.resources.getDimension(R.dimen.andes_radiobutton_radius)
        shape.setColor(color.colorInt(context))
        return shape
    }
}

internal object AndesRadioButtonStatusUnselected : AndesRadioButtonStatusInterface {
    override fun icon(context: Context, color: AndesColor): GradientDrawable? {
        return null
    }
}
