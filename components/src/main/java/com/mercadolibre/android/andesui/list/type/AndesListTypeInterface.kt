package com.mercadolibre.android.andesui.list.type

import android.content.Context

/**
 *
 */
internal interface AndesListTypeInterface {
    /**
     *
     */
    fun getType(context: Context): AndesListType
}

internal object AndesListTypeSimple : AndesListTypeInterface {
    override fun getType(context: Context) = AndesListType.SIMPLE
}

internal object AndesListTypeChevron : AndesListTypeInterface {
    override fun getType(context: Context) = AndesListType.CHEVRON
}

internal object AndesListTypeCheckBox : AndesListTypeInterface {
    override fun getType(context: Context) = AndesListType.CHECK_BOX
}

internal object AndesListTypeRadioButton : AndesListTypeInterface {
    override fun getType(context: Context) = AndesListType.RADIO_BUTTON
}
