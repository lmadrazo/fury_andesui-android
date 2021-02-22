package com.mercadolibre.android.andesui.datepicker

import android.os.Build
import com.facebook.drawee.BuildConfig
import com.mercadolibre.android.andesui.datepicker.factory.AndesDatePickerAttrs
import com.mercadolibre.android.andesui.datepicker.factory.AndesDatePickerConfigurationFactory
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesDatePickerTest{
    private val configFactory = Mockito.spy(AndesDatePickerConfigurationFactory)
    private lateinit var attrs: AndesDatePickerAttrs

    @Test
    fun `attr btn enabled`(){
        attrs = AndesDatePickerAttrs(
                andesDatePickerText = "aplicar",
                andesDatePickerMinDate = "01/01/2020",
                andesDatePickerMaxDate = "31/12/2020",
                andesBtnVisibility = true
        )
        val config = configFactory.create(attrs)
        Assert.assertEquals(config.text, "aplicar")
        Assert.assertEquals(config.minDate, "01/01/2020")
        Assert.assertEquals(config.maxDate, "31/12/2020")
        config.applyButtonVisibility?.let { Assert.assertTrue(it) }
    }

    @Test
    fun `attr btn disabled`(){
        attrs = AndesDatePickerAttrs(
                andesDatePickerText = "aplicar",
                andesDatePickerMinDate = "01/01/2020",
                andesDatePickerMaxDate = "31/12/2020",
                andesBtnVisibility = false
        )
        val config = configFactory.create(attrs)
        Assert.assertEquals(config.text, "aplicar")
        Assert.assertEquals(config.minDate, "01/01/2020")
        Assert.assertEquals(config.maxDate, "31/12/2020")
        Assert.assertFalse(config.applyButtonVisibility!!)
    }

}