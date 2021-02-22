package com.mercadolibre.android.andesui.radiobutton.align

enum class AndesRadioButtonAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesRadioButtonAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadioButtonAlign()

    private fun getAndesRadioButtonAlign(): AndesRadioButtonAlignInterface {
        return when (this) {
            LEFT -> AndesRadioButtonLeft
            RIGHT -> AndesRadioButtonRight
        }
    }
}
