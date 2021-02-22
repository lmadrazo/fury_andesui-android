package com.mercadolibre.android.andesui.snackbar

import android.content.Context
import android.os.Build
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.test.platform.app.InstrumentationRegistry
import android.view.View
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.snackbar.duration.AndesSnackbarDuration
import com.mercadolibre.android.andesui.snackbar.type.AndesSnackbarType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesSnackbarTest {

    lateinit var context: Context
    lateinit var view: View

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        context.setTheme(R.style.Theme_AppCompat_Light)
        view = CoordinatorLayout(context)
    }

    @Test
    fun `Snackbar, Neutral`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.NEUTRAL, MESSAGE, AndesSnackbarDuration.SHORT)
        Assert.assertEquals(snackbar.type, AndesSnackbarType.NEUTRAL)
    }

    @Test
    fun `Snackbar, Success`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.SUCCESS, MESSAGE, AndesSnackbarDuration.SHORT)
        Assert.assertEquals(snackbar.type, AndesSnackbarType.SUCCESS)
    }

    @Test
    fun `Snackbar, Error`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.ERROR, MESSAGE, AndesSnackbarDuration.SHORT)
        Assert.assertEquals(snackbar.type, AndesSnackbarType.ERROR)
    }

    @Test
    fun `Snackbar, Short duration`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.NEUTRAL, MESSAGE, AndesSnackbarDuration.SHORT)
        Assert.assertEquals(snackbar.duration, AndesSnackbarDuration.SHORT)
    }

    @Test
    fun `Snackbar, Normal duration`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.NEUTRAL, MESSAGE, AndesSnackbarDuration.NORMAL)
        Assert.assertEquals(snackbar.duration, AndesSnackbarDuration.NORMAL)
    }

    @Test
    fun `Snackbar, Long duration`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.NEUTRAL, MESSAGE, AndesSnackbarDuration.LONG)
        Assert.assertEquals(snackbar.duration, AndesSnackbarDuration.LONG)
    }

    @Test
    fun `Snackbar, Message`() {
        val snackbar = AndesSnackbar(context, view, AndesSnackbarType.NEUTRAL, MESSAGE, AndesSnackbarDuration.NORMAL)
        Assert.assertEquals(snackbar.text, MESSAGE)
    }

    @Test
    fun `Snackbar, isShow before show`() {
        val snackbar = AndesSnackbar(
                context,
                view,
                AndesSnackbarType.NEUTRAL,
                MESSAGE,
                AndesSnackbarDuration.SHORT
        )
        Assert.assertEquals(snackbar.isShown, false)
    }

    @Test
    fun `Snackbar, isShow after show`() {
        val snackbar = AndesSnackbar(
                context,
                view,
                AndesSnackbarType.NEUTRAL,
                MESSAGE,
                AndesSnackbarDuration.SHORT
        )
        snackbar.show()
        Assert.assertEquals(snackbar.isShown, true)
    }

    companion object {
        const val MESSAGE = "Snackbar"
    }
}
