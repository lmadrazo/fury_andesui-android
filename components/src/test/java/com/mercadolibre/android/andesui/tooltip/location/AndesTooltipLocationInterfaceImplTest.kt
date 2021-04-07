package com.mercadolibre.android.andesui.tooltip.location

import android.view.View
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.tooltip.radius.RadiusLayout
import com.nhaarman.mockitokotlin2.mock

object AndesTooltipLocationInterfaceImplTest : AndesTooltipLocationInterface {
    override val bodyWindowHeight: Int
        get() = 10
    override val bodyWindowWidth: Int
        get() = 20
    override val displaySizeX: Int
        get() = 30
    override val displaySizeY: Int
        get() = 40
    override val tooltipMeasuredWidth: Int
        get() = 50
    override val tooltipMeasuredHeight: Int
        get() = 60
    override val arrowWidth: Int
        get() = 70
    override val arrowHeight: Int
        get() = 80
    override val arrowBorder: Int
        get() = 90
    override val arrowImageInnerPadding: Int
        get() = 11
    override val paddingWithArrow: Int
        get() = 21
    override val elevation: Int
        get() = 31
    override val frameLayoutContainer: FrameLayout
        get() = mock()
    override val radiusLayout: RadiusLayout
        get() = mock()

    override fun showDropDown(target: View, xOff: Int, yOff: Int, locationConfig: AndesTooltipLocationConfig) {
        target.width + xOff + yOff
    }
}
