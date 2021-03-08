package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
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

class ButtonShowcaseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.andes_button_showcase))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesPagerAdapter
        addLoudButtons(adapter.views[0])
        addQuietButtons(adapter.views[1])
        addTransparentButtons(adapter.views[2])
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)
        val layoutLoudButtons = inflater.inflate(
                R.layout.andesui_loud_buttons_showcase, null, false)
        val layoutQuietButtons = inflater.inflate(
                R.layout.andesui_quiet_buttons_showcase, null, false)
        val layoutTransparentButtons = inflater.inflate(
                R.layout.andesui_transparent_buttons_showcase, null, false)
        val layoutLoadingButtons = inflater.inflate(
                R.layout.andesui_loading_buttons_showcase, null, false)

        return listOf<View>(layoutLoudButtons, layoutQuietButtons, layoutTransparentButtons, layoutLoadingButtons)
    }

    private fun addLoudButtons(container: View) {
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

        val andesButtonWithLeftDrawable = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.LOUD
        )
        andesButtonWithLeftDrawable.text = getString(R.string.loud_with_drawable_button_programmatic_left)
        andesButtonWithLeftDrawable.setIconDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)!!,
                AndesButtonIconOrientation.LEFT)

        val andesButtonWithRightDrawable = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.QUIET
        )
        andesButtonWithRightDrawable.text = getString(R.string.loud_with_drawable_button_programmatic_right)
        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.also {
            andesButtonWithRightDrawable.setIconDrawable(it, AndesButtonIconOrientation.RIGHT)
        }

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.button_margin_vertical).toInt())

        andesButtonLarge.layoutParams = params
        andesButtonMedium.layoutParams = params
        andesButtonSmall.layoutParams = params
        andesButtonWithLeftDrawable.layoutParams = params
        andesButtonWithRightDrawable.layoutParams = params

        val linearLoud = container.findViewById<LinearLayout>(R.id.andes_loud_container)
        linearLoud.addView(andesButtonLarge, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonMedium, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonSmall, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonWithLeftDrawable, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonWithRightDrawable, linearLoud.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun addQuietButtons(container: View) {
        val andesButtonSmall = AndesButton(
                this,
                AndesButtonSize.SMALL,
                AndesButtonHierarchy.QUIET,
                null
        )
        andesButtonSmall.text = getString(R.string.quiet_small_button_programmatic)

        val andesButtonMedium = AndesButton(
                this,
                AndesButtonSize.MEDIUM,
                AndesButtonHierarchy.QUIET,
                null
        )
        andesButtonMedium.text = getString(R.string.quiet_medium_button_programmatic)

        val andesButtonLarge = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.QUIET,
                AndesButtonIcon("andesui_icon_dynamic", AndesButtonIconOrientation.RIGHT)
        )
        andesButtonLarge.text = getString(R.string.quiet_large_button_programmatic)
        andesButtonLarge.setOnClickListener {
            andesButtonLarge.hierarchy = AndesButtonHierarchy.LOUD
            andesButtonLarge.text = getString(R.string.quiet_large_button_hierarchy_updated)
        }

        val andesButtonWithLeftDrawable = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.QUIET
        )
        andesButtonWithLeftDrawable.text = getString(R.string.loud_with_drawable_button_programmatic_left)
        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.also {
            andesButtonWithLeftDrawable.setIconDrawable(it, AndesButtonIconOrientation.LEFT)
        }

        val andesButtonWithRightDrawable = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.QUIET
        )
        andesButtonWithRightDrawable.text = getString(R.string.loud_with_drawable_button_programmatic_right)
        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.also {
            andesButtonWithRightDrawable.setIconDrawable(it, AndesButtonIconOrientation.RIGHT)
        }

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.button_margin_vertical).toInt())

        andesButtonLarge.layoutParams = params
        andesButtonMedium.layoutParams = params
        andesButtonSmall.layoutParams = params
        andesButtonWithLeftDrawable.layoutParams = params
        andesButtonWithRightDrawable.layoutParams = params

        val linearQuiet = container.findViewById<LinearLayout>(R.id.andes_quiet_container)
        linearQuiet.addView(andesButtonLarge, linearQuiet.childCount - 1)
        linearQuiet.addView(andesButtonMedium, linearQuiet.childCount - 1)
        linearQuiet.addView(andesButtonSmall, linearQuiet.childCount - 1)
        linearQuiet.addView(andesButtonWithLeftDrawable, linearQuiet.childCount - 1)
        linearQuiet.addView(andesButtonWithRightDrawable, linearQuiet.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun addTransparentButtons(container: View) {
        val andesButtonSmall = AndesButton(
                this,
                AndesButtonSize.SMALL,
                AndesButtonHierarchy.TRANSPARENT,
                null
        )
        andesButtonSmall.text = getString(R.string.transparent_small_button_programmatic)

        val andesButtonMedium = AndesButton(
                this,
                AndesButtonSize.MEDIUM,
                AndesButtonHierarchy.TRANSPARENT,
                AndesButtonIcon("andesui_icon_dynamic", AndesButtonIconOrientation.RIGHT)
        )
        andesButtonMedium.text = getString(R.string.transparent_medium_button_programmatic)

        val andesButtonLarge = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.TRANSPARENT
        )
        andesButtonLarge.text = getString(R.string.transparent_large_button_programmatic)

        val andesButtonLargeInt = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.TRANSPARENT,
                AndesButtonIcon("andesui_icon_dynamic", AndesButtonIconOrientation.RIGHT)
        )
        andesButtonLargeInt.text = getString(R.string.transparent_large_button_programmatic_int)

        val andesButtonWithLeftDrawable = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.TRANSPARENT
        )
        andesButtonWithLeftDrawable.text = getString(R.string.loud_with_drawable_button_programmatic_left)
        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.also {
            andesButtonWithLeftDrawable.setIconDrawable(it, AndesButtonIconOrientation.LEFT)
        }

        val andesButtonWithRightDrawable = AndesButton(
                this,
                AndesButtonSize.LARGE,
                AndesButtonHierarchy.TRANSPARENT
        )
        andesButtonWithRightDrawable.text = getString(R.string.loud_with_drawable_button_programmatic_right)
        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.also {
            andesButtonWithRightDrawable.setIconDrawable(it, AndesButtonIconOrientation.RIGHT)
        }

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.button_margin_vertical).toInt())

        andesButtonLarge.layoutParams = params
        andesButtonMedium.layoutParams = params
        andesButtonSmall.layoutParams = params
        andesButtonWithLeftDrawable.layoutParams = params
        andesButtonWithRightDrawable.layoutParams = params

        val linearTransparent = container.findViewById<LinearLayout>(R.id.andes_transparent_container)
        linearTransparent.addView(andesButtonLargeInt, linearTransparent.childCount - 1)
        linearTransparent.addView(andesButtonLarge, linearTransparent.childCount - 1)
        linearTransparent.addView(andesButtonMedium, linearTransparent.childCount - 1)
        linearTransparent.addView(andesButtonSmall, linearTransparent.childCount - 1)
        linearTransparent.addView(andesButtonWithLeftDrawable, linearTransparent.childCount - 1)
        linearTransparent.addView(andesButtonWithRightDrawable, linearTransparent.childCount - 1)

        bindAndesSpecsButton(container)
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BUTTON)
        }
    }

    fun click(v: View) {
        if (v is AndesButton) {
            v.isLoading = !v.isLoading
        }
    }
}
