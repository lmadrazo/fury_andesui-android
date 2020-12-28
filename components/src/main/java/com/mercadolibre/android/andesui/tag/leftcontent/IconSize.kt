package com.mercadolibre.android.andesui.tag.leftcontent

enum class IconSize {
    SMALL,
    LARGE;

    companion object {
        fun fromString(value: String): IconSize = valueOf(value.toUpperCase())
    }

    internal val size get() = getAndesIconSize()

    private fun getAndesIconSize(): AndesIconSizeInterface {
        return when (this) {
            SMALL -> IconSmall()
            LARGE -> IconLarge()
        }
    }
}
