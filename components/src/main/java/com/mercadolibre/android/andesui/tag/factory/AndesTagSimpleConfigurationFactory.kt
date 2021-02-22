package com.mercadolibre.android.andesui.tag.factory

import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent
import com.mercadolibre.android.andesui.tag.type.AndesSimpleTagTypeInterface

internal data class AndesTagSimpleConfiguration(
    val text: String? = null,
    val backgroundColor: AndesColor,
    val borderColor: AndesColor,
    val textColor: AndesColor,
    val dismissColor: AndesColor,
    val leftContentData: LeftContent? = null,
    val leftContent: AndesTagLeftContent = AndesTagLeftContent.NONE,
    val rightContent: AndesTagRightContent = AndesTagRightContent.NONE,
    val rightContentData: RightContent? = null
)

internal object AndesSimpleTagConfigurationFactory {

    fun create(andesTagSimpleAttrs: AndesTagSimpleAttrs): AndesTagSimpleConfiguration {
        return with(andesTagSimpleAttrs) {
            AndesTagSimpleConfiguration(
                    text = andesSimpleTagText,
                    backgroundColor = resolveBackgroundColor(andesTagType.type),
                    borderColor = resolveBorderColor(andesTagType.type),
                    textColor = resolveTextColor(andesTagType.type),
                    dismissColor = resolveDismissColor(andesTagType.type),
                    leftContentData = andesTagSimpleAttrs.leftContentData,
                    leftContent = andesTagSimpleAttrs.leftContent ?: AndesTagLeftContent.NONE,
                    rightContent = andesTagSimpleAttrs.rightContent ?: AndesTagRightContent.NONE,
                    rightContentData = andesTagSimpleAttrs.rightContentData
            )
        }
    }

    private fun resolveBackgroundColor(type: AndesSimpleTagTypeInterface) = type.backgroundColor()
    private fun resolveBorderColor(type: AndesSimpleTagTypeInterface) = type.borderColor()
    private fun resolveTextColor(type: AndesSimpleTagTypeInterface) = type.textColor()
    private fun resolveDismissColor(type: AndesSimpleTagTypeInterface) = type.dismissColor()
}
