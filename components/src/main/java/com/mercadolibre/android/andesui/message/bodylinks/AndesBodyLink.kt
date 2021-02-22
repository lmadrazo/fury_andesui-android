package com.mercadolibre.android.andesui.message.bodylinks

import android.text.SpannableString

class AndesBodyLink(val startIndex: Int, val endIndex: Int) {
    fun isValidRange(text: SpannableString): Boolean {
        return (startIndex >= 0 &&
                endIndex >= 0 &&
                startIndex <= endIndex &&
                endIndex <= text.length)
    }
}
