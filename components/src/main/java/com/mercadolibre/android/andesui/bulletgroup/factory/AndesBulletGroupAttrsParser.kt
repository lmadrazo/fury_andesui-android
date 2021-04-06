package com.mercadolibre.android.andesui.bulletgroup.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.bulletgroup.BulletItem
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType

internal data class AndesBulletGroupAttrs(
    val andesMessageHierarchy: AndesMessageHierarchy,
    val andesMessageType: AndesMessageType,
    val andesBulletGroupBullets: ArrayList<BulletItem>
)

internal object AndesBulletGroupAttrParser {

    private const val ANDES_MESSAGE_HIERARCHY_LOUD = "1000"
    private const val ANDES_MESSAGE_HIERARCHY_QUIET = "1001"

    private const val ANDES_MESSAGE_TYPE_NEUTRAL = "2000"
    private const val ANDES_MESSAGE_TYPE_SUCCESS = "2001"
    private const val ANDES_MESSAGE_TYPE_WARNING = "2002"
    private const val ANDES_MESSAGE_TYPE_ERROR = "2003"

    fun parse(context: Context, attr: AttributeSet?): AndesBulletGroupAttrs {
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

        return AndesBulletGroupAttrs(
            andesMessageHierarchy = hierarchy,
            andesMessageType = type,
            andesBulletGroupBullets = arrayListOf()
        ).also { typedArray.recycle() }
    }
}