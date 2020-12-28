package com.mercadolibre.android.andesui.badge

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.border.AndesBadgePillBorder
import com.mercadolibre.android.andesui.badge.factory.AndesBadgePillAttrs
import com.mercadolibre.android.andesui.badge.factory.AndesBadgePillConfigurationFactory
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgePillHierarchy
import com.mercadolibre.android.andesui.badge.size.AndesBadgePillSize
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType
import com.mercadolibre.android.andesui.color.toAndesColor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesBadgePillConfigurationLoudTest {

    private var context = RuntimeEnvironment.application

    private val configFactory = spy(AndesBadgePillConfigurationFactory)
    private lateinit var attrs: AndesBadgePillAttrs

    @Test
    fun `Pill, Loud, Neutral, Standard, Small background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.NEUTRAL,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_gray_450_solid.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Highlight, Standard, Small background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.HIGHLIGHT,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_accent_color_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Success, Standard, Small background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.SUCCESS,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_green_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Error, Standard, Small background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.ERROR,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_red_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Warning, Standard, Small background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.WARNING,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.SMALL, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_orange_500.toAndesColor(), config.backgroundColor)
        assertEquals(SMALL_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Neutral, Standard, Large background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.NEUTRAL,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_gray_450_solid.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Highlight, Standard, Large background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.HIGHLIGHT,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_accent_color_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Success, Standard, Large background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.SUCCESS,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_green_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Error, Standard, Large background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.ERROR,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_red_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    @Test
    fun `Pill, Loud, Warning, Standard, Large background color`() {
        attrs = AndesBadgePillAttrs(AndesBadgePillHierarchy.LOUD, AndesBadgeType.WARNING,
                AndesBadgePillBorder.STANDARD, AndesBadgePillSize.LARGE, "Title")
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_orange_500.toAndesColor(), config.backgroundColor)
        assertEquals(LARGE_HEIGHT, config.height)
    }

    companion object {
        private const val SMALL_HEIGHT = 16F
        private const val LARGE_HEIGHT = 24F
    }
}
