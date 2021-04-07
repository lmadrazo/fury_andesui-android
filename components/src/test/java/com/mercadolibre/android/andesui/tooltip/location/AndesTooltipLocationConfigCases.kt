package com.mercadolibre.android.andesui.tooltip.location

import org.mockito.Mockito.spy

val topLocationConfig: Array<Any?> = arrayOf(
        spy(AndesTooltipLocation.TOP),
        TopAndesTooltipLocationConfig(AndesTooltipLocationInterfaceImplTest)
)
val bottomLocationConfig: Array<Any?> = arrayOf(
        spy(AndesTooltipLocation.BOTTOM),
        BottomAndesTooltipLocationConfig(AndesTooltipLocationInterfaceImplTest)
)
val leftLocationConfig: Array<Any?> = arrayOf(
        spy(AndesTooltipLocation.LEFT),
        LeftAndesTooltipLocationConfig(AndesTooltipLocationInterfaceImplTest)
)
val rightLocationConfig: Array<Any?> = arrayOf(
        spy(AndesTooltipLocation.RIGHT),
        RightAndesTooltipLocationConfig(AndesTooltipLocationInterfaceImplTest)
)
