package com.mercadolibre.android.andesui.tag.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesTag] needs to be drawn properly.
 * Those properties change depending on the size of the tag.
 */
internal interface AndesTagSizeInterface {
    fun textSize(context: Context): Float
    fun height(context: Context): Float
    fun border(context: Context): Float
    fun leftMargin(context: Context): Int
    fun rightMargin(context: Context): Int
}

/**
 * Implementation of [AndesTagSizeInterface] that returns the required data but personalized for the Large Size,
 * according to Andes specifications.
 */
internal class AndesLargeTagSize : AndesTagSizeInterface {
    override fun textSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_large_text_size)

    override fun height(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_large_height)

    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_large_corner_radius)

    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_large_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_large_margin).toInt()
}

/**
 * Implementation of [AndesTagSizeInterface] that returns the required data but personalized for the small Size,
 * according to Andes specifications.
 */
internal class AndesSmallTagSize : AndesTagSizeInterface {
    override fun textSize(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_small_text_size)

    override fun height(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_small_height)

    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_small_corner_radius)

    override fun leftMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()

    override fun rightMargin(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
}
