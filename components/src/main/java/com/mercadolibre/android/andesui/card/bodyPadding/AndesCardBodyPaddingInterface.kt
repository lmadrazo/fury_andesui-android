package com.mercadolibre.android.andesui.card.bodyPadding

import android.content.Context
import com.mercadolibre.android.andesui.R

internal interface AndesCardBodyPaddingInterface {
    fun bodyPaddingSize(context: Context): Int
}

internal object AndesCardBodyPaddingNone : AndesCardBodyPaddingInterface {
    override fun bodyPaddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_none).toInt()
}

internal object AndesCardBodyPaddingSmall : AndesCardBodyPaddingInterface {
    override fun bodyPaddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_small).toInt()
}

internal object AndesCardBodyPaddingMedium : AndesCardBodyPaddingInterface {
    override fun bodyPaddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_medium).toInt()
}

internal object AndesCardBodyPaddingLarge : AndesCardBodyPaddingInterface {
    override fun bodyPaddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_large).toInt()
}

internal object AndesCardBodyPaddingXLarge : AndesCardBodyPaddingInterface {
    override fun bodyPaddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_xlarge).toInt()
}
