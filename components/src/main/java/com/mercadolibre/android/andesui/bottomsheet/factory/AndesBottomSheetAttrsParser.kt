package com.mercadolibre.android.andesui.bottomsheet.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.bottomsheet.state.AndesBottomSheetState
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment
import kotlin.IllegalArgumentException

private const val DEFAULT_PEEK_HEIGHT = 0f

internal data class AndesBottomSheetAttrs(
    val andesBottomSheetPeekHeight: Int,
    val andesBottomSheetState: AndesBottomSheetState,
    val andesBottomSheetTitleText: String?,
    val andesBottomSheetTitleAlignment: AndesBottomSheetTitleAlignment?
)

internal object AndesBottomSheetAttrsParser {

    private const val ANDES_BOTTOM_SHEET_STATE_COLLAPSED = "1000"
    private const val ANDES_BOTTOM_SHEET_STATE_EXPANDED = "1001"
    private const val ANDES_BOTTOM_SHEET_TITLE_CENTER = "2000"
    private const val ANDES_BOTTOM_SHEET_TITLE_LEFT = "2001"

    fun parse(context: Context, attr: AttributeSet?): AndesBottomSheetAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesBottomSheet)
        val title = typedArray.getString(R.styleable.AndesBottomSheet_andesBottomSheetTitleText)
        var titleAlignment: AndesBottomSheetTitleAlignment? = null

        val state = when (typedArray.getString(R.styleable.AndesBottomSheet_andesBottomSheetState)) {
            ANDES_BOTTOM_SHEET_STATE_COLLAPSED -> AndesBottomSheetState.COLLAPSED
            ANDES_BOTTOM_SHEET_STATE_EXPANDED -> AndesBottomSheetState.EXPANDED
            else -> throw IllegalArgumentException("Invalid state, valid states: [COLLAPSED, EXPANDED]")
        }

        if (title != null) {
            titleAlignment = when (typedArray.getString(R.styleable.AndesBottomSheet_andesBottomSheetTitleAlign)) {
                ANDES_BOTTOM_SHEET_TITLE_CENTER -> AndesBottomSheetTitleAlignment.CENTERED
                ANDES_BOTTOM_SHEET_TITLE_LEFT -> AndesBottomSheetTitleAlignment.LEFT_ALIGN
                else -> throw IllegalArgumentException("Invalid title alignment, valid alignments: [CENTERED, LEFT_ALIGN]")
            }
        }

        return AndesBottomSheetAttrs(
                andesBottomSheetPeekHeight =
                    typedArray.getDimension(R.styleable.AndesBottomSheet_andesBottomSheetPeekHeight,
                            DEFAULT_PEEK_HEIGHT).toInt(),
                andesBottomSheetState = state,
                andesBottomSheetTitleText = title,
                andesBottomSheetTitleAlignment = titleAlignment
        ).also { typedArray.recycle() }
    }
}
