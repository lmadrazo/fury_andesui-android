package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.mercadolibre.android.andesui.badge.AndesBadgeDot
import com.mercadolibre.android.andesui.badge.AndesBadgePill
import com.mercadolibre.android.andesui.badge.border.AndesBadgePillBorder
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgePillHierarchy
import com.mercadolibre.android.andesui.badge.size.AndesBadgePillSize
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.feature.specs.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.feature.specs.launchSpecs
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState
import kotlinx.android.synthetic.main.andesui_badges_showcase_change.*

class BadgeShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_badge)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        addDynamicBadges(adapter.views[0])
        addStaticBadges(adapter.views[1])
    }

    @Suppress("ComplexMethod", "LongMethod")
    private fun addDynamicBadges(container: View) {
        val modifierSpinner: Spinner = container.findViewById(R.id.modifier_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.modifier_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modifierSpinner.adapter = adapter
        }

        val hierarchySpinner: Spinner = container.findViewById(R.id.hierarchy_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.hierarchy_spinner,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                hierarchySpinner.adapter = adapter
            }

        val typeSpinner: Spinner = container.findViewById(R.id.simple_type_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.simple_type_spinner,
            android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    typeSpinner.adapter = adapter
                }

        val sizeSpinner: Spinner = container.findViewById(R.id.size_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.size_spinner,
            android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sizeSpinner.adapter = adapter
                }

        val borderSpinner: Spinner = container.findViewById(R.id.border_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.border_spinner,
            android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    borderSpinner.adapter = adapter
                }

        val textField: AndesTextfield = container.findViewById(R.id.label_text)
        val clearButton: AndesButton = container.findViewById(R.id.clear_button)
        val changeButton: AndesButton = container.findViewById(R.id.change_button)

        val andesBadgePill: AndesBadgePill = container.findViewById(R.id.andes_badge_pill)
        val andesBadgeDot: AndesBadgeDot = container.findViewById(R.id.andes_badge_dot)

        modifierSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                when (modifierSpinner.getItemAtPosition(position)) {
                    "Pill" -> {
                        group.visibility = View.VISIBLE
                        andesBadgePill.visibility = View.VISIBLE
                        andesBadgeDot.visibility = View.INVISIBLE
                    }
                    "Dot" -> {
                        group.visibility = View.GONE
                        andesBadgePill.visibility = View.INVISIBLE
                        andesBadgeDot.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing.
            }
        }

        clearButton.setOnClickListener {
            group.visibility = View.VISIBLE
            andesBadgePill.visibility = View.VISIBLE
            andesBadgeDot.visibility = View.INVISIBLE

            modifierSpinner.setSelection(0)
            hierarchySpinner.setSelection(0)
            typeSpinner.setSelection(0)
            sizeSpinner.setSelection(0)
            borderSpinner.setSelection(0)

            textField.text = ""
            andesBadgePill.text = getString(R.string.andes_badges_text)
            andesBadgePill.type = AndesBadgeType.NEUTRAL
            andesBadgePill.pillHierarchy = AndesBadgePillHierarchy.LOUD
            andesBadgePill.pillSize = AndesBadgePillSize.LARGE
            andesBadgePill.pillBorder = AndesBadgePillBorder.STANDARD
        }

        changeButton.setOnClickListener {
            when (modifierSpinner.getItemAtPosition(modifierSpinner.selectedItemPosition)) {
                "Pill" -> {
                    if (textField.text.isNullOrEmpty()) {
                        textField.state = AndesTextfieldState.ERROR
                        textField.helper = "Campo obligatorio"
                        return@setOnClickListener
                    } else {
                        textField.state = AndesTextfieldState.IDLE
                        textField.helper = null
                    }

                    val type = when (typeSpinner.getItemAtPosition(typeSpinner.selectedItemPosition)) {
                        "Neutral" -> AndesBadgeType.NEUTRAL
                        "Highlight" -> AndesBadgeType.HIGHLIGHT
                        "Success" -> AndesBadgeType.SUCCESS
                        "Warning" -> AndesBadgeType.WARNING
                        "Error" -> AndesBadgeType.ERROR
                        else -> AndesBadgeType.NEUTRAL
                    }

                    val hierarchy = when (hierarchySpinner.getItemAtPosition(hierarchySpinner.selectedItemPosition)) {
                        "Loud" -> AndesBadgePillHierarchy.LOUD
                        "Quiet" -> AndesBadgePillHierarchy.QUIET
                        else -> AndesBadgePillHierarchy.LOUD
                    }

                    val size = when (sizeSpinner.getItemAtPosition(sizeSpinner.selectedItemPosition)) {
                        "Small" -> AndesBadgePillSize.SMALL
                        "Large" -> AndesBadgePillSize.LARGE
                        else -> AndesBadgePillSize.LARGE
                    }

                    val border = when (borderSpinner.getItemAtPosition(borderSpinner.selectedItemPosition)) {
                        "Standard" -> AndesBadgePillBorder.STANDARD
                        "Corner" -> AndesBadgePillBorder.CORNER
                        "Rounded" -> AndesBadgePillBorder.ROUNDED
                        else -> AndesBadgePillBorder.STANDARD
                    }

                    andesBadgePill.type = type
                    andesBadgePill.pillHierarchy = hierarchy
                    andesBadgePill.pillSize = size
                    andesBadgePill.pillBorder = border
                    andesBadgePill.text = textField.text
                }
                "Dot" -> {
                    val type = when (typeSpinner.getItemAtPosition(typeSpinner.selectedItemPosition)) {
                        "Neutral" -> AndesBadgeType.NEUTRAL
                        "Highlight" -> AndesBadgeType.HIGHLIGHT
                        "Success" -> AndesBadgeType.SUCCESS
                        "Warning" -> AndesBadgeType.WARNING
                        "Error" -> AndesBadgeType.ERROR
                        else -> AndesBadgeType.NEUTRAL
                    }
                    andesBadgeDot.type = type
                }
            }
        }
    }

    private fun addStaticBadges(container: View) {
        bindAndesSpecsButton(container)
    }

    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_badge_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BADGE)
        }
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View?)
        }

        override fun isViewFromObject(view: View, other: Any): Boolean {
            return view == other
        }

        override fun getCount(): Int = views.size

        private fun initViews(): List<View> {
            val inflater = LayoutInflater.from(context)
            val layoutDynamic = inflater.inflate(R.layout.andesui_badges_showcase_change, null, false)
            val layoutStatic = inflater.inflate(R.layout.andesui_badges_showcase, null, false)
            return listOf<View>(layoutDynamic, layoutStatic)
        }
    }
}
