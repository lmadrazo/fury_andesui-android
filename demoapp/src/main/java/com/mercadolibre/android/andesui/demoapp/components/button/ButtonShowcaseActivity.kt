package com.mercadolibre.android.andesui.demoapp.components.button

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIconOrientation
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.checkbox.AndesCheckbox
import com.mercadolibre.android.andesui.checkbox.status.AndesCheckboxStatus
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.CustomViewPager
import com.mercadolibre.android.andesui.demoapp.utils.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.utils.PageIndicator
import com.mercadolibre.android.andesui.demoapp.utils.launchSpecs
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState
import kotlinx.android.synthetic.main.andesui_static_buttons.*

class ButtonShowcaseActivity : AppCompatActivity() {

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
        supportActionBar?.title = resources.getString(R.string.andes_demoapp_screen_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewPager() {
        val inflater = LayoutInflater.from(this)
        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.adapter = AndesPagerAdapter(listOf<View>(
                inflater.inflate(R.layout.andesui_dynamic_buttons, null, false),
                inflater.inflate(R.layout.andesui_static_buttons, null, false)
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

    private fun addDynamicPage(container: View) {
        val andesButton = container.findViewById<AndesButton>(R.id.andes_button)
        val changeButton = container.findViewById<AndesButton>(R.id.change_button)
        val clearButton = container.findViewById<AndesButton>(R.id.clear_button)
        val loadingCheckbox = container.findViewById<AndesCheckbox>(R.id.checkbox_loading)
        val enabledCheckbox = container.findViewById<AndesCheckbox>(R.id.checkbox_enabled)
        val text = container.findViewById<AndesTextfield>(R.id.label_text)

        val sizeSpinner: Spinner = container.findViewById(R.id.size_spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.andes_button_size_spinner,
                android.R.layout.simple_spinner_item
        ).let { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sizeSpinner.adapter = adapter
        }

        val hierarchySpinner: Spinner = container.findViewById(R.id.hierarchy_spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.andes_button_hierarchy_spinner,
                android.R.layout.simple_spinner_item
        ).let { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            hierarchySpinner.adapter = adapter
        }

        andesButton.setOnClickListener {
            if (loadingCheckbox.status == AndesCheckboxStatus.SELECTED) {
                (it as AndesButton).isLoading = !it.isLoading
            }
        }

        clearButton.setOnClickListener {
            sizeSpinner.setSelection(0)
            hierarchySpinner.setSelection(0)

            enabledCheckbox.status = AndesCheckboxStatus.SELECTED
            loadingCheckbox.status = AndesCheckboxStatus.UNSELECTED

            andesButton.size = AndesButtonSize.LARGE
            andesButton.hierarchy = AndesButtonHierarchy.LOUD
            andesButton.isEnabled = true
            andesButton.text = getString(R.string.andes_button)

            text.text = null
        }

        changeButton.setOnClickListener {
            if (text.text.isNullOrEmpty()) {
                text.state = AndesTextfieldState.ERROR
                text.helper = "Campo obligatorio"
                return@setOnClickListener
            } else {
                text.state = AndesTextfieldState.IDLE
            }
            val size = when (sizeSpinner.selectedItem) {
                "Large" -> AndesButtonSize.LARGE
                "Medium" -> AndesButtonSize.MEDIUM
                "Small" -> AndesButtonSize.SMALL
                else -> AndesButtonSize.LARGE
            }
            val hierarchy = when (hierarchySpinner.selectedItem) {
                "Loud" -> AndesButtonHierarchy.LOUD
                "Quiet" -> AndesButtonHierarchy.QUIET
                "Transparent" -> AndesButtonHierarchy.TRANSPARENT
                else -> AndesButtonHierarchy.LOUD
            }

            andesButton.text = text.text
            andesButton.size = size
            andesButton.hierarchy = hierarchy
            andesButton.isEnabled = enabledCheckbox.status == AndesCheckboxStatus.SELECTED
        }
    }

    private fun addStaticPage(container: View) {
        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.let {
            container.findViewById<AndesButton>(R.id.button_loud_with_drawable).setIconDrawable(
                    it, AndesButtonIconOrientation.LEFT
            )
        }

        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.let {
            container.findViewById<AndesButton>(R.id.button_quiet_with_drawable).setIconDrawable(
                    it, AndesButtonIconOrientation.RIGHT
            )
        }

        ResourcesCompat.getDrawable(resources, R.drawable.andesui_icon_dynamic, null)?.let {
            container.findViewById<AndesButton>(R.id.button_transparent_with_drawable).setIconDrawable(
                    it, AndesButtonIconOrientation.LEFT
            )
        }

        bindAndesSpecsButton(container)
    }

    fun buttonClicked(v: View) {
        if (v is AndesButton) {
            if (v.id == button_loud_with_loading.id ||
                    v.id == button_quiet_with_loading.id ||
                    v.id == button_transparent_with_loading.id) {
                v.isLoading = !v.isLoading
            }
            Toast.makeText(this, "${v.text} clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BUTTON)
        }
    }
}
