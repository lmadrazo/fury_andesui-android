package com.mercadolibre.android.andesui.tag.leftcontent

import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.color.AndesColor

@Suppress("LongParameterList")
class LeftContentIcon(
    val backgroundColor: String?,
    val path: String? = null,
    val icon: Drawable? = null,
    var iconColor: String? = null,
    var iconDefaultColor: AndesColor? = null,
    var iconSize: IconSize = IconSize.LARGE
)
