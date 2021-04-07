package com.mercadolibre.android.andesui.thumbnail.type

/**
 * Defines all types related properties that an [AndesThumbnail] needs to be drawn properly.
 * Those properties change depending on the style of the thumbnail.
 *
 */
internal interface AndesThumbnailTypeInterface

internal class AndesIconThumbnailType : AndesThumbnailTypeInterface

internal class AndesImageCircleThumbnailType : AndesThumbnailTypeInterface

internal class AndesImageSquareThumbnailType : AndesThumbnailTypeInterface
