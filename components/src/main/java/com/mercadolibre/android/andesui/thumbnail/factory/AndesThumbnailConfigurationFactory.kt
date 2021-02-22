package com.mercadolibre.android.andesui.thumbnail.factory

import android.content.Context
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchyInterface
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSizeInterface
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailStateInterface

internal data class AndesThumbnailConfiguration(
    val backgroundColor: AndesColor,
    val borderColor: AndesColor,
    val hasBorder: Boolean,
    val iconColor: AndesColor,
    val iconSize: Int,
    val image: Drawable,
    val size: Float
)

internal object AndesThumbnailConfigurationFactory {

    fun create(context: Context, andesThumbnailAttrs: AndesThumbnailAttrs): AndesThumbnailConfiguration {
        return with(andesThumbnailAttrs) {
            AndesThumbnailConfiguration(
                backgroundColor = resolveBackgroundColor(context, andesThumbnailState.state, andesThumbnailHierarchy,
                    andesThumbnailAccentColor),
                borderColor = resolveBorderColor(),
                hasBorder = resolveHasBorder(andesThumbnailHierarchy.hierarchy),
                iconColor = resolveIconColor(context, andesThumbnailState.state, andesThumbnailHierarchy,
                    andesThumbnailAccentColor),
                iconSize = resolveIconSize(context, andesThumbnailSize.size),
                image = resolveImage(andesThumbnailImage),
                size = resolveSize(context, andesThumbnailSize.size)
            )
        }
    }

    private fun resolveBackgroundColor(
        context: Context,
        state: AndesThumbnailStateInterface,
        hierarchy: AndesThumbnailHierarchy,
        accentColor: AndesColor
    ) = state.backgroundColor(context, hierarchy, accentColor)
    private fun resolveBorderColor(): AndesColor = R.color.andes_gray_070_solid.toAndesColor()
    private fun resolveHasBorder(hierarchy: AndesThumbnailHierarchyInterface): Boolean = hierarchy.hasBorder()
    private fun resolveIconColor(
        context: Context,
        state: AndesThumbnailStateInterface,
        hierarchy: AndesThumbnailHierarchy,
        accentColor: AndesColor
    ) = state.iconColor(context, hierarchy, accentColor)
    private fun resolveIconSize(context: Context, size: AndesThumbnailSizeInterface) = size.iconSize(context).toInt()
    private fun resolveImage(image: Drawable) = image
    private fun resolveSize(context: Context, size: AndesThumbnailSizeInterface) = size.diameter(context)
}
