package com.mercadolibre.android.andesui.bottomsheet.title

import android.view.Gravity

internal interface AndesBottomSheetTitleAlignmentInterface {
    fun getAlignment(): Int
}

internal object AndesBottomSheetTitleCenterAligned : AndesBottomSheetTitleAlignmentInterface {
    override fun getAlignment(): Int = Gravity.CENTER
}

internal object AndesBottomSheetTitleLeftAligned : AndesBottomSheetTitleAlignmentInterface {
    override fun getAlignment(): Int = Gravity.START
}
