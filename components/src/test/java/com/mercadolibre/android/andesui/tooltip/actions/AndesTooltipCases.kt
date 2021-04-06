package com.mercadolibre.android.andesui.tooltip.actions

import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.tooltip.location.AndesTooltipLocation
import com.mercadolibre.android.andesui.tooltip.style.AndesTooltipStyle

val tooltipNoAction: Array<Any?> = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.LEFT,
        null,
        null,
        null
)
val tooltipLoud = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        AndesTooltipAction("main action", AndesButtonHierarchy.LOUD, { _, _ -> }),
        null,
        null
)

val tooltipLoudAndQuiet = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        AndesTooltipAction("main action", AndesButtonHierarchy.LOUD, { _, _ -> }),
        AndesTooltipAction("secondary action", AndesButtonHierarchy.QUIET, { _, _ -> }),
        null
)

val tooltipLoudAndTransparent = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        AndesTooltipAction("main action", AndesButtonHierarchy.LOUD, { _, _ -> }),
        AndesTooltipAction("secondary action", AndesButtonHierarchy.TRANSPARENT, { _, _ -> }),
        null
)

val tooltipLink = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        null,
        null,
        AndesTooltipLinkAction("link action", { _, _ -> })
)
