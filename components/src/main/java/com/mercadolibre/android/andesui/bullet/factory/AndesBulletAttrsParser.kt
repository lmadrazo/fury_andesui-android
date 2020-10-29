package com.mercadolibre.android.andesui.bullet.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLinks
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType

internal data class AndesBulletAttrs(
    val andesMessageHierarchy: AndesMessageHierarchy,
    val andesMessageType: AndesMessageType,
    val andesBulletText: String?,
    val textLinks: AndesBodyLinks?
)

internal object AndesBulletAttrParser {

    private const val ANDES_MESSAGE_HIERARCHY_LOUD = "1000"
    private const val ANDES_MESSAGE_HIERARCHY_QUIET = "1001"

    private const val ANDES_MESSAGE_TYPE_NEUTRAL = "2000"
    private const val ANDES_MESSAGE_TYPE_SUCCESS = "2001"
    private const val ANDES_MESSAGE_TYPE_WARNING = "2002"
    private const val ANDES_MESSAGE_TYPE_ERROR = "2003"

    fun parse(context: Context, attr: AttributeSet?): AndesBulletAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesMessage)

        val hierarchy = when (typedArray.getString(R.styleable.AndesMessage_andesMessageHierarchy)) {
            ANDES_MESSAGE_HIERARCHY_LOUD -> AndesMessageHierarchy.LOUD
            ANDES_MESSAGE_HIERARCHY_QUIET -> AndesMessageHierarchy.QUIET
            else -> AndesMessageHierarchy.LOUD
        }

        val type = when (typedArray.getString(R.styleable.AndesMessage_andesMessageType)) {
            ANDES_MESSAGE_TYPE_NEUTRAL -> AndesMessageType.NEUTRAL
            ANDES_MESSAGE_TYPE_SUCCESS -> AndesMessageType.SUCCESS
            ANDES_MESSAGE_TYPE_WARNING -> AndesMessageType.WARNING
            ANDES_MESSAGE_TYPE_ERROR -> AndesMessageType.ERROR
            else -> AndesMessageType.NEUTRAL
        }

        return AndesBulletAttrs(
            andesMessageHierarchy = hierarchy,
            andesMessageType = type,
            andesBulletText = typedArray.getString(R.styleable.AndesMessage_andesMessageBodyText),
            textLinks = null
        ).also { typedArray.recycle() }
    }
}
