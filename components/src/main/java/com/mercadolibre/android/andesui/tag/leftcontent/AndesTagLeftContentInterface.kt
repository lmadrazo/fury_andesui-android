package com.mercadolibre.android.andesui.tag.leftcontent

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.utils.buildCircleBitmap
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable
import com.mercadolibre.android.andesui.utils.validateColor


internal sealed class AndesTagLeftContentInterface {
    abstract fun leftMargin(context: Context): Int
    abstract fun rightMargin(context: Context): Int
    abstract fun leftMarginText(context: Context, size: AndesTagSize): Int
    abstract fun size(context: Context): Int
    abstract fun border(context: Context): Float
    abstract fun view(context: Context, leftContent: LeftContent): View
}

internal object AndesTagLeftContentNone : AndesTagLeftContentInterface() {
    private const val ANDES_TAG_MARGIN = 0
    private const val ANDES_TAG_SIZE = 0
    private const val ANDES_TAG_BORDER = 0f

    override fun leftMargin(context: Context) = ANDES_TAG_MARGIN
    override fun rightMargin(context: Context) = ANDES_TAG_MARGIN
    override fun leftMarginText(context: Context, size: AndesTagSize) : Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_large_margin).toInt()
        }
    }
    override fun size(context: Context) = ANDES_TAG_SIZE
    override fun border(context: Context) = ANDES_TAG_BORDER
    override fun view(context: Context, leftContent: LeftContent) = View(context)
}

internal object AndesTagLeftContentDot : AndesTagLeftContentInterface() {
    override fun leftMargin(context: Context) = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
    override fun rightMargin(context: Context) = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    override fun leftMarginText(context: Context, size: AndesTagSize) = 0
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
    override fun border(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, leftContent: LeftContent): View {
        // Valid colors
        var isValid = validateColor(leftContent.dot!!.backgroundColor)
        if (isValid && !leftContent.dot!!.textColor.isNullOrEmpty()) {
            isValid = isValid && validateColor(leftContent.dot!!.textColor!!)
        }
        if (!isValid) {
            throw IllegalStateException("Invalid color, it is necessary to pass it in hexadecimal.")
        }
        // Valid text
        if (!leftContent.dot!!.text.isNullOrEmpty() && leftContent.dot!!.text!!.length > 2) {
            leftContent.dot!!.text = leftContent.dot!!.text!!.substring(0, 2)
        }
        // Create view
        if (leftContent.dot!!.text.isNullOrEmpty()) {
            val view = View(context)
            val shape = GradientDrawable()
            shape.cornerRadius = border(context)
            shape.setColor(Color.parseColor(leftContent.dot!!.backgroundColor))
            view.background = shape
            return view
        } else {
            val textView = TextView(context)
            textView.text = leftContent.dot!!.text
            textView.gravity = Gravity.CENTER
            val shape = GradientDrawable()
            shape.cornerRadius = border(context)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.andes_tag_small_text_size))
            if (!leftContent.dot!!.textColor.isNullOrEmpty()) {
                textView.setTextColor(Color.parseColor(leftContent.dot!!.textColor))
            }
            shape.setColor(Color.parseColor(leftContent.dot!!.backgroundColor))
            textView.background = shape
            return textView
        }
    }
}

internal object AndesTagLeftContentIcon : AndesTagLeftContentInterface() {
    var leftMargin = 0
    override fun leftMargin(context: Context) = leftMargin
    override fun rightMargin(context: Context) = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    override fun leftMarginText(context: Context, size: AndesTagSize) = 0
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
    override fun border(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, leftContent: LeftContent): View {
        // Valid color
        if (!leftContent.icon!!.iconColor.isNullOrEmpty() && !validateColor(leftContent.icon!!.iconColor!!)) {
            throw IllegalStateException("Invalid color, it is necessary to pass it in hexadecimal.")
        }
        // Valid if i have path or drawable
        if (leftContent.icon!!.path.isNullOrEmpty() && leftContent.icon!!.icon == null) {
            throw IllegalStateException("A drawable or IconProvider path is required.")
        }

        val imageView = ImageView(context)
        val background = leftContent.icon?.backgroundColor
        if (background != null) {
            val shape = GradientDrawable()
            shape.cornerRadius = border(context)
            shape.setColor(Color.parseColor(background))
            imageView.background = shape
            leftMargin = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
        } else {
            leftMargin = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }

        val bitmapDrawable = if (!leftContent.icon!!.path.isNullOrEmpty()) {
            IconProvider(context).loadIcon(leftContent.icon!!.path!!) as BitmapDrawable
        } else {
            if (background != null) {
                val padding = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
                imageView.setPadding(padding, padding, padding, padding)
            }
            leftContent.icon!!.icon as BitmapDrawable
        }

        val color: Int?
        val leftIcon = leftContent.icon!!
        color = when {
            leftIcon.iconColor != null -> {
                Color.parseColor(leftIcon.iconColor)
            }
            leftIcon.iconDefaultColor != null -> {
                leftIcon.iconDefaultColor?.colorInt(context)
            }
            else -> {
                Color.parseColor("#000000")
            }
        }

        val size = when(leftIcon.iconSize) {
            IconSize.LARGE -> context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
            IconSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_icon_size_small).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
        }
        
        val icon = buildColoredBitmapDrawable(
                bitmapDrawable,
                context,
                dstWidth = size,
                dstHeight = size,
                color = color
        )

        imageView.setImageDrawable(icon)
        return imageView
    }
}

internal object AndesTagLeftContentImage : AndesTagLeftContentInterface() {
    override fun leftMargin(context: Context) = context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
    override fun rightMargin(context: Context) = context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
    override fun leftMarginText(context: Context, size: AndesTagSize) = 0
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
    override fun border(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, leftContent: LeftContent): View {
        // Create avatar
        val icon = buildCircleBitmap(leftContent.image!!.avatar, size(context), size(context))
        if (icon != null) {
            leftContent.image!!.avatar = icon
        }
        val imageView = ImageView(context)
        imageView.setImageBitmap(leftContent.image!!.avatar)
        return imageView
    }
}
