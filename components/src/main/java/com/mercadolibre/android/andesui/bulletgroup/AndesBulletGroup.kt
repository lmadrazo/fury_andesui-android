package com.mercadolibre.android.andesui.bulletgroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.mercadolibre.android.andesui.bullet.AndesBullet
import com.mercadolibre.android.andesui.bulletgroup.factory.AndesBulletGroupAttrParser
import com.mercadolibre.android.andesui.bulletgroup.factory.AndesBulletGroupAttrs
import com.mercadolibre.android.andesui.bulletgroup.factory.AndesBulletGroupConfiguration
import com.mercadolibre.android.andesui.bulletgroup.factory.AndesBulletGroupConfigurationFactory
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.type.AndesMessageType
import com.mercadolibre.android.andesui.radiobutton.AndesRadioButton

class AndesBulletGroup : LinearLayout {

    /**
     * Getter and setter for [hierarchy].
     */
    var hierarchy: AndesMessageHierarchy
        get() = andesBulletGroupAttrs.andesMessageHierarchy
        set(value) {
            andesBulletGroupAttrs = andesBulletGroupAttrs.copy(andesMessageHierarchy = value)
            setupBullets()
        }

    /**
     * Getter and setter for [type].
     */
    var type: AndesMessageType
        get() = andesBulletGroupAttrs.andesMessageType
        set(value) {
            andesBulletGroupAttrs = andesBulletGroupAttrs.copy(andesMessageType = value)
            setupBullets()
        }

    /**
     * Getter and setter for [bullets].
     */
    var bullets: ArrayList<BulletItem>
        get() = andesBulletGroupAttrs.andesBulletGroupBullets
        set(value) {
            andesBulletGroupAttrs = andesBulletGroupAttrs.copy(andesBulletGroupBullets = value)
            setupBullets()
        }

    private lateinit var andesBulletGroupAttrs: AndesBulletGroupAttrs

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
        bullets: ArrayList<BulletItem>
    ) : super(context) {
        initAttrs(hierarchy, type, bullets)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesBulletGroupAttrs = AndesBulletGroupAttrParser.parse(context, attrs)
        val config = AndesBulletGroupConfigurationFactory.create(andesBulletGroupAttrs)
        setupComponents(config)
    }

    @Suppress("LongParameterList")
    private fun initAttrs(
        hierarchy: AndesMessageHierarchy,
        type: AndesMessageType,
        bullets: ArrayList<BulletItem>
    ) {
        andesBulletGroupAttrs = AndesBulletGroupAttrs(hierarchy, type, bullets)
        val config = AndesBulletGroupConfigurationFactory.create(andesBulletGroupAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesBulletGroupConfiguration) {
        initComponents()
        setupViewId()
        setupBullets()
    }

    /**
     * Creates all the views that are part of this bullet group.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        orientation = VERTICAL
    }

    /**
     * Sets a view id to this bullet group.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupBullets() {
        removeAllViews()
        this.bullets.forEachIndexed { _, item ->
            val andesBullet = AndesBullet(
                context,
                hierarchy,
                type,
                item.text,
                item.textLinks
            )
            addView(andesBullet)
        }
    }

    private fun createConfig() = AndesBulletGroupConfigurationFactory.create(andesBulletGroupAttrs)

    companion object {
        private val HIERARCHY_DEFAULT = AndesMessageHierarchy.LOUD
        private val STATE_DEFAULT = AndesMessageType.NEUTRAL
    }

}
