package com.mercadolibre.android.andesui.thumbnail

import android.os.Build
import androidx.core.content.ContextCompat
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.thumbnail.factory.AndesThumbnailAttrs
import com.mercadolibre.android.andesui.thumbnail.factory.AndesThumbnailConfigurationFactory
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSize
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailState
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesThumbnailConfigurationTest {

    private var context = RuntimeEnvironment.application

    private val configFactory = spy(AndesThumbnailConfigurationFactory)
    private lateinit var attrs: AndesThumbnailAttrs

    @Test
    fun `Thumbnail, Loud, Neutral, Icon, Enabled, background color`() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.andes_pagar_y_cobrar_efectivo_24)
        attrs = AndesThumbnailAttrs(R.color.andes_orange_800.toAndesColor(), AndesThumbnailHierarchy.LOUD,
                drawable!!, AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_48, AndesThumbnailState.ENABLED)
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_orange_800.toAndesColor(), config.backgroundColor)
        assertEquals(AndesThumbnailSize.SIZE_48.size.diameter(context), config.size)
        assertEquals(drawable, config.image)
    }

    @Test
    fun `Thumbnail, Quiet, Neutral, Icon, Enabled, background color`() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.andes_pagar_y_cobrar_efectivo_24)
        val expectedColor = R.color.andes_orange_500.toAndesColor()
        expectedColor.alpha = 0.1f
        attrs = AndesThumbnailAttrs(R.color.andes_orange_500.toAndesColor(), AndesThumbnailHierarchy.QUIET,
            drawable!!, AndesThumbnailType.ICON,
            AndesThumbnailSize.SIZE_48, AndesThumbnailState.ENABLED)
        val config = configFactory.create(context, attrs)
        assertEquals(expectedColor, config.backgroundColor)
        assertEquals(AndesThumbnailSize.SIZE_48.size.diameter(context), config.size)
        assertEquals(drawable, config.image)
    }

    @Test
    fun `Thumbnail, Default, Neutral, Icon, Enabled, background color`() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.andes_pagar_y_cobrar_efectivo_24)
        attrs = AndesThumbnailAttrs(R.color.andes_orange_800.toAndesColor(), AndesThumbnailHierarchy.DEFAULT,
            drawable!!, AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_48, AndesThumbnailState.ENABLED)
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_white.toAndesColor(), config.backgroundColor)
        assertEquals(AndesThumbnailSize.SIZE_48.size.diameter(context), config.size)
        assertEquals(drawable!!, config.image)
    }

    @Test
    fun `Thumbnail, Loud, Neutral, Icon, Disabled, background color`() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.andes_pagar_y_cobrar_efectivo_24)
        attrs = AndesThumbnailAttrs(R.color.andes_orange_800.toAndesColor(), AndesThumbnailHierarchy.LOUD,
            drawable!!, AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_gray_100_solid.toAndesColor(), config.backgroundColor)
        assertEquals(AndesThumbnailSize.SIZE_48.size.diameter(context), config.size)
        assertEquals(drawable!!, config.image)
    }

    @Test
    fun `Thumbnail, Quiet, Neutral, Icon, Disabled, background color`() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.andes_pagar_y_cobrar_efectivo_24)
        attrs = AndesThumbnailAttrs(R.color.andes_orange_500.toAndesColor(), AndesThumbnailHierarchy.QUIET,
                drawable!!, AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_gray_100_solid.toAndesColor(), config.backgroundColor)
        assertEquals(AndesThumbnailSize.SIZE_48.size.diameter(context), config.size)
        assertEquals(drawable, config.image)
    }

    @Test
    fun `Thumbnail, Default, Neutral, Icon, Disabled, background color`() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.andes_pagar_y_cobrar_efectivo_24)
        attrs = AndesThumbnailAttrs(R.color.andes_orange_800.toAndesColor(), AndesThumbnailHierarchy.DEFAULT,
            drawable!!, AndesThumbnailType.ICON, AndesThumbnailSize.SIZE_48, AndesThumbnailState.DISABLED)
        val config = configFactory.create(context, attrs)
        assertEquals(R.color.andes_white.toAndesColor(), config.backgroundColor)
        assertEquals(AndesThumbnailSize.SIZE_48.size.diameter(context), config.size)
        assertEquals(drawable, config.image)
    }
}
