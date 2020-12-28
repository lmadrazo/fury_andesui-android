package com.mercadolibre.android.andesui.demoapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.mercadolibre.android.andesui.demoapp.R
import io.noties.markwon.Markwon
import java.io.ByteArrayOutputStream
import java.io.InputStream

class WhatsNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_demoapp_whatsnew)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_whatsnew)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val whatsNew = findViewById<TextView>(R.id.andesui_demoapp_whats_new)

        val changelog = readChangelogFile()
        if (changelog.isEmpty()) {
            whatsNew.setText(R.string.andesui_demoapp_changelog_error)
        } else {
            val markwon = Markwon.create(this)
            markwon.setMarkdown(whatsNew, changelog)
        }
    }

    private fun readChangelogFile(): String {
        val inputStream: InputStream = resources.openRawResource(R.raw.andesui_demoapp_changelog)
        val byteArrayOutputStream = ByteArrayOutputStream()

        inputStream.use {
            var i = inputStream.read()
            while (i != -1) {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }
        }

        return byteArrayOutputStream.toString()
    }
}
