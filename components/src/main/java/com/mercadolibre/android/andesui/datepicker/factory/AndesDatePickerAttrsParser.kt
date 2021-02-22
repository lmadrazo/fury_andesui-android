package com.mercadolibre.android.andesui.datepicker.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R

internal data class AndesDatePickerAttrs(
        val andesDatePickerText: String?,
        val andesDatePickerMinDate: String?,
        val andesDatePickerMaxDate: String?,
        val andesBtnVisibility : Boolean?
)

/**
 * This object parse the attribute set and return an instance of AndesDatePickerAttrs
 * to be used by AndesDatePicker
 */
internal object AndesDatePickerAttrParser {

    fun parse(context: Context, attr: AttributeSet?): AndesDatePickerAttrs {

        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesDatePicker)

        return AndesDatePickerAttrs(
                andesDatePickerText = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerText),
                andesDatePickerMinDate = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerMaxDate),
                andesDatePickerMaxDate = typedArray.getString(R.styleable.AndesDatePicker_andesDatePickerMaxDate),
                andesBtnVisibility = null
        ).also { typedArray.recycle() }
    }

}




