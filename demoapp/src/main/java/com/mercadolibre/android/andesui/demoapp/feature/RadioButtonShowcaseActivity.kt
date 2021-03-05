package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.Toast
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.demoapp.feature.specs.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.feature.specs.launchSpecs
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.radiobutton.AndesRadioButton
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType
import com.mercadolibre.android.andesui.radiobuttongroup.AndesRadioButtonGroup
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonItem
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

class RadioButtonShowcaseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_radiobutton)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.andes_radiobutton_showcase))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)

        val staticRadioButtonLayout = addStaticRadioButton(inflater)
        val dynamicRadioButtonLayout = addDynamicRadioButton(inflater)
        val dynamicRadioButtonGroupLayout = adddynamicRadioButtonGroupLayout(inflater)

        return listOf(dynamicRadioButtonLayout, staticRadioButtonLayout, dynamicRadioButtonGroupLayout)
    }

    @Suppress("ComplexMethod", "LongMethod")
    private fun addDynamicRadioButton(inflater: LayoutInflater): View {
        val layoutRadioButton = inflater.inflate(
                R.layout.andesui_dynamic_radiobutton_showcase,
                null,
                false
        ) as ScrollView

        val radioButton: AndesRadioButton = layoutRadioButton.findViewById(R.id.andesRadioButton)

        val spinnerType: Spinner = layoutRadioButton.findViewById(R.id.spinnerType)
        ArrayAdapter.createFromResource(
                this,
                R.array.type_radiobutton_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerType.adapter = adapter
                }

        val spinnerAlign: Spinner = layoutRadioButton.findViewById(R.id.spinnerAlign)
        ArrayAdapter.createFromResource(
                this,
                R.array.align_radiobutton_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerAlign.adapter = adapter
                }

        val spinnerStatus: Spinner = layoutRadioButton.findViewById(R.id.spinnerStatus)
        ArrayAdapter.createFromResource(
                this,
                R.array.status_radiobutton_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerStatus.adapter = adapter
                }

        val clearButton: AndesButton = layoutRadioButton.findViewById(R.id.buttonClear)
        val changeButton: AndesButton = layoutRadioButton.findViewById(R.id.buttonUpdate)
        val andesTextfield: AndesTextfield = layoutRadioButton.findViewById(R.id.andesTextfield)

        clearButton.setOnClickListener {
            spinnerType.setSelection(0)
            spinnerAlign.setSelection(0)
            spinnerStatus.setSelection(0)

            andesTextfield.text = ""
            andesTextfield.state = AndesTextfieldState.IDLE
            andesTextfield.helper = null

            radioButton.align = AndesRadioButtonAlign.LEFT
            radioButton.type = AndesRadioButtonType.IDLE
            radioButton.status = AndesRadioButtonStatus.UNSELECTED
            radioButton.text = resources.getString(R.string.andes_radiobutton_text)
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
                "Idle" -> AndesRadioButtonType.IDLE
                "Error" -> AndesRadioButtonType.ERROR
                "Disabled" -> AndesRadioButtonType.DISABLED
                else -> AndesRadioButtonType.IDLE
            }
            val align = when (spinnerAlign.selectedItem) {
                "Left" -> AndesRadioButtonAlign.LEFT
                "Right" -> AndesRadioButtonAlign.RIGHT
                else -> AndesRadioButtonAlign.LEFT
            }
            val status = when (spinnerStatus.selectedItem) {
                "Unselected" -> AndesRadioButtonStatus.UNSELECTED
                "Selected" -> AndesRadioButtonStatus.SELECTED
                else -> AndesRadioButtonStatus.UNSELECTED
            }
            radioButton.align = align
            radioButton.type = type
            radioButton.status = status
            radioButton.text = andesTextfield.text
        }

        return layoutRadioButton
    }

    private fun addStaticRadioButton(inflater: LayoutInflater): View {
        val layoutRadioButton = inflater.inflate(
                R.layout.andesui_radiobutton_showcase,
                null,
                false
        ) as ScrollView

        layoutRadioButton.findViewById<AndesButton>(
                R.id.andesui_demoapp_andes_radiobutton_specs_button
        ).setOnClickListener {
            launchSpecs(it.context, AndesSpecs.RADIOBUTTON)
        }

        return layoutRadioButton
    }

    private fun adddynamicRadioButtonGroupLayout(inflater: LayoutInflater): View {
        val layoutRadioButton = inflater.inflate(
                R.layout.andesui_dynamic_radiobuttongroup_showcase,
                null,
                false
        ) as ScrollView

        val radioButtons = arrayListOf<RadioButtonItem>()
        radioButtons.add(RadioButtonItem("item 1", AndesRadioButtonType.DISABLED))
        radioButtons.add(RadioButtonItem("item 2", AndesRadioButtonType.IDLE))
        radioButtons.add(RadioButtonItem("item 3", AndesRadioButtonType.IDLE))
        radioButtons.add(RadioButtonItem("item 4", AndesRadioButtonType.IDLE))
        radioButtons.add(RadioButtonItem("item 5", AndesRadioButtonType.IDLE))

        val radioButtonGroup = layoutRadioButton.findViewById<AndesRadioButtonGroup>(R.id.radioButtonGroup1)
        radioButtonGroup.selected = 1
        radioButtonGroup.radioButtons = radioButtons
        radioButtonGroup.setupCallback(
                object : AndesRadioButtonGroup.OnRadioButtonCheckedChanged {
                    override fun onRadioButtonCheckedChanged(index: Int) {
                        Toast.makeText(this@RadioButtonShowcaseActivity, "Radiobutton clicked, index: $index", Toast.LENGTH_LONG).show()
                    }
                }
        )

        val radioButtonGroupHorizontal = layoutRadioButton.findViewById<AndesRadioButtonGroup>(
                R.id.radioButtonGroup2
        )
        radioButtonGroupHorizontal.selected = 1
        radioButtonGroupHorizontal.radioButtons = radioButtons
        radioButtonGroupHorizontal.setupCallback(
                object : AndesRadioButtonGroup.OnRadioButtonCheckedChanged {
                    override fun onRadioButtonCheckedChanged(index: Int) {
                        Toast.makeText(this@RadioButtonShowcaseActivity, "Radiobutton clicked, index: $index", Toast.LENGTH_LONG).show()
                    }
                }
        )

        return layoutRadioButton
    }

}
