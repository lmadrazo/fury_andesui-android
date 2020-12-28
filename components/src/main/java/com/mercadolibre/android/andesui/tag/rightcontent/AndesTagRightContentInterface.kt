package com.mercadolibre.android.andesui.tag.rightcontent

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.icons.IconProvider
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.utils.buildColoredAndesBitmapDrawable

internal sealed class AndesTagRightContentInterface {
    abstract fun leftMargin(context: Context, size: AndesTagSize): Int
    abstract fun rightMargin(context: Context, size: AndesTagSize): Int
    abstract fun rightMarginText(context: Context, size: AndesTagSize): Int
    abstract fun size(context: Context): Int
    abstract fun border(context: Context): Float
    abstract fun view(context: Context, color: AndesColor, rightContent: RightContent?, callback: View.OnClickListener?): View
}

internal object AndesTagRightContentNone : AndesTagRightContentInterface() {
    private const val ANDES_TAG_MARGIN = 0
    private const val ANDES_TAG_SIZE = 0
    private const val ANDES_TAG_BORDER = 0f

    override fun leftMargin(context: Context, size: AndesTagSize) = ANDES_TAG_MARGIN
    override fun rightMargin(context: Context, size: AndesTagSize) = ANDES_TAG_MARGIN
    override fun rightMarginText(context: Context, size: AndesTagSize) : Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_large_margin).toInt()
        }
    }
    override fun size(context: Context) = ANDES_TAG_SIZE
    override fun border(context: Context) = ANDES_TAG_BORDER
    override fun view(context: Context, color: AndesColor, rightContent: RightContent?, callback: View.OnClickListener?) = View(context)
}

internal object AndesTagRightContentDismiss : AndesTagRightContentInterface() {
    override fun leftMargin(context: Context, size: AndesTagSize): Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMargin(context: Context, size: AndesTagSize): Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMarginText(context: Context, size: AndesTagSize) = 0
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
    override fun border(context: Context) = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, color: AndesColor, rightContent: RightContent?, callback: View.OnClickListener?): View {

        val bitmapDrawable = buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_ui_close_16") as BitmapDrawable,
                context = context,
                color = color
        )
        val imageView = ImageView(context)
        imageView.setImageDrawable(bitmapDrawable)
        imageView.setOnClickListener {
            callback?.onClick(it)
        }
        return imageView
    }
}

internal object AndesTagRightContentDropDown : AndesTagRightContentInterface() {
    override fun leftMargin(context: Context, size: AndesTagSize): Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMargin(context: Context, size: AndesTagSize): Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMarginText(context: Context, size: AndesTagSize) = 0
    override fun size(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, color: AndesColor, rightContent: RightContent?, callback: View.OnClickListener?): View {
        val bitmapDrawable = buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_ui_chevron_down_16") as BitmapDrawable,
                context = context,
                color = color
        )
        val imageView = ImageView(context)
        imageView.setImageDrawable(bitmapDrawable)
        return imageView
    }
}

internal object AndesTagRightContentCheck : AndesTagRightContentInterface() {
    override fun leftMargin(context: Context, size: AndesTagSize): Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMargin(context: Context, size: AndesTagSize): Int {
        return when (size) {
            AndesTagSize.SMALL -> context.resources.getDimension(R.dimen.andes_tag_small_margin).toInt()
            else -> context.resources.getDimension(R.dimen.andes_tag_medium_margin).toInt()
        }
    }
    override fun rightMarginText(context: Context, size: AndesTagSize) = 0
    override fun size(context: Context): Int = context.resources.getDimension(R.dimen.andes_tag_icon_size_large).toInt()
    override fun border(context: Context): Float = context.resources.getDimension(R.dimen.andes_tag_icon_radius)
    override fun view(context: Context, color: AndesColor, rightContent: RightContent?, callback: View.OnClickListener?): View {
        val bitmapDrawable = buildColoredAndesBitmapDrawable(
                image = IconProvider(context).loadIcon("andes_ui_feedback_success_16") as BitmapDrawable,
                context = context,
                color = color
        )
        val imageView = ImageView(context)
        imageView.setImageDrawable(bitmapDrawable)
        return imageView
    }
}
