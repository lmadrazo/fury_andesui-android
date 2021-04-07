package com.mercadolibre.android.andesui.tag.choice

import com.mercadolibre.android.andesui.tag.AndesTagChoice

interface AndesTagChoiceCallback {
    fun shouldSelectTag(andesTagChoice: AndesTagChoice): Boolean
}
