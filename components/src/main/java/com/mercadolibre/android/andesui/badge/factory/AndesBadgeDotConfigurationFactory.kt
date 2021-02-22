package com.mercadolibre.android.andesui.badge.factory

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.type.AndesBadgeTypeInterface
import com.mercadolibre.android.andesui.color.AndesColor

internal data class AndesBadgeDotConfiguration(
    val backgroundColor: AndesColor,
    val size: Float
)

internal object AndesBadgeDotConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesBadgeDotAttrs): AndesBadgeDotConfiguration {
        return with(andesMessageAttrs) {
            AndesBadgeDotConfiguration(
                backgroundColor = resolveBackgroundColor(andesBadgeType.type),
                size = context.resources.getDimension(R.dimen.andes_badge_dot_size)
            )
        }
    }

    private fun resolveBackgroundColor(type: AndesBadgeTypeInterface) = type.primaryColor()
}
