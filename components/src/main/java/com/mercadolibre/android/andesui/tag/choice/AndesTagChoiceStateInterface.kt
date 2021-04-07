package com.mercadolibre.android.andesui.tag.choice

import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

/**
 * Defines all types related properties that an [AndesTag] needs to be drawn properly.
 * Those properties change depending on the style of the tag.
 */
interface AndesTagChoiceStateInterface {
    /**
     * @return an [AndesColor] that contains the background color data for the tag.
     */
    fun backgroundColor(): AndesColor

    /**
     * @return an [AndesColor] that contains the border color data for the tag.
     */
    fun borderColor(): AndesColor

    /**
     * @return an [AndesColor] that contains the text color data for the tag.
     */
    fun textColor(): AndesColor

    /**
     * @return an [AndesColor] that contains the right content color data for the tag.
     */
    fun rightContentColor(): AndesColor

    /**
     * @return an [AndesColor] that contains the left content color data for the tag.
     */
    fun leftContentColor(): AndesColor
}

internal class AndesChoiceIdleState : AndesTagChoiceStateInterface {
    override fun backgroundColor() = R.color.andes_transparent.toAndesColor()
    override fun borderColor() = R.color.andes_gray_250.toAndesColor()
    override fun textColor() = R.color.andes_gray_800_solid.toAndesColor()
    override fun rightContentColor() = R.color.andes_gray_450_solid.toAndesColor()
    override fun leftContentColor() = R.color.andes_gray_800.toAndesColor()
}

internal class AndesChoiceSelectedState : AndesTagChoiceStateInterface {
    override fun backgroundColor() = R.color.andes_blue_mp_100.toAndesColor()
    override fun borderColor() = R.color.andes_blue_mp_600.toAndesColor()
    override fun textColor() = R.color.andes_blue_mp_600.toAndesColor()
    override fun rightContentColor() = R.color.andes_blue_mp_600.toAndesColor()
    override fun leftContentColor() = R.color.andes_blue_mp_600.toAndesColor()
}
