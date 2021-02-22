package com.mercadolibre.android.andesui.thumbnail.hierarchy
/**
 * Defines all style related properties that an [AndesThumbnail] needs to be drawn properly.
 * Those properties change depending on the style of the thumbnail.
 *
 */
internal interface AndesThumbnailHierarchyInterface {
    fun hasBorder(): Boolean
}

internal class AndesDefaultThumbnailHierarchy : AndesThumbnailHierarchyInterface {
    override fun hasBorder() = true
}

internal class AndesLoudThumbnailHierarchy : AndesThumbnailHierarchyInterface {
    override fun hasBorder() = false
}

internal class AndesQuietThumbnailHierarchy : AndesThumbnailHierarchyInterface {
    override fun hasBorder() = false
}
