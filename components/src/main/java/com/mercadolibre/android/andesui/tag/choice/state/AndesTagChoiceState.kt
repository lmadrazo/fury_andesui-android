package com.mercadolibre.android.andesui.tag.choice.state

import com.mercadolibre.android.andesui.tag.choice.AndesChoiceIdleState
import com.mercadolibre.android.andesui.tag.choice.AndesChoiceSelectedState
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceStateInterface

enum class AndesTagChoiceState {
    IDLE,
    SELECTED;

    companion object {
        fun fromString(value: String): AndesTagChoiceState = valueOf(value.toUpperCase())
    }

    internal val state get() = getAndesTagChoiceState()

    private fun getAndesTagChoiceState(): AndesTagChoiceStateInterface {
        return when (this) {
            IDLE -> AndesChoiceIdleState()
            SELECTED -> AndesChoiceSelectedState()
        }
    }
}
