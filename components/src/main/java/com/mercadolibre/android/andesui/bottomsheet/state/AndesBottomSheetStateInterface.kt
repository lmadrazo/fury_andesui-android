package com.mercadolibre.android.andesui.bottomsheet.state

import com.google.android.material.bottomsheet.BottomSheetBehavior

internal interface AndesBottomSheetStateInterface {
    fun getState(): Int
}

internal object AndesBottomSheetStateExpanded : AndesBottomSheetStateInterface {
    override fun getState(): Int = BottomSheetBehavior.STATE_EXPANDED
}

internal object AndesBottomSheetStateCollapsed: AndesBottomSheetStateInterface{
    override  fun getState(): Int = BottomSheetBehavior.STATE_COLLAPSED
}
