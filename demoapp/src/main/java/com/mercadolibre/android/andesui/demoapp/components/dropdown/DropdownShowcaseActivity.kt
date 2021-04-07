package com.mercadolibre.android.andesui.demoapp.components.dropdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.CustomViewPager
import com.mercadolibre.android.andesui.demoapp.utils.PageIndicator
import com.mercadolibre.android.andesui.dropdown.AndesDropDownForm
import com.mercadolibre.android.andesui.dropdown.AndesDropDownItem
import com.mercadolibre.android.andesui.dropdown.AndesDropdownStandalone
import com.mercadolibre.android.andesui.dropdown.size.AndesDropdownSize
import com.mercadolibre.android.andesui.dropdown.utils.AndesDropdownDelegate
import com.mercadolibre.android.andesui.list.utils.AndesListDelegate
import com.mercadolibre.android.andesui.textfield.AndesTextfield

@Suppress("TooManyFunctions")
class DropdownShowcaseActivity : AppCompatActivity(), AndesDropdownDelegate {

    private var andesDropDownLabel = "Title"
    private var andesDropDownPlaceHolder = "Place holder"
    private var andesDropDownHelper = "Helper"

    private lateinit var andesDropDownForm: AndesDropDownForm
    private lateinit var andesDropdownStandalone: AndesDropdownStandalone
    private lateinit var buttonClear: AndesButton
    private lateinit var buttonUpdate: AndesButton
    private lateinit var editTextTitle: AndesTextfield
    private lateinit var editTextPlaceHolder: AndesTextfield
    private lateinit var editTextHelper: AndesTextfield
    private lateinit var sizeSpinner: Spinner

    private lateinit var viewPager: CustomViewPager

    companion object {
        private const val DROP_DOWN_ITEMS_COUNT = 10
    }

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
        supportActionBar?.title = resources.getString(R.string.andes_demoapp_screen_dropdown)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewPager() {
        val inflater = LayoutInflater.from(this)
        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.adapter = AndesPagerAdapter(listOf<View>(
                inflater.inflate(R.layout.andesui_dynamic_dropdown, null, false),
                inflater.inflate(R.layout.andesui_static_dropdown, null, false)
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
        buttonClear = container.findViewById(R.id.buttonClear)
        buttonUpdate = container.findViewById(R.id.buttonUpdate)

        andesDropDownForm = container.findViewById(R.id.andesDropdown)

        andesDropDownForm.label = andesDropDownLabel
        andesDropDownForm.placeholder = andesDropDownPlaceHolder
        andesDropDownForm.helper = andesDropDownHelper

        andesDropDownForm.setItems(getFakeList())

        andesDropDownForm.delegate = this

        editTextTitle = container.findViewById(R.id.editTextDropdownLabel)
        editTextPlaceHolder = container.findViewById(R.id.editTextDropdownPlaceHolder)
        editTextHelper = container.findViewById(R.id.editTextDropdownHelper)

        buttonClear.setOnClickListener {
            clear()
        }

        buttonUpdate.setOnClickListener {
            update()
        }
    }

    private fun addStaticPage(container: View) {
        val spinnerType: Spinner = container.findViewById(R.id.andes_dropdown_standalone_show_case_spinner)
        ArrayAdapter.createFromResource(
                this,
                R.array.andes_type_list_spinner,
                android.R.layout.simple_spinner_item
        ).let { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerType.adapter = adapter
        }

        andesDropdownStandalone = container.findViewById(R.id.andesDropdownStandalone)

        andesDropdownStandalone.setItems(getFakeList())

        andesDropdownStandalone.delegate = this

        sizeSpinner = container.findViewById(R.id.andes_dropdown_standalone_show_case_spinner)
        sizeSpinner.setSelection(1) // medium value

        sizeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                andesDropdownStandalone.size = when (sizeSpinner.getItemAtPosition(position).toString().toLowerCase()) {
                    "small" -> {
                        AndesDropdownSize.SMALL
                    }
                    "medium" -> {
                        AndesDropdownSize.MEDIUM
                    }
                    "large" -> {
                        AndesDropdownSize.LARGE
                    }
                    else -> AndesDropdownSize.MEDIUM
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing.
            }
        }
    }

    override fun onItemSelected(andesDropDown: AndesListDelegate, position: Int) {
        Toast.makeText(this, "item selected position: $position", Toast.LENGTH_SHORT).show()
    }

    private fun clear() {
        andesDropDownLabel = "Titulo"
        andesDropDownPlaceHolder = "Place holder"
        andesDropDownHelper = "Helper"

        editTextTitle.text = andesDropDownLabel
        editTextPlaceHolder.text = andesDropDownPlaceHolder
        editTextHelper.text = andesDropDownHelper
    }

    private fun update() {
        andesDropDownForm.label = editTextTitle.text.toString()
        andesDropDownForm.placeholder = editTextPlaceHolder.text.toString()
        andesDropDownForm.helper = editTextHelper.text.toString()
    }

    private fun getFakeList(): List<AndesDropDownItem> {
        val listItems: MutableList<AndesDropDownItem> = mutableListOf()

        var item: AndesDropDownItem?
        for (i in 1..DROP_DOWN_ITEMS_COUNT) {
            item = AndesDropDownItem()
            item.title = "test$i"
            item.avatar = ContextCompat.getDrawable(this, R.drawable.andes_otros_almanaque_20)

            listItems.add(item)
        }

        return listItems
    }
}
