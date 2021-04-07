package com.mercadolibre.android.andesui.list.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.list.size.AndesListViewItemSize
import com.mercadolibre.android.andesui.list.type.AndesListType

internal data class AndesListAttrs(
    val andesListItemSize: AndesListViewItemSize,
    val andesListType: AndesListType,
    val andesListDividerEnabled: Boolean = false
)

/**
 * This object parse the attribute set and return an instance of AndesListAttrs
 * to be used by AndesList
 */
internal object AndesListAttrParser {

    private const val ANDES_LIST_SIZE_SMALL = "9000"
    private const val ANDES_LIST_SIZE_MEDIUM = "9001"
    private const val ANDES_LIST_SIZE_LARGE = "9002"

    private const val ANDES_LIST_TYPE_SIMPLE = "11000"
    private const val ANDES_LIST_TYPE_CHEVRON = "11001"
    private const val ANDES_LIST_TYPE_CHECK_BOX = "11002"
    private const val ANDES_LIST_TYPE_RADIO_BUTTON = "11003"

    fun parse(context: Context, attr: AttributeSet?): AndesListAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesList)

        val size = when (typedArray.getString(R.styleable.AndesList_andesListItemSize)) {
            ANDES_LIST_SIZE_SMALL -> AndesListViewItemSize.SMALL
            ANDES_LIST_SIZE_MEDIUM -> AndesListViewItemSize.MEDIUM
            ANDES_LIST_SIZE_LARGE -> AndesListViewItemSize.LARGE
            else -> AndesListViewItemSize.MEDIUM
        }

        val listType = when (typedArray.getString(R.styleable.AndesList_andesListType)) {
            ANDES_LIST_TYPE_SIMPLE -> AndesListType.SIMPLE
            ANDES_LIST_TYPE_CHEVRON -> AndesListType.CHEVRON
            ANDES_LIST_TYPE_CHECK_BOX -> AndesListType.CHECK_BOX
            ANDES_LIST_TYPE_RADIO_BUTTON -> AndesListType.RADIO_BUTTON
            else -> AndesListType.SIMPLE
        }

        return AndesListAttrs(
                andesListItemSize = size,
                andesListType = listType,
                andesListDividerEnabled = typedArray.getBoolean(R.styleable.AndesList_andesListDividerItemEnabled, false)
        ).also { typedArray.recycle() }
    }
}
