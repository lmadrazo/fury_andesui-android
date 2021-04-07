package com.mercadolibre.android.andesui.demoapp.components.checkbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.checkbox.AndesCheckbox
import com.mercadolibre.android.andesui.checkbox.align.AndesCheckboxAlign
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.checkbox.type.AndesCheckboxType
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.CustomViewPager
import com.mercadolibre.android.andesui.demoapp.utils.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.utils.PageIndicator
import com.mercadolibre.android.andesui.demoapp.utils.launchSpecs
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

class CheckboxShowcaseActivity : AppCompatActivity() {

    private lateinit var viewPager: CustomViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        initActionBar()
        initViewPager()
        attachIndicator()
        loadViews()
    }

    private fun initActionBar() {
        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andes_demoapp_screen_checkbox)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewPager() {
        val inflater = LayoutInflater.from(this)
        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.adapter = AndesPagerAdapter(listOf<View>(
                inflater.inflate(R.layout.andesui_dynamic_checkbox, null, false),
                inflater.inflate(R.layout.andesui_static_checkbox, null, false)
        ))
    }

    private fun attachIndicator() {
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun loadViews() {
        val adapter = viewPager.adapter as AndesPagerAdapter
        addDynamicPage(adapter.views[0])
        addStaticPage(adapter.views[1])
    }

    @Suppress("ComplexMethod", "LongMethod")
    private fun addDynamicPage(container: View) {
        val checkbox: AndesCheckbox = container.findViewById(R.id.andesCheckbox)

        val spinnerType: Spinner = container.findViewById(R.id.spinnerType)
        ArrayAdapter.createFromResource(
                this, R.array.andes_checkbox_type_spinner, android.R.layout.simple_spinner_item)
                .let { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerType.adapter = adapter
                }

        val spinnerAlign: Spinner = container.findViewById(R.id.spinnerAlign)
        ArrayAdapter.createFromResource(
                this, R.array.andes_checkbox_align_spinner, android.R.layout.simple_spinner_item)
                .let { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerAlign.adapter = adapter
                }

        val spinnerStatus: Spinner = container.findViewById(R.id.spinnerStatus)
        ArrayAdapter.createFromResource(
                this, R.array.andes_checkbox_status_spinner, android.R.layout.simple_spinner_item)
                .let { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerStatus.adapter = adapter
                }

        val clearButton: AndesButton = container.findViewById(R.id.buttonClear)
        val changeButton: AndesButton = container.findViewById(R.id.buttonUpdate)
        val andesTextfield: AndesTextfield = container.findViewById(R.id.andesTextfield)

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
    }

    private fun addStaticPage(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_checkbox_specs_button)
                .setOnClickListener { launchSpecs(it.context, AndesSpecs.CHECKBOX) }
    }
}
