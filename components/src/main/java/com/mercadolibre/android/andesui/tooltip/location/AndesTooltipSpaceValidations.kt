package com.mercadolibre.android.andesui.tooltip.location

import android.view.View
import com.mercadolibre.android.andesui.tooltip.extensions.getActionBarHeight
import com.mercadolibre.android.andesui.tooltip.extensions.getStatusBarHeight
import com.mercadolibre.android.andesui.tooltip.extensions.getViewPointOnScreen
import com.mercadolibre.android.andesui.tooltip.extensions.isActionBarVisible

internal fun AndesTooltipLocation.getSpaceConditionByLocation(): ((tooltip: AndesTooltipLocationInterface, target: View) -> Boolean) {
    return when (this) {
        AndesTooltipLocation.TOP -> tooltipHasTopSpace
        AndesTooltipLocation.BOTTOM -> tooltipHasBottomSpace
        AndesTooltipLocation.LEFT -> tooltipHasLeftSpace
        AndesTooltipLocation.RIGHT -> tooltipHasRightSpace
    }
}

internal data class AndesTooltipArrowData(val positionInSide: ArrowPositionId, val point: Int)

internal val tooltipHasTopSpace = fun (tooltip: AndesTooltipLocationInterface, target: View): Boolean {
    val actionBarHeight = target.getActionBarHeight() + target.getStatusBarHeight(true)
    val actionBarVisible = target.isActionBarVisible()
    val targetY = target.getViewPointOnScreen().y
    val tooltipHeight = tooltip.bodyWindowHeight

    return if (!actionBarVisible) {
        targetY - tooltipHeight > 0
    } else {
        targetY - tooltipHeight - actionBarHeight > 0
    }
}

internal val tooltipHasBottomSpace = fun (tooltip: AndesTooltipLocationInterface, target: View): Boolean {
    val targetY = target.getViewPointOnScreen().y
    val targetHeight = target.height
    val tooltipHeight = tooltip.bodyWindowHeight
    val bottomWall = tooltip.displaySizeY

    return targetY + targetHeight + tooltipHeight < bottomWall
}

internal val tooltipHasLeftSpace = fun (tooltip: AndesTooltipLocationInterface, target: View): Boolean {
    val targetX = target.getViewPointOnScreen().x
    val tooltipWidth = tooltip.bodyWindowWidth

    return targetX - tooltipWidth > 0
}

internal val tooltipHasRightSpace = fun (tooltip: AndesTooltipLocationInterface, target: View): Boolean {
    val targetX = target.getViewPointOnScreen().x
    val targetWidth = target.width
    val tooltipWidth = tooltip.bodyWindowWidth
    val rightWall = tooltip.displaySizeX

    return targetX + targetWidth + tooltipWidth < rightWall
}

internal fun getTooltipXOff(target: View, tooltip: AndesTooltipLocationInterface): AndesTooltipArrowData {

    val targetX = target.getViewPointOnScreen().x
    val targetWidth = target.measuredWidth
    val targetHalfXPoint = targetX + (targetWidth / 2)

    val tooltipWidth = tooltip.tooltipMeasuredWidth
    val tooltipHalf = tooltipWidth / 2

    val leftSpaceNeededForCenterArrow = targetHalfXPoint - tooltipHalf
    val rightSpaceNeededForCenterArrow = targetHalfXPoint + tooltipHalf

    val rightSpaceNeededForLeftArrow = tooltipWidth - tooltip.arrowWidth / 2 - tooltip.arrowBorder
    val availableSpaceForLeftArrow = tooltip.displaySizeX - targetHalfXPoint

    val canArrowCenter = leftSpaceNeededForCenterArrow > 0 && rightSpaceNeededForCenterArrow < tooltip.displaySizeX
    val canArrowLeft = rightSpaceNeededForLeftArrow < availableSpaceForLeftArrow

    return when {
        (canArrowCenter) -> {
            AndesTooltipArrowData(
                    positionInSide = ArrowPositionId.MIDDLE,
                    point = ((targetWidth / 2) - (tooltip.tooltipMeasuredWidth / 2))
            )
        }
        (canArrowLeft) -> {
            AndesTooltipArrowData(
                    positionInSide = ArrowPositionId.LEFT,
                    point = (targetWidth / 2 - tooltip.arrowWidth - tooltip.arrowBorder)
            )
        }
        else -> {
            AndesTooltipArrowData(
                    positionInSide = ArrowPositionId.RIGHT,
                    point = (-tooltip.tooltipMeasuredWidth + targetWidth / 2 + tooltip.arrowWidth + tooltip.arrowBorder)
            )
        }
    }
}

internal fun getTooltipYOff(target: View, tooltip: AndesTooltipLocationInterface): AndesTooltipArrowData {
    val actionBarHeight = target.getActionBarHeight() + target.getStatusBarHeight(true)
    val actionBarVisible = target.isActionBarVisible()

    val targetY = target.getViewPointOnScreen().y
    val targetHeight = target.measuredHeight
    val targetHalfYPoint = targetY + (targetHeight / 2)

    val tooltipHeight = tooltip.tooltipMeasuredHeight
    val tooltipHalf = tooltipHeight / 2

    val topSpaceNeededForCenterArrow = targetHalfYPoint - tooltipHalf
    val bottomSpaceNeededForCenterArrow = targetHalfYPoint + tooltipHalf
    val topWall = if (actionBarVisible) { actionBarHeight } else { 0 }

    val bottomSpaceNeededForTopArrow = targetHalfYPoint - tooltip.arrowHeight / 2 - tooltip.arrowBorder + tooltipHeight
    val availableSpaceForTopArrow = tooltip.displaySizeY - targetHalfYPoint

    val canArrowCenter = topSpaceNeededForCenterArrow > topWall && bottomSpaceNeededForCenterArrow < tooltip.displaySizeY
    val canArrowTop = bottomSpaceNeededForTopArrow < availableSpaceForTopArrow

    return when {
        (canArrowCenter) -> {
            AndesTooltipArrowData(
                    positionInSide = ArrowPositionId.MIDDLE,
                    point = -((tooltipHeight / 2) + (targetHeight / 2))
            )
        }
        (canArrowTop) -> {
            AndesTooltipArrowData(
                    positionInSide = ArrowPositionId.TOP,
                    point = -(targetHeight/2 + tooltip.arrowWidth + tooltip.arrowBorder + tooltip.arrowImageInnerPadding)
            )
        }
        else -> {
            AndesTooltipArrowData(
                    positionInSide = ArrowPositionId.BOTTOM,
                    point = -tooltipHeight + tooltip.arrowWidth / 2 - tooltip.arrowBorder + targetHeight / 2
            )
        }
    }
}
