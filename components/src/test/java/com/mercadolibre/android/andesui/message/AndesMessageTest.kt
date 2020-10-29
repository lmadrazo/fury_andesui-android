package com.mercadolibre.android.andesui.message

import android.os.Build
import android.text.SpannableString
import android.text.style.ClickableSpan
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.soloader.SoLoader
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLink
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLinks
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesMessageTest {

    private var context = RuntimeEnvironment.application

    private lateinit var andesMessage: AndesMessage

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

    @Test
    fun `Body Links`() {
        var indexSelected = -1

        andesMessage = AndesMessage(context, AndesMessageHierarchy.LOUD, AndesMessageType.SUCCESS,
                "This is a body message", "Title", null, true, null)

        val links = listOf(AndesBodyLink(0, 10))
        andesMessage.bodyLinks = AndesBodyLinks(links, listener = {
            indexSelected = it
        })

        (andesMessage.bodyComponent.text as? SpannableString)?.let {
            it.getSpans(0, 10, ClickableSpan::class.java)[0].onClick(andesMessage.bodyComponent)
        }

        assertEquals(indexSelected, 0)
    }
}
