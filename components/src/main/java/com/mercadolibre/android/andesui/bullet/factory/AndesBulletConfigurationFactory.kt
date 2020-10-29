package com.mercadolibre.android.andesui.bullet.factory

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.message.type.AndesMessageTypeInterface

internal data class AndesBulletConfiguration(
    val textBody: String? = null,
    val textSize: Float,
    val bodyLinkIsUnderline: Boolean,
    val bodyLinkTextColor: AndesColor
)

internal object AndesBulletConfigurationFactory {

    fun create(context: Context, andesBulletAttrs: AndesBulletAttrs): AndesBulletConfiguration {
        return with(andesBulletAttrs) {
            AndesBulletConfiguration(
                textBody = andesBulletText,
                textSize = resolveBodySize(context),
                bodyLinkIsUnderline = resolveBodyLinkIsUnderline(andesMessageHierarchy.hierarchy, andesMessageType.type),
                bodyLinkTextColor = resolveBodyLinkTextColor(andesMessageHierarchy.hierarchy, andesMessageType.type)
            )
        }
    }

    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andes_message_body)

    private fun resolveBodyLinkIsUnderline(
        hierarchy: AndesMessageHierarchyInterface,
        type: AndesMessageTypeInterface
    ) = hierarchy.bodyLinkIsUnderLine(type)

    private fun resolveBodyLinkTextColor(
        hierarchy: AndesMessageHierarchyInterface,
        type: AndesMessageTypeInterface
    ) = hierarchy.bodyLinkTextColor(type)
}
