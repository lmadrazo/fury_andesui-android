package com.mercadolibre.android.andesui.thumbnail.factory

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchyInterface
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSizeInterface
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailStateInterface
import com.mercadolibre.android.andesui.thumbnail.type.AndesIconThumbnailType
import com.mercadolibre.android.andesui.thumbnail.type.AndesImageSquareThumbnailType
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailTypeInterface
import com.mercadolibre.android.andesui.utils.isLollipopOrNewer

internal data class AndesThumbnailConfiguration(
    val backgroundColor: AndesColor,
    val borderColor: AndesColor,
    val hasBorder: Boolean,
    val iconColor: AndesColor,
    val iconSize: Int,
    val image: Drawable,
    val size: Float,
    val cornerRadius: Float,
    val isIconType: Boolean
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
                isIconType = resolveIsIconType(andesThumbnailType.type)
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
    private fun resolveIsIconType(type: AndesThumbnailTypeInterface) = resolverByApiLevel.resolveIsIconType(type)
    private fun resolveCornerRadius(
            context: Context,
            size: AndesThumbnailSizeInterface,
            type: AndesThumbnailTypeInterface
    ) = context.let(if(type is AndesImageSquareThumbnailType) size::radiusSize else size::diameter)

    internal interface AndesThumbnailConfigurationApiLevelResolver {
        fun resolveIconSize(context: Context,
                            size: AndesThumbnailSizeInterface,
                            type: AndesThumbnailTypeInterface): Int = size.iconSize(context).toInt()
        fun resolveIsIconType(type: AndesThumbnailTypeInterface): Boolean = true
    }

    private object ResolverApiLevelBelow21: AndesThumbnailConfigurationApiLevelResolver
    private object ResolverApiLevel21: AndesThumbnailConfigurationApiLevelResolver {
        override fun resolveIconSize(
                context: Context,
                size: AndesThumbnailSizeInterface,
                type: AndesThumbnailTypeInterface
        ) = if(type is AndesIconThumbnailType) super.resolveIconSize(context, size, type)
            else size.diameter(context).toInt()

        override fun resolveIsIconType(type: AndesThumbnailTypeInterface) = type is AndesIconThumbnailType
    }

    private val resolverByApiLevel: AndesThumbnailConfigurationApiLevelResolver by lazy {
        if(isLollipopOrNewer()) ResolverApiLevel21 else ResolverApiLevelBelow21
    }

}
