package com.mercadolibre.android.andesui.bottomsheet

interface BottomSheetListener {
    /**
     * Called when the bottomSheet is collapsed
     */
    fun onCollapsed()

    /**
     * Called when the bottomSheet is expanded
     */
    fun onExpanded()
}
