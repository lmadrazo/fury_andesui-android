package com.mercadolibre.android.andesui.tag.leftcontent

import com.mercadolibre.android.andesui.R

interface AndesIconSizeInterface {
    fun getWidth(): Int
    fun getHeight(): Int
}

internal class IconSmall : AndesIconSizeInterface {
    override fun getWidth(): Int {
        return R.dimen.andes_tag_icon_size_small
    }

    override fun getHeight(): Int {
        return R.dimen.andes_tag_icon_size_small
    }
}

internal class IconLarge : AndesIconSizeInterface {
    override fun getWidth(): Int {
        return R.dimen.andes_tag_icon_size_large
    }

    override fun getHeight(): Int {
        return R.dimen.andes_tag_icon_size_large
    }
}
