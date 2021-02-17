package com.mercadolibre.android.andesui.tooltip.style

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfig
import com.mercadolibre.android.andesui.button.hierarchy.createBackgroundColorConfigLoud
import com.mercadolibre.android.andesui.button.hierarchy.createBackgroundColorConfigQuiet
import com.mercadolibre.android.andesui.button.hierarchy.createBackgroundColorConfigTransparent
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.message.type.AndesMessageTypeInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import com.mercadolibre.android.andesui.utils.buildColoredAndesBitmapDrawable
import com.mercadolibre.android.andesui.utils.buildColoredCircularShapeWithIconDrawable

internal sealed class AndesTooltipStyleInterface {

    fun titleTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andes_font_semibold)

    fun bodyTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andes_font_regular)

    fun dismissibleIcon(context: Context) =
            buildColoredAndesBitmapDrawable(
                    IconProvider(context).loadIcon("andes_ui_close_20") as BitmapDrawable,
                    context,
                    color = dismissIconColor()
            )

    abstract fun dismissIconColor(): AndesColor?

    abstract fun backgroundColor(): AndesColor

    abstract fun textColor(): AndesColor
}

internal object AndesTooltipLightStyle : AndesTooltipStyleInterface() {
    override fun backgroundColor() = R.color.andes_bg_color_white.toAndesColor()

    override fun textColor() = R.color.andes_gray_800.toAndesColor()

    override fun dismissIconColor(): AndesColor? = null

}

