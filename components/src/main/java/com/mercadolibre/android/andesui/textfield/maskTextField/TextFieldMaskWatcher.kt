package com.mercadolibre.android.andesui.textfield.maskTextField

import android.text.Editable
import android.text.TextWatcher
import java.math.BigDecimal

class TextFieldMaskWatcher(private var mask: String = "", private var textChange: OnTextChange?) : TextWatcher {
    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        textChange?.onChange(charSequence.toString())
    }

    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true

        val editableLength = editable.length
        if (mask.isNotBlank() && editableLength < mask.length && editableLength > 0) {
            if (mask[editableLength] != GENERIC_CHAR) {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != GENERIC_CHAR) {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }

        isRunning = false
    }

    fun setMask(mask: String) {
        this.mask = mask
    }

    fun cleanMask(text: String): String {
        val maskChars = mask.replace(GENERIC_CHAR.toString(), "")
        var textWithoutMask = text
        maskChars.forEach { char ->
            textWithoutMask = textWithoutMask.replace(char.toString(), "")
        }
        return textWithoutMask
    }

    fun getMaxLength(): Int {
        return if (mask.isNotEmpty()) {
            mask.length - mask.replace(GENERIC_CHAR.toString(), "").length
        } else {
            BigDecimal.ZERO.toInt()
        }
    }

    interface OnTextChange {
        fun onChange(text: String)
    }

    companion object {
        const val GENERIC_CHAR = '#'
    }
}
