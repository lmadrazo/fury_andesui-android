package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.demoapp.feature.specs.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.feature.specs.launchSpecs
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.thumbnail.AndesThumbnail
import com.mercadolibre.android.andesui.thumbnail.hierarchy.AndesThumbnailHierarchy
import com.mercadolibre.android.andesui.thumbnail.size.AndesThumbnailSize
import com.mercadolibre.android.andesui.thumbnail.state.AndesThumbnailState
import com.mercadolibre.android.andesui.thumbnail.type.AndesThumbnailType
import kotlinx.android.synthetic.main.andesui_thumbnail_showcase_change.*

class ThumbnailShowcaseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_thumbnail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.firebase_analytics_thumbnail))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesPagerAdapter
        addDynamicThumbnails(adapter.views[0])
        bindAndesSpecsButton(adapter.views[1])
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)
        val layoutChangeThumbnails = inflater.inflate(R.layout.andesui_thumbnail_showcase_change, null, false)
        val layoutThumbnails = inflater.inflate(R.layout.andesui_thumbnails_showcase, null, false)

        return listOf<View>(layoutChangeThumbnails, layoutThumbnails)
    }

    @Suppress("ComplexMethod", "LongMethod")
    private fun addDynamicThumbnails(container: View) {
        val hierarchySpinner: Spinner = container.findViewById(R.id.hierarchy_spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.thumbnail_hierarchy_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    hierarchySpinner.adapter = adapter
                }

        val typeSpinner: Spinner = container.findViewById(R.id.simple_type_spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.thumbnail_type_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    typeSpinner.adapter = adapter
                }

        val sizeSpinner: Spinner = container.findViewById(R.id.size_spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.thumbnail_size_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sizeSpinner.adapter = adapter
                }

        val stateSwitch: Switch = container.findViewById(R.id.state_switch)

        val clearButton: AndesButton = container.findViewById(R.id.clear_button)
        val changeButton: AndesButton = container.findViewById(R.id.change_button)

        val andesThumbnail: AndesThumbnail = container.findViewById(R.id.andes_thumbnail_icon)

        hierarchySpinner.setSelection(DEFAULT_HIERARCHY_OPTION)
        typeSpinner.setSelection(DEFAULT_TYPE_OPTION)
        sizeSpinner.setSelection(DEFAULT_SIZE_OPTION)
        stateSwitch.isChecked = true

        clearButton.setOnClickListener {
            group.visibility = View.VISIBLE
            andesThumbnail.visibility = View.VISIBLE

            hierarchySpinner.setSelection(DEFAULT_HIERARCHY_OPTION)
            typeSpinner.setSelection(DEFAULT_TYPE_OPTION)
            sizeSpinner.setSelection(DEFAULT_SIZE_OPTION)
            stateSwitch.isChecked = true

            andesThumbnail.type = AndesThumbnailType.ICON
            andesThumbnail.hierarchy = AndesThumbnailHierarchy.LOUD
            andesThumbnail.size = AndesThumbnailSize.SIZE_48
            andesThumbnail.accentColor = AndesColor(R.color.andes_red_700)
            andesThumbnail.state = AndesThumbnailState.ENABLED
        }

        changeButton.setOnClickListener {
            val type = when (typeSpinner.getItemAtPosition(typeSpinner.selectedItemPosition)) {
                "Icon" -> AndesThumbnailType.ICON
                else -> AndesThumbnailType.ICON
            }

            val hierarchy = when (hierarchySpinner.getItemAtPosition(hierarchySpinner.selectedItemPosition)) {
                "Loud" -> AndesThumbnailHierarchy.LOUD
                "Quiet" -> AndesThumbnailHierarchy.QUIET
                "Default" -> AndesThumbnailHierarchy.DEFAULT
                else -> AndesThumbnailHierarchy.LOUD
            }

            val size = when (sizeSpinner.getItemAtPosition(sizeSpinner.selectedItemPosition)) {
                "Size 24" -> AndesThumbnailSize.SIZE_24
                "Size 32" -> AndesThumbnailSize.SIZE_32
                "Size 40" -> AndesThumbnailSize.SIZE_40
                "Size 48" -> AndesThumbnailSize.SIZE_48
                "Size 56" -> AndesThumbnailSize.SIZE_56
                "Size 64" -> AndesThumbnailSize.SIZE_64
                "Size 72" -> AndesThumbnailSize.SIZE_72
                "Size 80" -> AndesThumbnailSize.SIZE_80
                else -> AndesThumbnailSize.SIZE_48
            }

            andesThumbnail.type = type
            andesThumbnail.hierarchy = hierarchy
            andesThumbnail.size = size
            andesThumbnail.state = if (stateSwitch.isChecked) {
                AndesThumbnailState.ENABLED
            } else {
                AndesThumbnailState.DISABLED
            }
        }
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.THUMBNAIL)
        }
    }

    companion object {
        const val DEFAULT_HIERARCHY_OPTION = 1
        const val DEFAULT_TYPE_OPTION = 0
        const val DEFAULT_SIZE_OPTION = 3
    }
}
