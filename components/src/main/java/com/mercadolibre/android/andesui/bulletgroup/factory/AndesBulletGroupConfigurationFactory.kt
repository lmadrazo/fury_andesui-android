package com.mercadolibre.android.andesui.bulletgroup.factory

import com.mercadolibre.android.andesui.bulletgroup.BulletItem

internal data class AndesBulletGroupConfiguration(
    val bullets: ArrayList<BulletItem>
)

internal object AndesBulletGroupConfigurationFactory {
    fun create(andesBulletGroupAttrs: AndesBulletGroupAttrs): AndesBulletGroupConfiguration {
        return with(andesBulletGroupAttrs) {
            AndesBulletGroupConfiguration(
                bullets = andesBulletGroupAttrs.andesBulletGroupBullets
            )
        }
    }
}
