package com.mercadolibre.android.andesui.carousel.utils

import android.view.View
import com.mercadolibre.android.andesui.carousel.AndesCarousel

/**
 * AndesCarouselDelegate: interface to define the delegate of AndesCarousel
 */
interface AndesCarouselDelegate {

    /**
     * Bind view that will execute to render the item
     */
    fun bind(andesCarouselView: AndesCarousel, view: View, position: Int)

    /**
     * Listener that will execute when user clicks the item
     */
    fun onClickItem(andesCarouselView: AndesCarousel, position: Int)

    /**
     * Amount of items in carousel
     */
    fun getDataSetSize(andesCarouselView: AndesCarousel): Int

    /**
     * Layout of item in carousel
     */
    fun getLayoutItem(andesCarouselView: AndesCarousel): Int
}
