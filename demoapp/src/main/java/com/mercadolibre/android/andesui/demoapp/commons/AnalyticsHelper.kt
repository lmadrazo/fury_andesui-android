package com.mercadolibre.android.andesui.demoapp.commons

internal class AnalyticsHelper {

    companion object {
        const val whatsNewTrack = "WhatsNew"
        const val specsTrack = "Contribute"
        const val contributeTrack = "Specs"
    }

    private val components = mapOf(
            "BadgeShowcaseActivity" to "AndesBadge",
            "BottomSheetShowcaseActivity" to "AndesBottomSheet",
            "ButtonShowcaseActivity" to "AndesButton",
            "CardShowcaseActivity" to "AndesCard",
            "CarouselShowcaseActivity" to "AndesCarousel",
            "CheckboxShowcaseActivity" to "AndesCheckbox",
            "CoachmarkShowcaseActivity" to "AndesCoachmark",
            "DatePickerShowcaseActivity" to "AndesDatePicker",
            "DropdownShowcaseActivity" to "AndesDropdown",
            "ListShowcaseActivity" to "AndesList",
            "MessageShowcaseActivity" to "AndesMessage",
            "ProgressShowcaseActivity" to "AndesProgressIndicator",
            "RadioButtonShowcaseActivity" to "AndesRadioButton",
            "SnackbarShowcaseActivity" to "AndesSnackbar",
            "TagShowcaseActivity" to "AndesTag",
            "TextfieldShowcaseActivity" to "AndesTextfield",
            "ThumbnailShowcaseActivity" to "AndesThumbnail",
            "TooltipShowcaseActivity" to "AndesTooltip"
    )

    private val screens = mapOf(
            "BadgeShowcaseActivity" to arrayListOf("/badge/dynamic", "/badge/static"),
            "BottomSheetShowcaseActivity" to arrayListOf("/bottomsheet/dynamic"),
            "ButtonShowcaseActivity" to arrayListOf("/button/dynamic", "/button/static"),
            "CardShowcaseActivity" to arrayListOf("/card/dynamic", "/card/static"),
            "CarouselShowcaseActivity" to arrayListOf("/carousel/dynamic", "/carousel/static"),
            "CheckboxShowcaseActivity" to arrayListOf("/checkbox/dynamic", "/checkbox/static"),
            "CoachmarkShowcaseActivity" to arrayListOf("/coachmark/static"),
            "DatePickerShowcaseActivity" to arrayListOf("/datepicker/static"),
            "DropdownShowcaseActivity" to arrayListOf("/dropdown/dynamic", "/dropdown/static"),
            "ListShowcaseActivity" to arrayListOf("/list/dynamic", "/list/static"),
            "MessageShowcaseActivity" to arrayListOf("/message/dynamic", "/message/static"),
            "ProgressShowcaseActivity" to arrayListOf("/progressindicator/static"),
            "RadioButtonShowcaseActivity" to arrayListOf("/radiobutton/radiobutton/dynamic",
                    "/radiobutton/radiobutton/static", "/radiobutton/radiobuttongroup/static"),
            "SnackbarShowcaseActivity" to arrayListOf("/snackbar/dynamic"),
            "TagShowcaseActivity" to arrayListOf("/tag/simple/dynamic", "/tag/simple/static", "/tag/choice/static"),
            "TextfieldShowcaseActivity" to arrayListOf("/textfield/textfield/dynamic", "/textfield/textarea/dynamic",
                    "/textfield/textcode/dynamic", "/textfield/static"),
            "ThumbnailShowcaseActivity" to arrayListOf("/thumbnail/dynamic", "/thumbnail/static"),
            "TooltipShowcaseActivity" to arrayListOf("/tooltip/dynamic", "/tooltip/static")
    )

    fun getPath(className: String, position: Int): String? {
        return screens[className]?.getOrNull(position)
    }

    fun getComponentName(className: String): String? {
        return components[className]
    }
}
