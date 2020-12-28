package com.mercadolibre.android.andesui.carousel.factory

import android.content.Context
import com.mercadolibre.android.andesui.carousel.margin.AndesCarouselMarginInterface

internal data class AndesCarouselConfiguration(
    val margin: Int,
    val center: Boolean
)

internal object AndesCarouselConfigurationFactory {

    fun create(context: Context, andesCarouselAttrs: AndesCarouselAttrs): AndesCarouselConfiguration {
        val padding = andesCarouselAttrs.andesCarouselMargin.margin
        return AndesCarouselConfiguration(
            margin = resolveItemPadding(padding, context),
            center = andesCarouselAttrs.andesCarouselCenter
        )
    }

    private fun resolveItemPadding(padding: AndesCarouselMarginInterface, context: Context) = padding.getMargin(context)
}
