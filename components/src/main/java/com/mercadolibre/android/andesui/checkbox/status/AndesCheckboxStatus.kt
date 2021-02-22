package com.mercadolibre.android.andesui.checkbox.status

enum class AndesCheckboxStatus {
    SELECTED,
    UNSELECTED,
    UNDEFINED;

    companion object {
        fun fromString(value: String): AndesCheckboxStatus = valueOf(value.toUpperCase())
    }

    internal val status get() = getAndesCheckboxStatus()

    private fun getAndesCheckboxStatus(): AndesCheckboxStatusInterface {
        return when (this) {
            SELECTED -> AndesCheckboxStatusSelected
            UNSELECTED -> AndesCheckboxStatusUnselected
            UNDEFINED -> AndesCheckboxStatusUndefined
        }
    }
}
