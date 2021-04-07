package com.mercadolibre.android.andesui.card.bodyPadding

enum class AndesCardBodyPadding {
    NONE,
    SMALL,
    MEDIUM,
    LARGE,
    XLARGE;

    companion object {
        fun fromString(value: String): AndesCardBodyPadding = valueOf(value.toUpperCase())
    }

    internal val bodyPadding get() = getAndesCardBodyPadding()

    private fun getAndesCardBodyPadding(): AndesCardBodyPaddingInterface {
        return when (this) {
            NONE -> AndesCardBodyPaddingNone
            SMALL -> AndesCardBodyPaddingSmall
            MEDIUM -> AndesCardBodyPaddingMedium
            LARGE -> AndesCardBodyPaddingLarge
            XLARGE -> AndesCardBodyPaddingXLarge
        }
    }
}
