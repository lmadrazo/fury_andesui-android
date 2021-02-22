package com.mercadolibre.android.andesui.radiobutton.status

enum class AndesRadioButtonStatus {
    SELECTED,
    UNSELECTED;

    companion object {
        fun fromString(value: String): AndesRadioButtonStatus = valueOf(value.toUpperCase())
    }

    internal val status get() = getAndesRadioButtonStatus()

    private fun getAndesRadioButtonStatus(): AndesRadioButtonStatusInterface {
        return when (this) {
            SELECTED -> AndesRadioButtonStatusSelected
            UNSELECTED -> AndesRadioButtonStatusUnselected
        }
    }
}
