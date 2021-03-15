package com.mercadolibre.android.andesui.demoapp.commons

class AnalyticsHelper {

    companion object {

        private val components = mapOf(
                "BadgeShowcaseActivity" to "AndesBadge",
                "ButtonShowcaseActivity" to "AndesButton",
                "CardShowcaseActivity" to "AndesCard"
        )

        private val screens = mapOf(
                "BadgeShowcaseActivity" to arrayListOf("/dynamic", "/static"),
                "ButtonShowcaseActivity" to arrayListOf("/button/dynamic", "/button/static"),
                "CardShowcaseActivity" to arrayListOf("/dynamic", "/static")
        )

        fun getPath(className: String, position: Int): String? {
            var path: String? = null
            if (screens.containsKey(className) && position < screens.getValue(className).size) {
                path = screens.getValue(className)[position]
            }
            return path
        }

        fun getComponentName(className: String): String? {
            var componentName: String? = null
            if (screens.containsKey(className)) {
                componentName = components[className]
            }
            return componentName
        }
    }
}