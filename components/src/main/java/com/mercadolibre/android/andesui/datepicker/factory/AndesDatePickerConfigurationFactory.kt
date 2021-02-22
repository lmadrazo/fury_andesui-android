package com.mercadolibre.android.andesui.datepicker.factory

internal data class AndesDatePickerConfiguration(
        val text: String?,
        val minDate: String?,
        val maxDate: String?,
        val applyButtonVisibility : Boolean?
)

internal object AndesDatePickerConfigurationFactory {

    fun create(andesDatePickerAttrs: AndesDatePickerAttrs): AndesDatePickerConfiguration {
        return with(andesDatePickerAttrs) {
            AndesDatePickerConfiguration(
                    text = andesDatePickerAttrs.andesDatePickerText,
                    minDate = andesDatePickerAttrs.andesDatePickerMinDate,
                    maxDate = andesDatePickerAttrs.andesDatePickerMaxDate,
                    applyButtonVisibility = andesDatePickerAttrs.andesBtnVisibility
            )
        }
    }

}
