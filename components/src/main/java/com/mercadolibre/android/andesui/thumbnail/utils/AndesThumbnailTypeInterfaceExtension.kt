package com.mercadolibre.android.andesui.thumbnail.utils

import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.type.AndesIconThumbnailType
import com.mercadolibre.android.andesui.thumbnail.type.AndesImageCircleThumbnailType
import com.mercadolibre.android.andesui.thumbnail.type.AndesImageSquareThumbnailType
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailTypeInterface

internal val AndesThumbnailTypeInterface.isImageType: Boolean
    get() = this is AndesImageCircleThumbnailType || this is AndesImageSquareThumbnailType

internal val AndesThumbnailTypeInterface.isIconType: Boolean
    get() = this is AndesIconThumbnailType

internal fun AndesThumbnailTypeInterface.getHierarchy(
    hierarchy: AndesThumbnailHierarchy
): AndesThumbnailHierarchy = if (isImageType) AndesThumbnailHierarchy.DEFAULT else hierarchy
