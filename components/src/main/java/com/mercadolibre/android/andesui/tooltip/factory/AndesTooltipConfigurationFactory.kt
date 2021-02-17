package com.mercadolibre.android.andesui.tooltip.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.tooltip.style.AndesTooltipStyle
import com.mercadolibre.android.andesui.tooltip.location.AndesTooltipLocation

internal data class AndesTooltipConfiguration(
        val backgroundColor: AndesColor,
        val textColor: AndesColor,
        val titleText: String? = null,
        val titleTypeface: Typeface?,
        val titleTextSize: Float?,
        val bodyText: String,
        val bodyTypeface: Typeface?,
        val bodyTextSize: Float?,
        val isDismissible: Boolean,
        val dismissibleIcon: Drawable?,
        val tooltipLocation: AndesTooltipLocation
)

@Suppress("TooManyFunctions")
internal object AndesTooltipConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesTooltipAttrs): AndesTooltipConfiguration {
        return with(andesMessageAttrs) {
            AndesTooltipConfiguration(
                backgroundColor = resolveBackgroundColor(style),
                    textColor = resolveTextColor(style),
                    titleText = title,
                    titleTypeface = resolveTitleTypeface(style, context),
                    titleTextSize = resolveTitleSize(context),
                    bodyText = body,
                    bodyTypeface = resolveBodyTypeface(style, context),
                    bodyTextSize = resolveBodySize(context),
                    isDismissible = isDismissible,
                    dismissibleIcon = resolveDismissibleIcon(style, context),
                    tooltipLocation = tooltipLocation

            )
        }
    }

    private fun resolveBackgroundColor(style: AndesTooltipStyle) = style.type.backgroundColor()
    private fun resolveTextColor(style: AndesTooltipStyle) = style.type.textColor()
    private fun resolveTitleSize(context: Context) = context.resources.getDimension(R.dimen.andes_message_title)
    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andes_message_body)
    private fun resolveTitleTypeface(style: AndesTooltipStyle, context: Context) = style.type.titleTypeface(context)
    private fun resolveBodyTypeface(style: AndesTooltipStyle, context: Context) = style.type.bodyTypeface(context)
    private fun resolveDismissibleIcon(style: AndesTooltipStyle, context: Context) = style.type.dismissibleIcon(context)
}
