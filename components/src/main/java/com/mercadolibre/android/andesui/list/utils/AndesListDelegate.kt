package com.mercadolibre.android.andesui.list.utils

import android.view.View
import com.mercadolibre.android.andesui.list.AndesList
import com.mercadolibre.android.andesui.list.AndesListViewItem

/**
 * AndesListDelegate: interface to define the delegate of AndesList
 */
interface AndesListDelegate {
    fun onItemClick(andesList: AndesList, position: Int)

    fun bind(andesList: AndesList, view: View, position: Int): AndesListViewItem

    fun getDataSetSize(andesList: AndesList): Int
}
