package com.mercadolibre.android.andesui.radiobutton.type

enum class AndesRadioButtonType {
    IDLE,
    DISABLED,
    ERROR;

    companion object {
        fun fromString(value: String): AndesRadioButtonType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadioButtonType()

    private fun getAndesRadioButtonType(): AndesRadioButtonTypeInterface {
        return when (this) {
            IDLE -> AndesRadioButtonTypeIdle
            DISABLED -> AndesRadioButtonTypeDisabled
            ERROR -> AndesRadioButtonTypeError
        }
    }
}
