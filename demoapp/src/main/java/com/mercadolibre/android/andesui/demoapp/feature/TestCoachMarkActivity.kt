package com.mercadolibre.android.andesui.demoapp.feature

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmark
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStep
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStyle
import com.mercadolibre.android.andesui.coachmark.view.CoachmarkView
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.commons.BaseActivity
import kotlinx.android.synthetic.main.andesui_coachmark_activity.*
import java.util.Date
import kotlin.collections.ArrayList

@SuppressWarnings("MaxLineLength")
class TestCoachMarkActivity : BaseActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var screensTime: Long = 0
    private var startTime: Long = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_coachmark_activity)
        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_coachmark)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        texto.text = "Texto a resaltar"
        textoLargo.text = "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"
        actionButton.text = "Empezar CoachMark"
        textoAbajo.text = "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"

        val stepsNewCoachmark = ArrayList<AndesWalkthroughCoachmarkStep>()

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep(
                "Primer titulo",
                "Resaltamos el primer texto",
                "Siguiente",
                texto,
                AndesWalkthroughCoachmarkStyle.RECTANGLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Segundo titulo",
                "Probando el circulo magico con flecha abajo a la izquierda Probando el " +
                        "circulo magico con flecha abajo a la izquierda Probando el circulo magico " +
                        "con flecha abajo a la izquierda",
                "Siguiente",
                circleAdd,
                AndesWalkthroughCoachmarkStyle.CIRCLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Tercer titulo ",
                "Resaltamos el primer texto",
                "Siguiente",
                texto,
                AndesWalkthroughCoachmarkStyle.RECTANGLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Cuarto titulo ",
                "Probando el circulo magico con flecha abajo a la derecha",
                "Siguiente",
                circleRight,
                AndesWalkthroughCoachmarkStyle.CIRCLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Quinto titulo ",
                "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo " +
                        "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo " +
                        "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo " +
                        "Resaltamos el texto largo Resaltamos el texto largo",
                "Siguiente",
                textoLargo,
                AndesWalkthroughCoachmarkStyle.RECTANGLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Sexto titulo ",
                "Si vemos esto es porque scrolleo al fin y estamos al final del coachmark ;)",
                "Siguiente",
                textoAbajo,
                AndesWalkthroughCoachmarkStyle.RECTANGLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Septimo titulo ",
                "Probando el circulo magico con flecha arriba a la izquierda",
                "Siguiente",
                circleAdd,
                AndesWalkthroughCoachmarkStyle.CIRCLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Octavo titulo ",
                "Probando el circulo magico con flecha arriba a la derecha Probando el " +
                        "circulo magico con flecha arriba a la derecha Probando el circulo magico con " +
                        "flecha arriba a la derecha Probando el circulo magico con flecha arriba a la derecha",
                "Siguiente",
                circleRight,
                AndesWalkthroughCoachmarkStyle.CIRCLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Noveno titulo ",
                "Probando scroll hacia arriba", "Siguiente",
                textoLargo,
                AndesWalkthroughCoachmarkStyle.RECTANGLE)
        )

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Decimo titulo ",
                "Esto sigue en prueba y esta bueno que funcione bien",
                "Siguiente",
                actionButton,
                AndesWalkthroughCoachmarkStyle.RECTANGLE)
        )

        actionButton.setOnClickListener {
            CoachmarkView.Builder(this, AndesWalkthroughCoachmark(stepsNewCoachmark, scrollview) {
                println("Entro al despues de cerrar")
            }).build()
        }

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    override fun onDestroy() {
        if (startTime > 0) {
            val diff = Date().time - startTime
            screensTime += diff
        }

        // Track
        val bundle = Bundle()
        bundle.putLong("Screen 1", screensTime)
        firebaseAnalytics.logEvent(getString(R.string.firebase_analytics_coach_mark).replace(" ", ""), bundle)

        super.onDestroy()
    }

    override fun onStop() {
        val diff = Date().time - startTime
        screensTime += diff
        startTime = 0
        super.onStop()
    }

    override fun onResume() {
        startTime = Date().time
        super.onResume()
    }
}
