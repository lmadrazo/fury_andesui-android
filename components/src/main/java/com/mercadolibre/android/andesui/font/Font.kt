package com.mercadolibre.android.andesui.font
import java.util.EnumMap

/**
 * The font types that we support
 * Use the FontConfig to initialize all fonts
 */
enum class Font {
    BLACK, BOLD, EXTRA_BOLD, LIGHT, REGULAR, SEMI_BOLD, MEDIUM, THIN;

    /**
     * Getter for the fontName
     *
     * @return the fontName
     */
    val fontName: String?
        get() = theFont

    /**
     * Getter for the path formed with the fontName
     *
     * @return the path of the font
     */
    val fontPath: String?
        get() = theFont

    private val theFont: String?
        get() {
            val fonts: EnumMap<Font, String>? = FontConfig.fonts
            return fonts?.get(this)
        }

    object FontConfig {
        /* default */ var fonts: EnumMap<Font, String>? = null
            private set

        /**
         * Sets the fonts to use
         * @param fontsMap with Font as Key and String with path to the font as value
         */
        fun setFonts(fontsMap: EnumMap<Font, String>) {
            fonts = fontsMap
        }
    }
}
