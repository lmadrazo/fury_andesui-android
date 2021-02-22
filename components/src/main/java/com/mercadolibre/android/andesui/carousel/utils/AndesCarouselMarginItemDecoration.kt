package com.mercadolibre.android.andesui.carousel.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * AndesCarouselPaddingItemDecoration: override the itemDecoration to recyclerview which uses in AndesCarousel
 */
class AndesCarouselMarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {

        val lastPosition = parent.adapter?.let {
            it.itemCount - 1
        } ?: FIRST_POSITION

        with(outRect) {
            when (parent.getChildAdapterPosition(view)) {
                FIRST_POSITION -> right = margin
                lastPosition -> left = margin
                else -> {
                    left = margin
                    right = margin
                }
            }
        }
    }

    companion object {
        private const val FIRST_POSITION = 0
    }
}
