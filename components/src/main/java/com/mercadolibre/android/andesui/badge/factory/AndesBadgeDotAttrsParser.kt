package com.mercadolibre.android.andesui.badge.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.factory.Constants.ANDES_BADGE_TYPE_ERROR
import com.mercadolibre.android.andesui.badge.factory.Constants.ANDES_BADGE_TYPE_HIGHLIGHT
import com.mercadolibre.android.andesui.badge.factory.Constants.ANDES_BADGE_TYPE_NEUTRAL
import com.mercadolibre.android.andesui.badge.factory.Constants.ANDES_BADGE_TYPE_SUCCESS
import com.mercadolibre.android.andesui.badge.factory.Constants.ANDES_BADGE_TYPE_WARNING
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType

/**
 * The data class that contains the public components of the badge.
 */
internal data class AndesBadgeDotAttrs(
    val andesBadgeType: AndesBadgeType
)

/**
 * This object parse the attribute set and return an instance of AndesBadgeAttrs to be used by AndesBadge
 */
internal object AndesBadgeDotAttrsParser {

    fun parse(context: Context, attr: AttributeSet?): AndesBadgeDotAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesBadgeDot)

        val type = when (typedArray.getString(R.styleable.AndesBadgeDot_andesBadgeDotType)) {
            ANDES_BADGE_TYPE_NEUTRAL -> AndesBadgeType.NEUTRAL
            ANDES_BADGE_TYPE_HIGHLIGHT -> AndesBadgeType.HIGHLIGHT
            ANDES_BADGE_TYPE_SUCCESS -> AndesBadgeType.SUCCESS
            ANDES_BADGE_TYPE_WARNING -> AndesBadgeType.WARNING
            ANDES_BADGE_TYPE_ERROR -> AndesBadgeType.ERROR
            else -> AndesBadgeType.NEUTRAL
        }

        return AndesBadgeDotAttrs(andesBadgeType = type).also {
            typedArray.recycle()
        }
    }
}
