package com.mercadolibre.android.andesui.tooltip.actions

import android.os.Build
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.soloader.SoLoader
import com.mercadolibre.android.andesui.tooltip.AndesTooltip
import com.mercadolibre.android.andesui.tooltip.location.AndesTooltipLocation
import com.mercadolibre.android.andesui.tooltip.style.AndesTooltipStyle
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
@Suppress("LongParameterList")
class AndesTooltipActionsCasesTests(
    private val style: AndesTooltipStyle,
    private val title: String?,
    private val body: String,
    private val isDismissible: Boolean,
    private val tooltipLocation: AndesTooltipLocation,
    private val mainAction: AndesTooltipAction?,
    private val secondaryAction: AndesTooltipAction?,
    private val linkAction: AndesTooltipLinkAction?
) {

    private var context = RuntimeEnvironment.application
    private lateinit var tooltip: AndesTooltip

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<Array<Any?>> {
            return listOf(
                    tooltipNoAction,
                    tooltipLoud,
                    tooltipLoudAndQuiet,
                    tooltipLoudAndTransparent,
                    tooltipLink
            )
        }
    }

    @Before
    fun setUp() {
        SoLoader.setInTestMode()
        val requestListeners = setOf<RequestListener>(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(context)
                // other setters
                .setRequestListeners(requestListeners)
                .build()
        Fresco.initialize(context, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
    }

    @Test
    fun `should build tooltip with actions passed`() {
        tooltip = buildTooltip()

        Assert.assertEquals(title, tooltip.title)
        Assert.assertEquals(body, tooltip.body)
        Assert.assertEquals(style, tooltip.style)
        Assert.assertEquals(isDismissible, tooltip.isDismissible)
        Assert.assertEquals(tooltipLocation, tooltip.location)
        Assert.assertEquals(mainAction, tooltip.mainAction)
        Assert.assertEquals(secondaryAction, tooltip.secondaryAction)
        Assert.assertEquals(linkAction, tooltip.linkAction)
    }

    private fun buildTooltip(): AndesTooltip {
        return when {
            mainAction != null -> AndesTooltip(
                    context = context,
                    style = style,
                    title = title,
                    body = body,
                    isDismissible = isDismissible,
                    tooltipLocation = tooltipLocation,
                    mainAction = mainAction,
                    secondaryAction = secondaryAction
            )

            linkAction != null -> AndesTooltip(
                    context = context,
                    style = style,
                    title = title,
                    body = body,
                    isDismissible = isDismissible,
                    tooltipLocation = tooltipLocation,
                    linkAction = linkAction
            )

            else -> AndesTooltip(
                    context = context,
                    style = style,
                    title = title,
                    body = body,
                    isDismissible = isDismissible,
                    tooltipLocation = tooltipLocation
            )
        }
    }
}
