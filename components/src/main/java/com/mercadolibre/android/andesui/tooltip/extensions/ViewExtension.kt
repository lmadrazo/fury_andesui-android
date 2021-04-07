package com.mercadolibre.android.andesui.tooltip.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.view.View
import androidx.appcompat.app.AppCompatActivity

internal const val DEFAULT_HEIGHT = 0
/** gets display size as a point. */
internal fun Context.displaySize(): Point {
  return Point(
          resources.displayMetrics.widthPixels,
          resources.displayMetrics.heightPixels
  )
}
/** returns if an Activity is finishing or not. */
internal fun Context.isFinishing(): Boolean {
  return this is Activity && this.isFinishing
}

/** sets visibility of the view based on the given parameter. */
internal fun View.visible(shouldVisible: Boolean) {
  visibility = if (shouldVisible) {
    View.VISIBLE
  } else {
    View.GONE
  }
}

/** computes and returns the coordinates of this view on the screen. */
internal fun View.getViewPointOnScreen(): Point {
  val location: IntArray = intArrayOf(0, 0)
  getLocationOnScreen(location)
  return Point(location[0], location[1])
}

/** returns the status bar height if the anchor is on the Activity. */
internal fun View.getStatusBarHeight(isStatusBarVisible: Boolean): Int {
  val rectangle = Rect()
  return if (context is Activity && isStatusBarVisible) {
    (context as Activity).window.decorView.getWindowVisibleDisplayFrame(rectangle)
    rectangle.top
  } else { DEFAULT_HEIGHT }
}

internal fun View.getActionBarHeight(): Int {
  if (context is AppCompatActivity) {
    (context as AppCompatActivity).supportActionBar?.let {
      if (it.isShowing) { return it.height }
    }
  }
  return DEFAULT_HEIGHT
}

internal fun View.isActionBarVisible(): Boolean {
  if (context is AppCompatActivity) {
    (context as AppCompatActivity).supportActionBar?.let {
      return it.isShowing
    }
  }
  return false
}
