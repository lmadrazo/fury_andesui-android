package com.mercadolibre.android.andesui.demoapp.feature.andesbottomsheet

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.mercadolibre.android.andesui.bottomsheet.AndesBottomSheet
import com.mercadolibre.android.andesui.bottomsheet.BottomSheetListener
import com.mercadolibre.android.andesui.bottomsheet.title.AndesBottomSheetTitleAlignment
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_bottom_sheet_showcase.*

@Suppress("TooManyFunctions")
class BottomSheetShowcaseActivity : AppCompatActivity(), BottomSheetListener {
    private lateinit var bottomSheet: AndesBottomSheet
    private var showTitle = false
    private var leftAlignTitle = true
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_bottom_sheet_showcase)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_bottom_sheet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bottomSheet = findViewById(R.id.andes_bottom_sheet)
        bottomSheet.setBottomSheetListener(this)
    }

    override fun onCollapsed() {
        Toast.makeText(applicationContext, "Collapsed!", Toast.LENGTH_SHORT).show()
    }

    override fun onExpanded() {
        Toast.makeText(applicationContext, "Expanded!", Toast.LENGTH_SHORT).show()
    }

    fun onAttachViewButtonClicked(view: View) {
        bottomSheet.removeContent()
        if (textView == null) {
            textView = TextView(applicationContext)
            val params = ViewGroup.LayoutParams(MATCH_PARENT, VIEW_HEIGHT)
            textView?.layoutParams = params
            textView?.text = getString(R.string.andesui_demoapp_bottom_sheet_dummy_view)
            textView?.gravity = Gravity.CENTER
        }

        bottomSheet.setContent(textView!!)
        bottomSheet.expand()
    }

    fun onAttachFragmentButtonClicked(view: View) {
        bottomSheet.removeContent()

        bottomSheet.setContent(supportFragmentManager, TestFragment())

        Handler().postDelayed(  {
            bottomSheet.expand()
        }, ONE_HUNDRED_MS)
    }

    fun onToggleTitleClicked(view: View) {
        val andesButton = view as AndesButton
        showTitle = !showTitle
        if (showTitle) {
            bottomSheet.titleText = "Hello, I'm Title"
            andesButton.hierarchy = AndesButtonHierarchy.QUIET
        } else {
            bottomSheet.titleText = null
            andesButton.hierarchy = AndesButtonHierarchy.LOUD
        }
    }
    fun onToggleAlignButtonClicked(view: View) {
        val andesButton = view as AndesButton
        leftAlignTitle = !leftAlignTitle
        if (leftAlignTitle) {
            bottomSheet.titleAlignment = AndesBottomSheetTitleAlignment.LEFT_ALIGN
            andesButton.hierarchy = AndesButtonHierarchy.LOUD
        } else {
            bottomSheet.titleAlignment = AndesBottomSheetTitleAlignment.CENTERED
            andesButton.hierarchy = AndesButtonHierarchy.QUIET
        }
    }

    fun onSetPeekHeightButtonClicked(view: View) {
        if (!andes_bottom_sheet_peek_height_text_field.text.isNullOrEmpty()) {
            bottomSheet.peekHeight = andes_bottom_sheet_peek_height_text_field.text!!.toInt()
            closeKeyboard(view)
            andes_bottom_sheet_peek_height_text_field.text = ""
        }
    }

    private fun closeKeyboard(view: View) {
        val inputManager: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        private const val VIEW_HEIGHT = 800
        private const val ONE_HUNDRED_MS = 100.toLong()
    }
}
