package com.mercadolibre.android.andesui.tag.factory

import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.tag.choice.AndesTagChoiceStateInterface
import com.mercadolibre.android.andesui.tag.leftcontent.AndesIconSizeInterface
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent

internal data class AndesTagChoiceConfiguration(
    val text: String? = null,
    val backgroundColor: AndesColor,
    val borderColor: AndesColor,
    val textColor: AndesColor,
    val rightContentColor: AndesColor,
    val leftContentColor: AndesColor,
    val leftContentData: LeftContent? = null,
    val leftContentWidth: Int?,
    val leftContentHeight: Int?,
    val leftContent: AndesTagLeftContent = AndesTagLeftContent.NONE,
    val rightContent: AndesTagRightContent = AndesTagRightContent.NONE,
    val shouldAnimateTag: Boolean
)
internal object AndesChoiceTagConfigurationFactory {

    fun create(andesTagChoiceAttrs: AndesTagChoiceAttrs): AndesTagChoiceConfiguration {
        return with(andesTagChoiceAttrs) {
            AndesTagChoiceConfiguration(
                    text = andesSimpleTagText,
                    backgroundColor = resolveBackgroundColor(andesTagChoiceState.state),
                    borderColor = resolveBorderColor(andesTagChoiceState.state),
                    textColor = resolveTextColor(andesTagChoiceState.state),
                    rightContentColor = resolveRightContentColor(andesTagChoiceState.state),
                    leftContentColor = resolveLeftContentColor(andesTagChoiceState.state),
                    leftContentData = andesTagChoiceAttrs.leftContentData,
                    leftContentWidth = resolveLeftContentWidth(leftContentData?.icon?.iconSize?.size),
                    leftContentHeight = resolveLeftContentHeight(leftContentData?.icon?.iconSize?.size),
                    leftContent = andesTagChoiceAttrs.leftContent ?: AndesTagLeftContent.NONE,
                    rightContent = andesTagChoiceAttrs.rightContent ?: AndesTagRightContent.NONE,
                    shouldAnimateTag = andesTagChoiceAttrs.shouldAnimateTag
            )
        }
    }

    private fun resolveLeftContentHeight(iconSize: AndesIconSizeInterface?): Int? {
        if (iconSize != null) {
            return iconSize.getHeight()
        }

        return null
    }
    private fun resolveLeftContentWidth(iconSize: AndesIconSizeInterface?): Int? {
        if (iconSize != null) {
            return iconSize.getWidth()
        }

        return null
    }
    private fun resolveBackgroundColor(state: AndesTagChoiceStateInterface) = state.backgroundColor()
    private fun resolveBorderColor(state: AndesTagChoiceStateInterface) = state.borderColor()
    private fun resolveTextColor(state: AndesTagChoiceStateInterface) = state.textColor()
    private fun resolveRightContentColor(state: AndesTagChoiceStateInterface) = state.rightContentColor()
    private fun resolveLeftContentColor(state: AndesTagChoiceStateInterface) = state.leftContentColor()
}
