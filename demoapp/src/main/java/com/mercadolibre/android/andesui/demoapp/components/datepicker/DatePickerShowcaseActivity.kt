package com.mercadolibre.android.andesui.demoapp.components.datepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.datepicker.AndesDatePicker
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.CustomViewPager
import com.mercadolibre.android.andesui.demoapp.utils.PageIndicator
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DatePickerShowcaseActivity : AppCompatActivity() {

    private lateinit var viewPager: CustomViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        initActionBar()
        initViewPager()
        attachIndicator()
        loadViews()
    }

    private fun initActionBar() {
        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andes_demoapp_screen_datepicker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViewPager() {
        val inflater = LayoutInflater.from(this)
        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.adapter = AndesPagerAdapter(listOf<View>(
                inflater.inflate(R.layout.andesui_static_datepicker, null, false)
        ))
    }

    private fun attachIndicator() {
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun loadViews() {
        val adapter = viewPager.adapter as AndesPagerAdapter
        addStaticPage(adapter.views[0])
    }

    private fun addStaticPage(container: View) {
        val datepicker: AndesDatePicker = container.findViewById(R.id.andesDatePicker)
        val btnSend: AndesButton = container.findViewById(R.id.btnSendMinMaxDate)
        val btnReset: AndesButton = container.findViewById(R.id.btnReset)
        val inputMinDate: AndesTextfield = container.findViewById(R.id.andesTextfieldMinDate)
        val inputMaxDate: AndesTextfield = container.findViewById(R.id.andesTextfieldMaxDate)
        datepicker.setupButtonVisibility(false)
        datepicker.setupButtonText("Aplicar")

        fun convertStringToDate(time: String, format: String): Date {
            val format = SimpleDateFormat(format)
            return format.parse(time)
        }

        fun isValid(time: String, format: String): Boolean {
            val df = SimpleDateFormat(format)
            df.isLenient = false
            return try {
                df.parse(time)
                true
            } catch (e: ParseException) {
                false
            }
        }

        btnSend.setOnClickListener() {
            datepicker.clearMinMaxDate()
            val setterMax: String? = inputMaxDate.text?.trim()
            val setterMin: String? = inputMinDate.text?.trim()
            if (setterMax != null && setterMax.isNotEmpty() && isValid(setterMax, "dd/MM/yyyy")) {
                datepicker.setupMaxDate(convertStringToDate(setterMax, "dd/MM/yyyy").time)
            } else {
                Toast.makeText(this, "la fecha maxima no es una fecha valida", Toast.LENGTH_SHORT).show()
            }
            if (setterMin != null && setterMin.isNotEmpty() && isValid(setterMin, "dd/MM/yyyy")) {
                datepicker.setupMinDate(convertStringToDate(setterMin, "dd/MM/yyyy").time)
            } else {
                Toast.makeText(this, "la fecha minima no es una fecha valida", Toast.LENGTH_SHORT).show()
            }
        }

        btnReset.setOnClickListener() {
            datepicker.clearMinMaxDate()
        }

        datepicker.setDateAppearance(R.style.Andes_CalendarDateDemo)
        datepicker.setWeekDayAppearance(R.style.Andes_CalendarDateWeekDemo)

        datepicker.setDateListener(object : AndesDatePicker.ApplyDatePickerClickListener {
            override fun onDateApply(date: Calendar) {
                val dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
                val formattedDate = dateFormatter.format(date.time)
                Toast.makeText(this@DatePickerShowcaseActivity, formattedDate, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
