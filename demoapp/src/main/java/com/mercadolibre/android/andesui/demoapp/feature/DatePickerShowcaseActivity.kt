package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.datepicker.AndesDatePicker
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.AndesPagerAdapter
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import com.mercadolibre.android.andesui.demoapp.feature.utils.PageIndicator
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class DatePickerShowcaseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_datepicker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.andesui_viewpager)
        viewPager.setScreenName(getString(R.string.andesui_demoapp_datepicker_button))
        viewPager.adapter = AndesPagerAdapter(loadViews())

        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    private fun loadViews(): List<View> {
        val inflater = LayoutInflater.from(this)
        val staticDatePickerLayout = addStaticDatePicker(inflater)
        return listOf(staticDatePickerLayout)
    }

    private fun addStaticDatePicker(inflater: LayoutInflater): View {
        val layoutDatePicker = inflater.inflate(
                R.layout.andesui_date_picker_showcase,
                null,
                false
        ) as ScrollView

        val datepicker: AndesDatePicker = layoutDatePicker.findViewById(R.id.andesDatePicker)
        val btnSend: AndesButton = layoutDatePicker.findViewById(R.id.btnSendMinMaxDate)
        val btnReset: AndesButton = layoutDatePicker.findViewById(R.id.btnReset)
        val inputMinDate: AndesTextfield = layoutDatePicker.findViewById(R.id.andesTextfieldMinDate)
        val inputMaxDate: AndesTextfield = layoutDatePicker.findViewById(R.id.andesTextfieldMaxDate)
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

        return layoutDatePicker
    }
}
