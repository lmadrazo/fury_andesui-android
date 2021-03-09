package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.card.AndesCard
import com.mercadolibre.android.andesui.card.bodyPadding.AndesCardBodyPadding
import com.mercadolibre.android.andesui.card.hierarchy.AndesCardHierarchy
import com.mercadolibre.android.andesui.card.padding.AndesCardPadding
import com.mercadolibre.android.andesui.card.style.AndesCardStyle
import com.mercadolibre.android.andesui.card.type.AndesCardType
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.demoapp.feature.specs.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.feature.specs.launchSpecs
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.textfield.AndesTextfield

class CardShowcaseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_card)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.firebase_analytics_card))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)
        val staticCardLayout = addStaticCard(inflater)
        val dynamicCardLayout = addDynamicCard(inflater)
        return listOf(dynamicCardLayout, staticCardLayout)
    }

    @Suppress("ComplexMethod", "LongMethod")
    private fun addDynamicCard(inflater: LayoutInflater): View {
        val layoutCard = inflater.inflate(
                R.layout.andesui_dynamic_card_showcase,
                null,
                false
        ) as ScrollView

        val title = "Andes card!  \uD83D\uDE04"
        val link = "View more"
        val message = "Cards are containers that allow you to group or structure the content on the screen. " +
                "They are used as a support for actions, text, images and other components throughout the entire UI."

        val andesCard: AndesCard = layoutCard.findViewById(R.id.andesCard)
        andesCard.setCardAction(
                View.OnClickListener {
                    Toast.makeText(this, "OnClicked card!", Toast.LENGTH_LONG).show()
                }
        )
        andesCard.title = title
        andesCard.setLinkAction(
                link,
                View.OnClickListener {
                    launchSpecs(it.context, AndesSpecs.CARD)
                }
        )
        andesCard.padding = AndesCardPadding.SMALL
        andesCard.type = AndesCardType.HIGHLIGHT

        // View card
        val textView = TextView(this)
        textView.text = message
        textView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.title_text_size_card)
        )
        textView.setTextColor(resources.getColor(R.color.andes_gray_800))
        andesCard.cardView = textView

        val andesCardTitle = layoutCard.findViewById<AndesTextfield>(R.id.andesTitle)
        andesCardTitle.text = title

        val andesCardLink = layoutCard.findViewById<AndesTextfield>(R.id.andesLink)
        andesCardLink.text = link

        val spinnerType: Spinner = layoutCard.findViewById(R.id.spinnerType)
        ArrayAdapter.createFromResource(
                this,
                R.array.card_type_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerType.adapter = adapter
                }
        spinnerType.setSelection(1)

        val spinnerStyle: Spinner = layoutCard.findViewById(R.id.spinnerStyle)
        ArrayAdapter.createFromResource(
                this,
                R.array.card_style_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerStyle.adapter = adapter
                }

        val spinnerPadding: Spinner = layoutCard.findViewById(R.id.spinnerPadding)
        ArrayAdapter.createFromResource(
                this,
                R.array.card_padding_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerPadding.adapter = adapter
                }
        spinnerPadding.setSelection(1)

        val spinnerBodyPadding: Spinner = layoutCard.findViewById(R.id.spinnerBodyPadding)
        ArrayAdapter.createFromResource(
                this,
                R.array.card_body_padding_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerBodyPadding.adapter = adapter
                }
        spinnerBodyPadding.setSelection(1)

        val spinnerHierarchy: Spinner = layoutCard.findViewById(R.id.spinnerHierarchy)
        ArrayAdapter.createFromResource(
                this,
                R.array.card_hierarchy_spinner,
                android.R.layout.simple_spinner_item
        )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerHierarchy.adapter = adapter
                }

        layoutCard.findViewById<AndesButton>(R.id.buttonUpdate).setOnClickListener {
            val type = when (spinnerType.selectedItem) {
                "None" -> AndesCardType.NONE
                "Highlight" -> AndesCardType.HIGHLIGHT
                "Error" -> AndesCardType.ERROR
                "Success" -> AndesCardType.SUCCESS
                "Warning" -> AndesCardType.WARNING
                else -> AndesCardType.NONE
            }
            val style = when (spinnerStyle.selectedItem) {
                "Elevated" -> AndesCardStyle.ELEVATED
                "Outline" -> AndesCardStyle.OUTLINE
                else -> AndesCardStyle.ELEVATED
            }
            val padding = when (spinnerPadding.selectedItem) {
                "None" -> AndesCardPadding.NONE
                "Small" -> AndesCardPadding.SMALL
                "Medium" -> AndesCardPadding.MEDIUM
                "Large" -> AndesCardPadding.LARGE
                "XLarge" -> AndesCardPadding.XLARGE
                else -> AndesCardPadding.NONE
            }
            val bodyPadding = when (spinnerBodyPadding.selectedItem) {
                "None" -> AndesCardBodyPadding.NONE
                "Small" -> AndesCardBodyPadding.SMALL
                "Medium" -> AndesCardBodyPadding.MEDIUM
                "Large" -> AndesCardBodyPadding.LARGE
                "XLarge" -> AndesCardBodyPadding.XLARGE
                else -> AndesCardBodyPadding.NONE
            }
            val hierarchy = when (spinnerHierarchy.selectedItem) {
                "Primary" -> AndesCardHierarchy.PRIMARY
                "Secondary" -> AndesCardHierarchy.SECONDARY
                "Secondary dark" -> AndesCardHierarchy.SECONDARY_DARK
                else -> AndesCardHierarchy.PRIMARY
            }

            andesCard.type = type
            andesCard.style = style
            andesCard.padding = padding
            andesCard.bodyPadding = bodyPadding
            andesCard.hierarchy = hierarchy
            andesCard.title = andesCardTitle.text
            if (!andesCardLink.text.isNullOrEmpty()) {
                andesCard.setLinkAction(
                        andesCardLink.text!!,
                        View.OnClickListener {
                            Toast.makeText(this, "OnClicked action!", Toast.LENGTH_LONG).show()
                        }
                )
            } else {
                andesCard.removeLinkAction()
            }
        }

        layoutCard.findViewById<AndesButton>(R.id.buttonClear).setOnClickListener {
            andesCardTitle.text = title
            andesCardLink.text = link
            andesCard.cardView = textView
            andesCard.title = title
            andesCard.padding = AndesCardPadding.SMALL
            andesCard.bodyPadding = AndesCardBodyPadding.SMALL
            andesCard.type = AndesCardType.HIGHLIGHT
            andesCard.style = AndesCardStyle.ELEVATED
            andesCard.setCardAction(
                    View.OnClickListener {
                        Toast.makeText(this, "OnClicked card!", Toast.LENGTH_LONG).show()
                    }
            )
            andesCard.setLinkAction(
                    link,
                    View.OnClickListener {
                        Toast.makeText(this, "OnClicked action card!", Toast.LENGTH_SHORT).show()
                    }
            )
            spinnerType.setSelection(1)
            spinnerStyle.setSelection(0)
            spinnerPadding.setSelection(1)
            spinnerBodyPadding.setSelection(1)
            spinnerHierarchy.setSelection(0)
        }

        return layoutCard
    }

    private fun addStaticCard(inflater: LayoutInflater): View {
        val layoutCard = inflater.inflate(
                R.layout.andesui_card_showcase,
                null,
                false
        ) as ScrollView

        val card1 = layoutCard.findViewById<AndesCard>(R.id.card_example_1)
        card1.setLinkAction(
                "Action",
                View.OnClickListener {
                    Toast.makeText(this, "Action clicked!", Toast.LENGTH_SHORT).show()
                }
        )
        val textView1 = TextView(this)
        textView1.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.title_text_size_card)
        )
        textView1.setTextColor(resources.getColor(R.color.andes_gray_800))
        textView1.text = resources.getString(R.string.andesui_demoapp_card_example_1)
        card1.cardView = textView1

        val card2 = layoutCard.findViewById<AndesCard>(R.id.card_example_2)
        val textView2 = TextView(this)
        textView2.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.title_text_size_card)
        )
        textView2.setTextColor(resources.getColor(R.color.andes_gray_800))
        textView2.text = resources.getString(R.string.andesui_demoapp_card_example_2)
        card2.cardView = textView2

        val card3 = layoutCard.findViewById<AndesCard>(R.id.card_example_3)
        card3.setLinkAction(
                "Action",
                View.OnClickListener {
                    Toast.makeText(this, "Action clicked!", Toast.LENGTH_SHORT).show()
                }
        )
        val textView3 = TextView(this)
        textView3.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.title_text_size_card)
        )
        textView3.setTextColor(resources.getColor(R.color.andes_gray_800))
        textView3.text = resources.getString(R.string.andesui_demoapp_card_example_3)
        card3.cardView = textView3

        val card4 = layoutCard.findViewById<AndesCard>(R.id.card_example_4)
        val textView4 = TextView(this)
        textView4.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.title_text_size_card)
        )
        textView4.setTextColor(resources.getColor(R.color.andes_gray_800))
        textView4.text = resources.getString(R.string.andesui_demoapp_card_example_4)
        card4.cardView = textView4

        val card5 = layoutCard.findViewById<AndesCard>(R.id.card_example_5)
        val textView5 = TextView(this)
        textView5.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.title_text_size_card)
        )
        textView5.setTextColor(resources.getColor(R.color.andes_gray_800))
        textView5.text = resources.getString(R.string.andesui_demoapp_card_example_5)
        card5.cardView = textView5

        layoutCard.findViewById<AndesButton>(R.id.andesui_demoapp_andes_checkbox_specs_button).setOnClickListener {
            launchSpecs(it.context, AndesSpecs.CHECKBOX)
        }

        return layoutCard
    }
}
