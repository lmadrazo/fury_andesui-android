package com.mercadolibre.android.andesui.demoapp.feature

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.mercadolibre.android.andesui.demoapp.R

class AndesTextfieldShowcasePagerAdapter(private val views: List<TextFieldPage>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        container.addView(views[position].page)
        return views[position].page
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View?)
    }

    override fun isViewFromObject(view: View, other: Any): Boolean {
        return view == other
    }

    override fun getCount(): Int = views.size

    fun getTitleResource(position: Int) = views[position].titleResource
}

data class TextFieldPage(
    val page: View,
    val titleResource: Int = R.string.andesui_demoapp_screen_textfield
)
