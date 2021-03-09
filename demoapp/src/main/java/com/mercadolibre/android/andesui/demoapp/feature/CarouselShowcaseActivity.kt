package com.mercadolibre.android.andesui.demoapp.feature

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.mercadolibre.android.andesui.carousel.AndesCarousel
import com.mercadolibre.android.andesui.carousel.margin.AndesCarouselMargin
import com.mercadolibre.android.andesui.carousel.utils.AndesCarouselDelegate
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType
import kotlinx.android.synthetic.main.andesui_carousel_showcase.*
import java.util.Date

class CarouselShowcaseActivity : BaseActivity(), AndesCarouselDelegate {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var screensTime: Long = 0
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_carousel_showcase)
        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_carousel)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        carouselMain.delegate = this

        ArrayAdapter.createFromResource(
                this,
                R.array.carousel_margin_spinner,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            marginSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
                this,
                R.array.carousel_center_spinner,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            centerSpinner.adapter = adapter
        }

        updateButton.setOnClickListener {
            val padding: AndesCarouselMargin = when (marginSpinner.selectedItem.toString()) {
                "None" -> AndesCarouselMargin.NONE
                else -> AndesCarouselMargin.DEFAULT
            }
            val center = when (centerSpinner.selectedItem.toString()) {
                "False" -> false
                else -> true
            }
            carouselMain.margin = padding
            carouselMain.center = center
        }

        val newCarousel = AndesCarousel(this, false, AndesCarouselMargin.NONE)
        newCarousel.delegate = this

        mainContainer.addView(newCarousel)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    override fun onDestroy() {
        if (startTime > 0) {
            val diff = Date().time - startTime
            screensTime += diff
        }

        // Track
        val bundle = Bundle()
        bundle.putLong("Screen 1", screensTime)
        firebaseAnalytics.logEvent(getString(R.string.firebase_analytics_carousel).replace(" ", ""), bundle)

        super.onDestroy()
    }

    override fun onStop() {
        val diff = Date().time - startTime
        screensTime += diff
        startTime = 0
        super.onStop()
    }

    override fun onResume() {
        startTime = Date().time
        super.onResume()
    }

    @SuppressLint("SetTextI18n")
    override fun bind(andesCarouselView: AndesCarousel, view: View, position: Int) {
        val model = getDataSet()[position]
        val textView1 = TextView(applicationContext)
        textView1.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                applicationContext.resources.getDimension(R.dimen.title_text_size_card)
        )
        textView1.setTextColor(applicationContext.resources.getColor(R.color.andes_gray_800))
        textView1.text = model.label
        view.findViewById<AndesMessage>(R.id.messageCarousel).apply {
            if (position % 2 == 0) {
                setupPrimaryAction(
                        "Primary",
                        View.OnClickListener {
                            Toast.makeText(context, "Primary onClick", Toast.LENGTH_SHORT).show()
                        }
                )
            } else {
                setupLinkAction(
                        "Link",
                        View.OnClickListener {
                            Toast.makeText(context, "Link onClick", Toast.LENGTH_SHORT).show()
                        }
                )
            }

            title = "Andes Message"
            body = model.label
            type = AndesMessageType.fromString(model.type)
            hierarchy = AndesMessageHierarchy.fromString(model.hierarchy)
        }
    }

    override fun getLayoutItem(andesCarouselView: AndesCarousel) = R.layout.andesui_carousel_item

    override fun onClickItem(andesCarouselView: AndesCarousel, position: Int) {
        Toast.makeText(this@CarouselShowcaseActivity, getDataSet()[position].toString(), Toast.LENGTH_SHORT).show()
    }

    override fun getDataSetSize(andesCarouselView: AndesCarousel) = getDataSet().size

    private fun getDataSet() = listOf(
            Model(Color.RED, "Button Nº4", "Neutral", "Quiet"),
            Model(Color.RED, "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "", "Neutral", "Loud"),
            Model(Color.RED, "Button Nº4", "Success", "Loud"),
            Model(Color.RED, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ", "Success", "Quiet"),
            Model(Color.RED, "Button Nº4 Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "Warning", "Quiet"),
            Model(Color.RED, "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ", "Warning", "Loud")
    )
}

data class Model(val backgroundColor: Int, val label: String, val type: String, val hierarchy: String)
