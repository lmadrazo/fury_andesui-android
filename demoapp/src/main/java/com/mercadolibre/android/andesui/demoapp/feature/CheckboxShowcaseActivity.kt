package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Spinner
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.checkbox.AndesCheckbox
import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.demoapp.feature.specs.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.feature.specs.launchSpecs
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

class CheckboxShowcaseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_checkbox)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.andes_checkbox_showcase))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)
        val staticCheckboxLayout = addStaticCheckbox(inflater)
        val dynamicCheckboxLayout = addDynamicCheckbox(inflater)
        return listOf(dynamicCheckboxLayout, staticCheckboxLayout)
    }

    @Suppress("ComplexMethod", "LongMethod")
    private fun addDynamicCheckbox(inflater: LayoutInflater): View {
        val layoutCheckbox = inflater.inflate(
                R.layout.andesui_dynamic_checkbox_showcase,
                null,
                false
        ) as ScrollView

        val checkbox: AndesCheckbox = layoutCheckbox.findViewById(R.id.andesCheckbox)

        val spinnerType: Spinner = layoutCheckbox.findViewById(R.id.spinnerType)
        ArrayAdapter.createFromResource(
                this, R.array.type_checkbox_spinner, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerType.adapter = adapter
                }

        val spinnerAlign: Spinner = layoutCheckbox.findViewById(R.id.spinnerAlign)
        ArrayAdapter.createFromResource(
                this, R.array.align_checkbox_spinner, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerAlign.adapter = adapter
                }

        val spinnerStatus: Spinner = layoutCheckbox.findViewById(R.id.spinnerStatus)
        ArrayAdapter.createFromResource(
                this, R.array.status_checkbox_spinner, android.R.layout.simple_spinner_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerStatus.adapter = adapter
                }

        val clearButton: AndesButton = layoutCheckbox.findViewById(R.id.buttonClear)
        val changeButton: AndesButton = layoutCheckbox.findViewById(R.id.buttonUpdate)
        val andesTextfield: AndesTextfield = layoutCheckbox.findViewById(R.id.andesTextfield)

        clearButton.setOnClickListener {
            spinnerType.setSelection(0)
            spinnerAlign.setSelection(0)
            spinnerStatus.setSelection(0)

            andesTextfield.text = ""
            andesTextfield.state = AndesTextfieldState.IDLE
            andesTextfield.helper = null

            checkbox.align = AndesCheckboxAlign.LEFT
            checkbox.type = AndesCheckboxType.IDLE
            checkbox.status = AndesCheckboxStatus.UNSELECTED
            checkbox.text = resources.getString(R.string.andes_checkbox_text)
        }

        changeButton.setOnClickListener {
            if (andesTextfield.text.isNullOrEmpty()) {
                andesTextfield.state = AndesTextfieldState.ERROR
                andesTextfield.helper = "Este campo es requerido"
                return@setOnClickListener
            } else {
                andesTextfield.state = AndesTextfieldState.IDLE
                andesTextfield.helper = null
            }

            val type = when (spinnerType.selectedItem) {
                "Idle" -> AndesCheckboxType.IDLE
                "Error" -> AndesCheckboxType.ERROR
                "Disabled" -> AndesCheckboxType.DISABLED
                else -> AndesCheckboxType.IDLE
            }
            val align = when (spinnerAlign.selectedItem) {
                "Left" -> AndesCheckboxAlign.LEFT
                "Right" -> AndesCheckboxAlign.RIGHT
                else -> AndesCheckboxAlign.LEFT
            }
            val status = when (spinnerStatus.selectedItem) {
                "Unselected" -> AndesCheckboxStatus.UNSELECTED
                "Selected" -> AndesCheckboxStatus.SELECTED
                "Undefined" -> AndesCheckboxStatus.UNDEFINED
                else -> AndesCheckboxStatus.UNSELECTED
            }
            checkbox.align = align
            checkbox.type = type
            checkbox.status = status
            checkbox.text = andesTextfield.text
        }

        return layoutCheckbox
    }

    private fun addStaticCheckbox(inflater: LayoutInflater): View {
        val layoutCheckbox = inflater.inflate(
                R.layout.andesui_checkbox_showcase,
                null,
                false
        ) as ScrollView

        layoutCheckbox.findViewById<AndesButton>(R.id.andesui_demoapp_andes_checkbox_specs_button)
                .setOnClickListener { launchSpecs(it.context, AndesSpecs.CHECKBOX) }

        return layoutCheckbox
    }

}
