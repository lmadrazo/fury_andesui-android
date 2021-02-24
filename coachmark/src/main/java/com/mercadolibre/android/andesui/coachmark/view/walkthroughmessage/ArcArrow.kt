package com.mercadolibre.android.andesui.coachmark.view.walkthroughmessage
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.mercadolibre.android.andesui.coachmark.R

class ArcArrow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint: Paint = Paint()

    // About arcs
    var rectTooltipF: RectF
    var rectTargetF: RectF
    private val radius: Float
    var startTooltipAngle = ANGLE_90
    var startTargetAngle = ANGLE_270
    private var sweepAngle = ANGLE_90

    // About line between arcs
    val linePosition = mutableListOf<Float>() // [0] xStart - yStart - xEnd - yEnd

    // Vars about head of arrow
    var startHeadArrowX: Float = START_POINT
    var startHeadArrowY: Float = START_POINT
    var endLeftX: Float = START_POINT
    var endLeftY: Float = START_POINT
    var endRightX: Float = START_POINT
    var endRightY: Float = START_POINT
    private val lengthHeadArrow: Float
    private val lengthRadioHeadArrow: Float

    init {
        // set to STOKE
        mPaint.style = Paint.Style.STROKE
        // set the paint cap to round too
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = context.resources.getDimension(R.dimen.andes_coachmark_stroke_arrow)
        mPaint.isAntiAlias = true
        mPaint.color = ContextCompat.getColor(context, R.color.andes_white)
        rectTooltipF = RectF()
        rectTargetF = RectF()

        lengthHeadArrow = context.resources.getDimension(R.dimen.andes_coachmark_lenght_head_arrow)
        lengthRadioHeadArrow = lengthHeadArrow / NUMBER_TWO

        radius = context.resources.getDimension(R.dimen.andes_coachmark_radius_arrow)
    }

    fun addRect(xTooltip: Int, yTooltip: Int, xTarget: Int, yTarget: Int) {

        // flecha derecha abajo
        if (xTooltip < xTarget && yTooltip < yTarget) {
            setBottomRightArrow(xTooltip, yTooltip, xTarget)

            // flecha izquierda abajo
        } else if (xTarget < xTooltip && yTooltip < yTarget) {
            setBottomLeftArrow(xTooltip, yTooltip, xTarget)

            // flecha derecha arriba
        } else if (xTooltip < xTarget && yTarget < yTooltip) {
            setTopRightArrow(xTooltip, yTooltip, xTarget)

        // flecha izquierda arriba
        } else {
            setTopLeftArrow(xTooltip, yTooltip, xTarget)
        }
    }

    private fun setBottomRightArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_90
        startTargetAngle = ANGLE_270

        rectTooltipF = RectF(
            xTooltip.toFloat(),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat() + (NUMBER_TWO * radius),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() + radius,
            xTarget.toFloat(),
            yTooltip.toFloat() + (NUMBER_THREE * radius))

        linePosition.add(xTooltip.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() + radius)
        linePosition.add(xTarget.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() + radius)

        startHeadArrowX = xTarget.toFloat()
        startHeadArrowY = yTooltip.toFloat() + (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() -
            lengthRadioHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_small_x_new_position_arrow)
        endLeftY = yTooltip.toFloat() +
            (NUMBER_TWO * radius) -
            lengthHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_large_y_new_position_arrow)
        endRightX = xTarget.toFloat() +
            lengthRadioHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_large_x_new_position_arrow)
        endRightY = yTooltip.toFloat() +
            (NUMBER_TWO * radius) -
            lengthHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_small_y_new_position_arrow)
    }

    private fun setBottomLeftArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_0
        startTargetAngle = ANGLE_180

        rectTooltipF = RectF(
            xTooltip.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat(),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat(),
            yTooltip.toFloat() + radius,
            xTarget + (NUMBER_TWO * radius),
            yTooltip.toFloat() + (NUMBER_THREE * radius))

        linePosition.add(xTarget.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() + radius)
        linePosition.add(xTooltip.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() + radius)

        startHeadArrowX = xTarget.toFloat()
        startHeadArrowY = yTooltip.toFloat() + (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() -
            lengthRadioHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_large_x_new_position_arrow)
        endLeftY = yTooltip.toFloat() +
            (NUMBER_TWO * radius) -
            lengthHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_small_y_new_position_arrow)
        endRightX = xTarget.toFloat() +
            lengthRadioHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_small_x_new_position_arrow)
        endRightY = yTooltip.toFloat() +
            (NUMBER_TWO * radius) -
            lengthHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_large_y_new_position_arrow)
    }

    private fun setTopRightArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_180
        startTargetAngle = ANGLE_0

        rectTooltipF = RectF(
            xTooltip.toFloat(),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat() + (NUMBER_TWO * radius),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() - (NUMBER_THREE * radius),
            xTarget.toFloat(),
            yTooltip.toFloat() - radius)

        linePosition.add(xTooltip.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() - radius)
        linePosition.add(xTarget.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() - radius)

        startHeadArrowX = xTarget.toFloat()
        startHeadArrowY = yTooltip.toFloat() - (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() -
            lengthRadioHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_small_x_new_position_arrow)
        endLeftY = yTooltip.toFloat() -
            (NUMBER_TWO * radius) +
            lengthHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_large_y_new_position_arrow)
        endRightX = xTarget.toFloat() +
            lengthRadioHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_large_x_new_position_arrow)
        endRightY = yTooltip.toFloat() -
            (NUMBER_TWO * radius) +
            lengthHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_small_y_new_position_arrow)
    }

    private fun setTopLeftArrow(xTooltip: Int, yTooltip: Int, xTarget: Int) {

        startTooltipAngle = ANGLE_270
        startTargetAngle = ANGLE_90

        rectTooltipF = RectF(
            xTooltip.toFloat() - (NUMBER_TWO * radius),
            yTooltip.toFloat() - radius,
            xTooltip.toFloat(),
            yTooltip.toFloat() + radius)

        rectTargetF = RectF(
            xTarget.toFloat(),
            yTooltip.toFloat() - (NUMBER_THREE * radius),
            xTarget.toFloat() + (NUMBER_TWO * radius),
            yTooltip.toFloat() - radius)

        linePosition.add(xTarget.toFloat() + radius)
        linePosition.add(yTooltip.toFloat() - radius)
        linePosition.add(xTooltip.toFloat() - radius)
        linePosition.add(yTooltip.toFloat() - radius)

        startHeadArrowX = xTarget.toFloat()
        startHeadArrowY = yTooltip.toFloat() - (NUMBER_TWO * radius)
        endLeftX = xTarget.toFloat() -
                lengthRadioHeadArrow +
                context.resources.getDimension(R.dimen.andes_coachmark_large_x_new_position_arrow)
        endLeftY = yTooltip.toFloat() -
            (NUMBER_TWO * radius) +
            lengthHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_small_y_new_position_arrow)
        endRightX = xTarget.toFloat() +
            lengthRadioHeadArrow +
            context.resources.getDimension(R.dimen.andes_coachmark_small_x_new_position_arrow)
        endRightY = yTooltip.toFloat() -
            (NUMBER_TWO * radius) +
            lengthHeadArrow -
            context.resources.getDimension(R.dimen.andes_coachmark_large_y_new_position_arrow)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (linePosition.isNotEmpty()) {

            // arc start on tooltip
            canvas.drawArc(rectTooltipF, startTooltipAngle, sweepAngle, false, mPaint)

            // arc start on highlighted view
            canvas.drawArc(rectTargetF, startTargetAngle, sweepAngle, false, mPaint)

            // line between arcs
            canvas.drawLine(linePosition[0], linePosition[NUMBER_ONE], linePosition[NUMBER_TWO], linePosition[NUMBER_THREE], mPaint)

            // head arrow
            canvas.drawLine(startHeadArrowX, startHeadArrowY, endLeftX, endLeftY, mPaint)
            canvas.drawLine(startHeadArrowX, startHeadArrowY, endRightX, endRightY, mPaint)
        }
    }

    fun clear() {
        rectTooltipF = RectF()
        rectTargetF = RectF()
        linePosition.clear()
    }

    companion object {
        const val STROKE = 1F

        const val NUMBER_ONE = 1
        const val NUMBER_TWO = 2
        const val NUMBER_THREE = 3

        const val START_POINT = 0F

        const val ANGLE_0 = 0F
        const val ANGLE_90 = 90F
        const val ANGLE_180 = 180F
        const val ANGLE_270 = 270F
    }
}
