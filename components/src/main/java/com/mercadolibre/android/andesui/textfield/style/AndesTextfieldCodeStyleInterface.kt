package com.mercadolibre.android.andesui.textfield.style

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all style that an [AndesTextfieldCode] needs to be drawn properly.
 * Each implementation define pattern of the boxes and gap between groups.
 * We takes as constants box width and gap between them.
 */
private const val THREE_SOME = 3
private const val FOUR_SOME = 4
private const val DEFAULT_GROUP_MARGIN = 0

internal sealed class AndesTextfieldCodeStyleInterface {
    abstract fun pattern(): IntArray
    abstract fun groupMargin(context: Context): Int
    open fun boxMargin(context: Context): Int = context
        .resources
        .getDimension(R.dimen.andes_textfield_box_margin).toInt()
    open fun boxWidth(context: Context): Int = context
        .resources
        .getDimension(R.dimen.andes_textfield_box_width).toInt()
}

internal object AndesTextfieldCodeThreesomeStyleInterface : AndesTextfieldCodeStyleInterface() {
    override fun pattern(): IntArray = intArrayOf(THREE_SOME)
    override fun groupMargin(context: Context): Int = DEFAULT_GROUP_MARGIN
}

internal object AndesTextfieldCodeFoursomeStyleInterface : AndesTextfieldCodeStyleInterface() {
    override fun pattern(): IntArray = intArrayOf(FOUR_SOME)
    override fun groupMargin(context: Context): Int = DEFAULT_GROUP_MARGIN
}

internal object AndesTextfieldCodeThreeByThreeStyleInterface : AndesTextfieldCodeStyleInterface() {
    override fun pattern(): IntArray = intArrayOf(THREE_SOME, THREE_SOME)
    override fun groupMargin(context: Context): Int = context
        .resources
        .getDimension(R.dimen.andes_textfield_box_group_margin).toInt()
}
