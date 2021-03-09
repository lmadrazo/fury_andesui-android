package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIcon
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIconOrientation
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.demoapp.feature.specs.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.feature.specs.launchSpecs
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator

class ProgressShowcaseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.firebase_analytics_progress_indicator))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun addProgress(container: View) {
        val andesButtonSmall = AndesButton(
                this,
                AndesButtonSize.SMALL,
                AndesButtonHierarchy.LOUD,
                null
        )
        andesButtonSmall.text = getString(R.string.loud_small_button_programmatic)
        andesButtonSmall.isEnabled = false

        val andesButtonMedium = AndesButton(
                this,
                AndesButtonSize.MEDIUM,
                AndesButtonHierarchy.LOUD,
                AndesButtonIcon("andesui_icon_dynamic", AndesButtonIconOrientation.LEFT)
        )
        andesButtonMedium.text = getString(R.string.loud_medium_button_programmatic)

        val andesButtonLarge = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.QUIET,
                AndesButtonIcon("andesui_icon_dynamic", AndesButtonIconOrientation.LEFT)
        )
        andesButtonLarge.text = getString(R.string.loud_large_button_programmatic)
        andesButtonLarge.hierarchy = AndesButtonHierarchy.LOUD
        andesButtonLarge.setOnClickListener {
            andesButtonLarge.text = getString(R.string.loud_large_button_text_updated)
            andesButtonLarge.size = AndesButtonSize.SMALL
        }

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.button_margin_vertical).toInt())

        andesButtonLarge.layoutParams = params
        andesButtonMedium.layoutParams = params
        andesButtonSmall.layoutParams = params

        val linearLoud = container.findViewById<LinearLayout>(R.id.andes_loud_container)
        linearLoud.addView(andesButtonLarge, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonMedium, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonSmall, linearLoud.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BUTTON)
        }
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)
        val layoutLoudButtons = inflater.inflate(
                R.layout.andesui_progress_showcase,
                null,
                false
        )
        return listOf<View>(layoutLoudButtons)
    }

    fun click(v: View) {
        if (v is AndesButton) {
            v.isLoading = !v.isLoading
        }
    }
}
