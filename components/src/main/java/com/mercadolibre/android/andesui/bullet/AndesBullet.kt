package com.mercadolibre.android.andesui.bullet

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.BulletSpan
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.bullet.factory.AndesBulletAttrParser
import com.mercadolibre.android.andesui.bullet.factory.AndesBulletAttrs
import com.mercadolibre.android.andesui.bullet.factory.AndesBulletConfiguration
import com.mercadolibre.android.andesui.bullet.factory.AndesBulletConfigurationFactory
import com.mercadolibre.android.andesui.message.bodylinks.AndesBodyLinks
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType

class AndesBullet : ConstraintLayout {

    /**
     * Getter and setter for [hierarchy].
     */
    var hierarchy: AndesMessageHierarchy
        get() = andesBulletAttrs.andesMessageHierarchy
        set(value) {
            andesBulletAttrs = andesBulletAttrs.copy(andesMessageHierarchy = value)
            setupComponents(createConfig())
        }

    /**
     * Getter and setter for [type].
     */
    var type: AndesMessageType
        get() = andesBulletAttrs.andesMessageType
        set(value) {
            andesBulletAttrs = andesBulletAttrs.copy(andesMessageType = value)
            setupComponents(createConfig())
        }

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesBulletAttrs.andesBulletText
        set(value) {
            andesBulletAttrs = andesBulletAttrs.copy(andesBulletText = value)
            setupTextComponent(createConfig())
        }

    /**
     * Getter and setter for [textLinks].
     */
    var textLinks: AndesBodyLinks?
        get() = andesBulletAttrs.textLinks
        set(value) {
            andesBulletAttrs = andesBulletAttrs.copy(textLinks = value)
            setupTextComponent(createConfig())
        }

    lateinit var textComponent: TextView
    private lateinit var andesBulletAttrs: AndesBulletAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context,
        hierarchy: AndesMessageHierarchy = HIERARCHY_DEFAULT,
        type: AndesMessageType = STATE_DEFAULT,
        text: String,
        textLinks: AndesBodyLinks? = null
    ) : super(context) {
        initAttrs(hierarchy, type, text, textLinks)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesBulletAttrs = AndesBulletAttrParser.parse(context, attrs)
        val config = AndesBulletConfigurationFactory.create(context, andesBulletAttrs)
        setupComponents(config)
    }

    @Suppress("LongParameterList")
    private fun initAttrs(
        hierarchy: AndesMessageHierarchy,
        type: AndesMessageType,
        text: String,
        textLinks: AndesBodyLinks?
    ) {
        andesBulletAttrs = AndesBulletAttrs(hierarchy, type, text, textLinks)
        val config = AndesBulletConfigurationFactory.create(context, andesBulletAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesBulletConfiguration) {
        initComponents()
        setupViewId()
        setupTextComponent(config)
    }

    /**
     * Creates all the views that are part of this bullet.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_bullet, this, true)
        textComponent = container.findViewById(R.id.andes_bullet_text)
    }

    /**
     * Sets a view id to this bullet.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the text component of this button.
     *
     */
    private fun setupTextComponent(config: AndesBulletConfiguration) {
        if (config.textBody.isNullOrEmpty()) {
            Log.e("AndesBullet", "Bullet cannot be visualized with null or empty body")
        } else {
            textComponent.text = getText(config.textBody, config)
        }
    }

    private fun getText(text: String, config: AndesBulletConfiguration): SpannableString {
        val spannableString = SpannableString(text)
        val bulletSpan = BulletSpan(
            8,
            ContextCompat.getColor(context, R.color.andes_gray_800))
        spannableString.setSpan(bulletSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textLinks?.let {
            it.links.forEachIndexed { linkIndex, andesBodyLink ->
                if (andesBodyLink.isValidRange(spannableString)) {
                    val clickableSpan = object : ClickableSpan() {
                        override fun onClick(view: View) {
                            it.listener(linkIndex)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.isUnderlineText = config.bodyLinkIsUnderline
                            ds.color = config.bodyLinkTextColor.colorInt(context)
                        }
                    }
                    spannableString.setSpan(clickableSpan,
                        andesBodyLink.startIndex, andesBodyLink.endIndex,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                } else {
                    Log.d("AndesBullet", "Text link range incorrect: " +
                        "${andesBodyLink.startIndex}, ${andesBodyLink.endIndex}")
                }
            }
            textComponent.movementMethod = LinkMovementMethod.getInstance()
        }
        return spannableString
    }

    private fun createConfig() = AndesBulletConfigurationFactory.create(context, andesBulletAttrs)

    companion object {
        private val HIERARCHY_DEFAULT = AndesMessageHierarchy.LOUD
        private val STATE_DEFAULT = AndesMessageType.NEUTRAL
    }
}
