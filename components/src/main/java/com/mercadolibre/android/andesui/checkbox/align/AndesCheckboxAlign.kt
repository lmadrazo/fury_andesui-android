package com.mercadolibre.android.andesui.checkbox.align

enum class AndesCheckboxAlign {
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): AndesCheckboxAlign = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesCheckboxAlign()

    private fun getAndesCheckboxAlign(): AndesCheckboxAlignInterface {
        return when (this) {
            LEFT -> AndesCheckboxLeft
            RIGHT -> AndesCheckboxRight
        }
    }
}
