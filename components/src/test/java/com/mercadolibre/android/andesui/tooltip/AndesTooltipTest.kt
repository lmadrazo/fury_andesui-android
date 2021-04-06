package com.mercadolibre.android.andesui.tooltip

import android.os.Build
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.soloader.SoLoader
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.tooltip.actions.AndesTooltipAction
import com.mercadolibre.android.andesui.tooltip.actions.AndesTooltipLinkAction
import com.mercadolibre.android.andesui.tooltip.location.AndesTooltipLocation
import com.mercadolibre.android.andesui.tooltip.location.BottomAndesTooltipLocationConfig
import com.mercadolibre.android.andesui.tooltip.style.AndesTooltipStyle
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertNull
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.mockito.Mockito.validateMockitoUsage
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.mockito.Mockito.never
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.util.ReflectionHelpers

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesTooltipTest {
    private var context = RuntimeEnvironment.application

    private val title = "My title"
    private val body = "my body"
    private val isDismissible = false
    private val location = AndesTooltipLocation.BOTTOM

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            SoLoader.setInTestMode()
        }
    }

    @Before
    fun setUp() {
        val requestListeners = setOf<RequestListener>(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(context)
                // other setters
                .setRequestListeners(requestListeners)
                .build()
        Fresco.initialize(context, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
    }

    @After
    fun validate() {
        validateMockitoUsage()
    }

    @Test
    fun `should build basic tooltip`() {
        val body = "my body"
        val isDismissibleDefault = true
        val locationDefault = AndesTooltipLocation.TOP
        val styleDefault = AndesTooltipStyle.LIGHT

        val basicTooltip = AndesTooltip(context = context, body = body)

        assertEquals(body, basicTooltip.body)
        assertEquals(isDismissibleDefault, basicTooltip.isDismissible)
        assertEquals(locationDefault, basicTooltip.location)
        assertEquals(styleDefault, basicTooltip.style)
        assertNull(basicTooltip.title)
        assertNull(basicTooltip.mainAction)
        assertNull(basicTooltip.secondaryAction)
        assertNull(basicTooltip.linkAction)
    }

    @Test
    fun `should NOT build tooltip with main action transparent`() {
        val mainTransparentAction = AndesTooltipAction(
                label = "main action",
                hierarchy = AndesButtonHierarchy.TRANSPARENT,
                onActionClicked = { _, _ -> }
        )
        val tooltip = AndesTooltip(context = context, body = body)

        assertThrows(IllegalStateException::class.java) {
            tooltip.mainAction = mainTransparentAction
        }
    }

    @Test
    fun `should NOT build tooltip with secondary action loud`() {
        val mainActionData = AndesTooltipAction(
                label = "main action",
                hierarchy = AndesButtonHierarchy.LOUD,
                onActionClicked = { _, _ -> }
        )
        val secondaryLoudAction = AndesTooltipAction(
                label = "sec action",
                hierarchy = AndesButtonHierarchy.LOUD,
                onActionClicked = { _, _ -> }
        )
        val tooltip = spy(AndesTooltip(
                context = context,
                style = AndesTooltipStyle.LIGHT,
                title = title,
                body = body,
                isDismissible = isDismissible,
                tooltipLocation = location
        ))

        tooltip.mainAction = mainActionData

        assertThrows(IllegalStateException::class.java) {
            tooltip.secondaryAction = secondaryLoudAction
        }
    }

    @Test
    fun `should NOT build tooltip with another AndesTooltipStyle`() {
        val tooltip = spy(AndesTooltip(
                context = context,
                style = AndesTooltipStyle.LIGHT,
                title = title,
                body = body,
                isDismissible = isDismissible,
                tooltipLocation = location
        ))
        assertThrows(IllegalStateException::class.java) {
            tooltip.style = AndesTooltipStyle.DARK
        }
    }

    @Test
    fun `should execute passed onClick action`() {
        val onClickFunction: (view: View, tooltip: AndesTooltip) -> Unit = spy({ _, _ -> })
        val mainActionData = mock<AndesTooltipAction>()
        `when`(mainActionData.onActionClicked).thenReturn(onClickFunction)

        val tooltip = AndesTooltip(context = context, body = body)
        tooltip.mainAction = mainActionData

        val mainActionButton = ReflectionHelpers.getField(
                tooltip,
                "primaryActionComponent"
        ) as AndesButton

        mainActionButton.performClick()
        verify(mainActionData.onActionClicked).invoke(mainActionButton, tooltip)
    }

    @Test
    fun `should execute passed onClick secondary action`() {

        val onClickFunction: (view: View, tooltip: AndesTooltip) -> Unit = spy({ _, _ -> })
        val mainActionData = mock<AndesTooltipAction>()
        `when`(mainActionData.onActionClicked).thenReturn(onClickFunction)

        val onClickFunctionSec: (view: View, tooltip: AndesTooltip) -> Unit = spy({ _, _ -> })
        val secActionData = mock<AndesTooltipAction>()
        `when`(secActionData.onActionClicked).thenReturn(onClickFunctionSec)

        val tooltip = spy(AndesTooltip(context = context, body = body))

        tooltip.mainAction = mainActionData
        tooltip.secondaryAction = secActionData

        val secActionButton = ReflectionHelpers.getField(
                tooltip,
                "secondaryActionComponent"
        ) as AndesButton

        secActionButton.performClick()
        verify(secActionData.onActionClicked).invoke(secActionButton, tooltip)
    }

    @Test
    fun `should execute passed onClick link action`() {
        val onLinkActionClicked: (view: View, tooltip: AndesTooltip) -> Unit = spy({ _, _ -> })
        val linkActionData = mock<AndesTooltipLinkAction>()
        `when`(linkActionData.onActionClicked).thenReturn(onLinkActionClicked)

        val tooltip = spy(AndesTooltip(context = context, body = body))
        tooltip.linkAction = linkActionData

        val linkActionButton = ReflectionHelpers.getField(
                tooltip,
                "linkActionComponent"
        ) as TextView

        linkActionButton.performClick()
        verify(onLinkActionClicked).invoke(linkActionButton, tooltip)
    }

    @Test
    fun `should apply title`() {
        val newTitle = "my new title"
        val tooltip = AndesTooltip(context = context, body = body)

        tooltip.title = newTitle

        assertEquals(newTitle, tooltip.title)
    }

    @Test
    fun `should apply body`() {
        val newBody = "my new body"
        val tooltip = spy(AndesTooltip(
                context = context,
                style = AndesTooltipStyle.LIGHT,
                title = title,
                body = body,
                isDismissible = isDismissible,
                tooltipLocation = location
        ))

        assertEquals(tooltip.body, body)

        tooltip.body = newBody

        assertEquals(tooltip.body, newBody)
    }

    @Test
    fun `should apply isDismissible`() {
        val newValue = true
        val tooltip = AndesTooltip(context = context, body = body)

        tooltip.isDismissible = newValue

        assertEquals(tooltip.isDismissible, newValue)
    }

    @Test
    fun `should apply mainAction`() {
        val newValue = AndesTooltipAction(
                label = "main action",
                hierarchy = AndesButtonHierarchy.LOUD,
                onActionClicked = { _, _ -> }
        )
        val tooltip = spy(AndesTooltip(
                context = context,
                style = AndesTooltipStyle.LIGHT,
                title = title,
                body = body,
                isDismissible = isDismissible,
                tooltipLocation = location
        ))

        assertNotEquals(tooltip.mainAction, newValue)

        tooltip.mainAction = newValue

        assertEquals(tooltip.mainAction, newValue)
    }

    @Test
    fun `should apply secondaryAction`() {
        val tooltip = AndesTooltip(context = context, body = body)
        val newValueMain = AndesTooltipAction(
                label = "main action",
                hierarchy = AndesButtonHierarchy.LOUD,
                onActionClicked = { _, _ -> }
        )

        val newValue = AndesTooltipAction(
                label = "sec action",
                hierarchy = AndesButtonHierarchy.QUIET,
                onActionClicked = { _, _ -> }
        )

        assertNotEquals(tooltip.secondaryAction, newValue)

        tooltip.mainAction = newValueMain
        tooltip.secondaryAction = newValue

        assertEquals(tooltip.secondaryAction, newValue)
    }

    @Test
    fun `should apply location`() {
        val newValue = AndesTooltipLocation.RIGHT
        val tooltip = spy(AndesTooltip(
                context = context,
                style = AndesTooltipStyle.LIGHT,
                title = title,
                body = body,
                isDismissible = isDismissible,
                tooltipLocation = location
        ))

        assertNotEquals(tooltip.location, newValue)

        tooltip.location = newValue

        assertEquals(tooltip.location, newValue)
    }

    @Test
    fun `should show tooltip by required location config`() {
        val tooltip = spy(AndesTooltip(context = context, body = body))
        val mockTarget: View = AndesButton(context)
        val locationConfig = mock<BottomAndesTooltipLocationConfig>()

        ReflectionHelpers.setField(tooltip, "andesTooltipLocationConfigRequired", locationConfig)
        `when`(tooltip.canShowTooltip(mockTarget)).thenReturn(true)
        `when`(locationConfig.canBuildTooltipInRequiredLocation(mockTarget)).thenReturn(true)

        tooltip.show(mockTarget)

        verify(locationConfig, times(1)).canBuildTooltipInRequiredLocation(mockTarget)
        verify(locationConfig, never()).iterateOtherLocations(mockTarget)
    }

    @Test
    fun `should show tooltip by another location config`() {
        val tooltip = spy(AndesTooltip(context = context, body = body))
        val mockTarget: View = AndesButton(context)
        val locationConfig = mock<BottomAndesTooltipLocationConfig>()
        ReflectionHelpers.setField(tooltip, "andesTooltipLocationConfigRequired", locationConfig)
        `when`(tooltip.canShowTooltip(mockTarget)).thenReturn(true)
        `when`(locationConfig.canBuildTooltipInRequiredLocation(mockTarget)).thenReturn(false)

        tooltip.show(mockTarget)

        verify(locationConfig, times(1)).canBuildTooltipInRequiredLocation(mockTarget)
        verify(locationConfig).iterateOtherLocations(mockTarget)
    }

    @Test
    fun `should showDropdown bodyWindow with config`() {
        val tooltip = spy(AndesTooltip(
                context = context,
                title = title,
                body = body
        ))
        val mockTarget: View = spy(AndesButton(context))
        val locationConfig = spy(BottomAndesTooltipLocationConfig(tooltip))
        ReflectionHelpers.setField(tooltip, "andesTooltipLocationConfigRequired", locationConfig)
        `when`(tooltip.canShowTooltip(mockTarget)).thenReturn(true)

        tooltip.show(mockTarget)

        verify(locationConfig).canBuildTooltipInRequiredLocation(mockTarget)
        verify(tooltip).showDropDown(mockTarget, -30, 0, locationConfig)
    }

    @Test
    fun `should dismiss tooltip`() {
        val tooltip = spy(AndesTooltip(
                context = context,
                title = title,
                body = body
        ))

        val bodyWindow: PopupWindow = mock()
        ReflectionHelpers.setField(tooltip, "bodyWindow", bodyWindow)
        ReflectionHelpers.setField(tooltip, "isShowing", true)

        tooltip.dismiss()

        verify(bodyWindow).dismiss()
    }
}
