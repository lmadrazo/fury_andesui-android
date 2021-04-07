package com.mercadolibre.android.andesui.demoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.mercadolibre.android.andesui.demoapp.commons.AnalyticsHelper
import com.mercadolibre.android.andesui.demoapp.utils.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.utils.SafeIntent
import com.mercadolibre.android.andesui.demoapp.utils.launchSpecs
import kotlinx.android.synthetic.main.andesui_demoapp_main.*

/**
 * Main activity class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_demoapp_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_app_name)

        setupCoreComponents()
        setupExtras()
    }

    private fun setupCoreComponents() {
        andesui_tooltip.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/tooltip"))
        }
        andesui_carousel.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/carousel"))
        }
        andesui_coachmark.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/coachmark"))
        }
        andesui_card.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/card"))
        }
        andesui_checkbox.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/checkbox"))
        }
        andesui_radiobutton.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/radiobutton"))
        }
        andesui_snackbar.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/snackbar"))
        }
        andesui_tags.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/tag"))
        }
        andesui_badges.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/badge"))
        }
        andesui_buttons.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/button"))
        }
        andesui_messages.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/message"))
        }
        andesui_textfield.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/textfield"))
        }
        andesui_thumbnail.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/thumbnail"))
        }
        andesui_dropdown.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/dropdown"))
        }
        andesui_list.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/list"))
        }
        andesui_progress.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/progress"))
        }
        andesui_bottom_sheet.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/bottom_sheet"))
        }
        andesui_date_picker.setOnClickListener {
            startActivity(SafeIntent(this, "meli://andes/datepicker"))
        }
    }

    private fun setupExtras() {
        andesui_demoapp_changelog.setupPrimaryAction(
                getString(R.string.andes_demoapp_whatsnew_main_action),
                View.OnClickListener {
                    trackAnalytics(AnalyticsHelper.whatsNewTrack)
                    startActivity(SafeIntent(this, "meli://andes/whats-new"))
                }
        )

        andesui_demoapp_andes_specs.setOnClickListener {
            trackAnalytics(AnalyticsHelper.specsTrack)
            launchSpecs(this, AndesSpecs.HOME_PAGE)
        }

        andesui_demoapp_contribution.setOnClickListener {
            trackAnalytics(AnalyticsHelper.contributeTrack)
            ContextCompat.startActivity(this,
                    Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://meli.workplace.com/notes/andes-ui/c%C3%B3mo-contribuir-en-andes-ui/2559399620854933")),
                    null)
        }
    }

    private fun trackAnalytics(screen: String) {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screen)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle())
    }
}
