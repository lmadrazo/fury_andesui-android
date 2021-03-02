package com.mercadolibre.android.andesui.tooltip

import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.tooltip.actions.AndesTooltipAction
import com.mercadolibre.android.andesui.tooltip.actions.AndesTooltipLinkAction
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

val tooltipQuiet = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        AndesTooltipAction("main action", AndesButtonHierarchy.QUIET, { _, _ -> }),
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

val tooltipQuietAndQuiet = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        AndesTooltipAction("main action", AndesButtonHierarchy.QUIET, { _, _ -> }),
        AndesTooltipAction("secondary action", AndesButtonHierarchy.QUIET, { _, _ -> }),
        null
)

val tooltipQuietAndTransparent = arrayOf(
        AndesTooltipStyle.LIGHT,
        "title",
        "body",
        true,
        AndesTooltipLocation.TOP,
        AndesTooltipAction("main action", AndesButtonHierarchy.QUIET, { _, _ -> }),
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