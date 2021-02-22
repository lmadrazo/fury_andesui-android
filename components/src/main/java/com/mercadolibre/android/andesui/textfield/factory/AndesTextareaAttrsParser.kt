package com.mercadolibre.android.andesui.textfield.factory

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

/**
 * The data class that contains the public components of the textfield.
 */
internal data class AndesTextareaAttrs(
    val label: String?,
    val helper: String?,
    val placeholder: String?,
    val counter: Int,
    val state: AndesTextfieldState,
    val maxLines: Int?,
    val textWatcher: TextWatcher? = null
)

/**
 * This object parse the attribute set and return an instance of AndesMessageAttrs to be used by AndesMessage
 */
internal object AndesTextareaAttrsParser {

    private const val ANDES_TEXTFIELD_STATE_ENABLED = "20"
    private const val ANDES_TEXTFIELD_STATE_ERROR = "21"
    private const val ANDES_TEXTFIELD_STATE_DISABLED = "22"
    private const val ANDES_TEXTFIELD_STATE_READONLY = "23"

    fun parse(context: Context, attr: AttributeSet?): AndesTextareaAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesTextarea)

        val state = when (typedArray.getString(R.styleable.AndesTextarea_andesTextareaState)) {
            ANDES_TEXTFIELD_STATE_ENABLED -> AndesTextfieldState.IDLE
            ANDES_TEXTFIELD_STATE_ERROR -> AndesTextfieldState.ERROR
            ANDES_TEXTFIELD_STATE_DISABLED -> AndesTextfieldState.DISABLED
            ANDES_TEXTFIELD_STATE_READONLY -> AndesTextfieldState.READONLY

            else -> AndesTextfieldState.IDLE
        }

        return AndesTextareaAttrs(
                label = typedArray.getString(R.styleable.AndesTextarea_andesTextareaLabel),
                helper = typedArray.getString(R.styleable.AndesTextarea_andesTextareaHelper),
                placeholder = typedArray.getString(R.styleable.AndesTextarea_andesTextareaPlaceholder),
                counter = typedArray.getInt(R.styleable.AndesTextarea_andesTextareaCounter, 0),
                state = state,
                maxLines = typedArray.getInt(R.styleable.AndesTextarea_andesTextareaMaxLines, Int.MAX_VALUE)
        ).also { typedArray.recycle() }
    }
}
