package com.mercadolibre.android.andesui.checkbox.status

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.utils.buildColoredAndesBitmapDrawable

internal interface AndesCheckboxStatusInterface {
    /**
     * Returns a [Drawable] that contains the checkbox icon.
     *
     * @return a [Drawable] that contains the checkbox icon.
     */
    fun icon(context: Context, color: AndesColor): BitmapDrawable?
}

internal object AndesCheckboxStatusSelected : AndesCheckboxStatusInterface {
    override fun icon(context: Context, color: AndesColor): BitmapDrawable? {
        return buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_selected") as BitmapDrawable,
                context = context,
                color = color
        )
    }
}

internal object AndesCheckboxStatusUnselected : AndesCheckboxStatusInterface {
    override fun icon(context: Context, color: AndesColor): BitmapDrawable? {
        return null
    }
}

internal object AndesCheckboxStatusUndefined : AndesCheckboxStatusInterface {
    override fun icon(context: Context, color: AndesColor): BitmapDrawable? {
        return buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_indeterminate") as BitmapDrawable,
                context = context,
                color = color
        )
    }
}
