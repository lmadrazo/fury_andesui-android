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
import com.mercadolibre.android.andesui.thumbnail.type.AndesImageSquareThumbnailType
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailTypeInterface
import com.mercadolibre.android.andesui.thumbnail.utils.isIconType
import com.mercadolibre.android.andesui.thumbnail.utils.resolverByApiLevel

internal data class AndesThumbnailConfiguration(
    val backgroundColor: AndesColor,
    val borderColor: AndesColor,
    val hasBorder: Boolean,
    val iconColor: AndesColor,
    val iconSize: Int,
    val image: Drawable,
    val size: Float,
    val cornerRadius: Float,
    val isImageType: Boolean,
    val hasTint: Boolean
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
                iconSize = resolveIconSize(context, andesThumbnailSize.size, andesThumbnailType.type),
                image = resolveImage(andesThumbnailImage),
                size = resolveSize(context, andesThumbnailSize.size),
                cornerRadius = resolveCornerRadius(context, andesThumbnailSize.size, andesThumbnailType.type),
                isImageType = resolveIsImageType(andesThumbnailType.type),
                hasTint = resolveHasTint(andesThumbnailType.type)
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
    private fun resolveImage(image: Drawable) = image
    private fun resolveSize(context: Context, size: AndesThumbnailSizeInterface) = size.diameter(context)
    private fun resolveIconSize(
            context: Context,
            size: AndesThumbnailSizeInterface,
            type: AndesThumbnailTypeInterface) = resolverByApiLevel.resolveIconSize(context, size, type)
    private fun resolveIsImageType(type: AndesThumbnailTypeInterface) = resolverByApiLevel.resolveIsImageType(type)
    private fun resolveHasTint(type: AndesThumbnailTypeInterface) = type.isIconType
    private fun resolveCornerRadius(
            context: Context,
            size: AndesThumbnailSizeInterface,
            type: AndesThumbnailTypeInterface
    ) = if(type is AndesImageSquareThumbnailType) size.radiusSize(context) else size.diameter(context)

}
