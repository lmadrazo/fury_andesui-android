package com.mercadolibre.android.andesui.textfield.textwatcher

import android.text.Editable
import android.text.TextWatcher

internal class AndesTextfieldBoxWatcher(
    private val andesCodeTextChangedHandler: AndesCodeTextChangedHandler,
    private val focusManagement: AndesCodeFocusManagement,
    private val indexChild: Int
) : TextWatcher {

    private var isRunning = false
    private var isDeleting = false
    private var wasDirty = false

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
        wasDirty = charSequence.isEmpty() || charSequence.length == 1 && charSequence.contains(DIRTY_CHARACTER)
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        var textToChange: CharSequence? = charSequence

        if (wasDirty && isDeleting) {
            return
        }

        if (charSequence.isNotEmpty() && charSequence.contains(DIRTY_CHARACTER)) {
            textToChange = charSequence.filter { it != DIRTY_CHARACTER[0] }.takeIf { it.isNotEmpty() || !wasDirty }
        }

        textToChange?.let { andesCodeTextChangedHandler.onTextChanged(it, indexChild) }
    }

    override fun afterTextChanged(editable: Editable) {
        if (isRunning) {
            return
        }

        isRunning = true

        if (editable.contains(DIRTY_CHARACTER)) {
            if (editable.length > 1) {
                focusManagement.goToNextFocus()
            }
        } else if (editable.isNotEmpty()) {
            focusManagement.goToNextFocus()
        } else if (editable.isEmpty()) {
            focusManagement.goToPreviousFocus()
        }

        isRunning = false
    }

    companion object {
        const val DIRTY_CHARACTER = "\r"
    }
}
