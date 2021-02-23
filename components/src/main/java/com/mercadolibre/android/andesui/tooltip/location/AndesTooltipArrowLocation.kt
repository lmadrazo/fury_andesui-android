package com.mercadolibre.android.andesui.tooltip.location

sealed class AndesTooltipArrowLocation{
    abstract fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float
    abstract fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float
}

object AndesTooltipArrowBottomLeft : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.arrowBorder.toFloat()
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.y + tooltip.radiusLayout.height - tooltip.elevation
    }

}
object AndesTooltipArrowBottomMiddle : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return ((tooltip.frameLayoutContainer.width / 2) - (tooltip.arrowWidth/2)).toFloat()
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.y + tooltip.radiusLayout.height - tooltip.elevation
    }

}
object AndesTooltipArrowBottomRight : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return (tooltip.frameLayoutContainer.width - tooltip.arrowBorder - tooltip.arrowWidth).toFloat()
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.y + tooltip.radiusLayout.height - tooltip.elevation
    }

}

object AndesTooltipArrowTopLeft : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.arrowBorder.toFloat()
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.radiusLayout.y - tooltip.arrowBorder
    }

}
object AndesTooltipArrowTopMiddle : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return ((tooltip.frameLayoutContainer.width / 2) - (tooltip.arrowWidth/2)).toFloat()
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.radiusLayout.y - tooltip.arrowBorder
    }

}
object AndesTooltipArrowTopRight : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return (tooltip.frameLayoutContainer.width - tooltip.arrowBorder - tooltip.arrowWidth).toFloat()
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.radiusLayout.y - tooltip.arrowBorder
    }

}

object AndesTooltipArrowRightTop : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.x + tooltip.radiusLayout.width - tooltip.elevation - tooltip.arrowImageInnerPadding
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.arrowBorder.toFloat()
    }

}
object AndesTooltipArrowRightMiddle : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.x + tooltip.radiusLayout.width - tooltip.elevation - tooltip.arrowImageInnerPadding
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return ((tooltip.frameLayoutContainer.height / 2) - (tooltip.arrowWidth/2)).toFloat()
    }

}
object AndesTooltipArrowRightBottom : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.x + tooltip.radiusLayout.width - tooltip.elevation - tooltip.arrowImageInnerPadding
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return (tooltip.frameLayoutContainer.height - tooltip.arrowBorder - tooltip.arrowWidth).toFloat()
    }

}

object AndesTooltipArrowLeftTop : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.x
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.arrowBorder.toFloat()
    }

}
object AndesTooltipArrowLeftMiddle : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.x
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return ((tooltip.frameLayoutContainer.height / 2) - (tooltip.arrowWidth/2)).toFloat()
    }

}
object AndesTooltipArrowLeftBottom : AndesTooltipArrowLocation() {
    override fun getArrowPositionX(tooltip: AndesTooltipLocationInterface): Float {
        return tooltip.frameLayoutContainer.x
    }

    override fun getArrowPositionY(tooltip: AndesTooltipLocationInterface): Float {
        return (tooltip.frameLayoutContainer.height - tooltip.arrowBorder - tooltip.arrowWidth).toFloat()
    }

}

enum class ArrowPositionId{
    TOP,
    LEFT,
    RIGHT,
    BOTTOM,
    MIDDLE
}

internal fun getAndesTooltipArrowLocation(tooltipSideId: ArrowPositionId, positionInSideId: ArrowPositionId): AndesTooltipArrowLocation =
        when(tooltipSideId to positionInSideId){
            ArrowPositionId.BOTTOM to ArrowPositionId.LEFT -> AndesTooltipArrowBottomLeft
            ArrowPositionId.BOTTOM to ArrowPositionId.MIDDLE -> AndesTooltipArrowBottomMiddle
            ArrowPositionId.BOTTOM to ArrowPositionId.RIGHT -> AndesTooltipArrowBottomRight

            ArrowPositionId.TOP to ArrowPositionId.LEFT -> AndesTooltipArrowTopLeft
            ArrowPositionId.TOP to ArrowPositionId.MIDDLE -> AndesTooltipArrowTopMiddle
            ArrowPositionId.TOP to ArrowPositionId.RIGHT -> AndesTooltipArrowTopRight

            ArrowPositionId.RIGHT to ArrowPositionId.TOP -> AndesTooltipArrowRightTop
            ArrowPositionId.RIGHT to ArrowPositionId.MIDDLE -> AndesTooltipArrowRightMiddle
            ArrowPositionId.RIGHT to ArrowPositionId.BOTTOM -> AndesTooltipArrowRightBottom

            ArrowPositionId.LEFT to ArrowPositionId.TOP -> AndesTooltipArrowLeftTop
            ArrowPositionId.LEFT to ArrowPositionId.MIDDLE -> AndesTooltipArrowLeftMiddle
            ArrowPositionId.LEFT to ArrowPositionId.BOTTOM -> AndesTooltipArrowLeftBottom

            else -> AndesTooltipArrowBottomLeft
        }
