package com.mercadolibre.android.andesui.tooltip.actions

import android.view.View
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.tooltip.AndesTooltip

open class AndesTooltipAction(
    open val label: String,
    open val hierarchy: AndesButtonHierarchy?,
    open val onActionClicked: (view: View, tooltip: AndesTooltip) -> Unit
)

class AndesTooltipLinkAction(
    override val label: String,
    override val onActionClicked: (view: View, tooltip: AndesTooltip) -> Unit
) : AndesTooltipAction(hierarchy = null, label = label, onActionClicked = onActionClicked)
