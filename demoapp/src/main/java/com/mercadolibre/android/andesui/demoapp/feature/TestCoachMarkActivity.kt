package com.mercadolibre.android.andesui.demoapp.feature

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmark
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStep
import com.mercadolibre.android.andesui.coachmark.model.AndesWalkthroughCoachmarkStyle
import com.mercadolibre.android.andesui.coachmark.view.CoachmarkView
import com.mercadolibre.android.andesui.demoapp.R
import kotlinx.android.synthetic.main.andesui_coachmark_activity.*

@SuppressWarnings("MaxLineLength")
class TestCoachMarkActivity : AppCompatActivity() {

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

        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Primer titulo",
                "Resaltamos el primer texto", "Siguiente", texto, AndesWalkthroughCoachmarkStyle.RECTANGLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Segundo titulo",
                "Probando el circulo magico con flecha abajo a la izquierda Probando el " +
                        "circulo magico con flecha abajo a la izquierda Probando el circulo magico " +
                        "con flecha abajo a la izquierda", "Siguiente", circleAdd, AndesWalkthroughCoachmarkStyle.CIRCLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Tercer titulo ",
                "Resaltamos el primer texto", "Siguiente", texto, AndesWalkthroughCoachmarkStyle.RECTANGLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Cuarto titulo ",
                "Probando el circulo magico con flecha abajo a la derecha",
                "Siguiente", circleRight, AndesWalkthroughCoachmarkStyle.CIRCLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Quinto titulo ",
                "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo " +
                        "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo " +
                        "Resaltamos el texto largo Resaltamos el texto largo Resaltamos el texto largo " +
                        "Resaltamos el texto largo Resaltamos el texto largo",
                "Siguiente", textoLargo, AndesWalkthroughCoachmarkStyle.RECTANGLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Sexto titulo ",
                "Si vemos esto es porque scrolleo al fin y estamos al final del coachmark ;)",
                "Siguiente", textoAbajo, AndesWalkthroughCoachmarkStyle.RECTANGLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Septimo titulo ",
                "Probando el circulo magico con flecha arriba a la izquierda",
                "Siguiente", circleAdd, AndesWalkthroughCoachmarkStyle.CIRCLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Octavo titulo ",
                "Probando el circulo magico con flecha arriba a la derecha Probando el " +
                        "circulo magico con flecha arriba a la derecha Probando el circulo magico con " +
                        "flecha arriba a la derecha Probando el circulo magico con flecha arriba a la derecha",
                "Siguiente", circleRight, AndesWalkthroughCoachmarkStyle.CIRCLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Noveno titulo ",
                "Probando scroll hacia arriba", "Siguiente",
                textoLargo, AndesWalkthroughCoachmarkStyle.RECTANGLE))
        stepsNewCoachmark.add(AndesWalkthroughCoachmarkStep("Decimo titulo ",
                "Esto sigue en prueba y esta bueno que funcione bien",
                "Siguiente", actionButton, AndesWalkthroughCoachmarkStyle.RECTANGLE))

        actionButton.setOnClickListener {
            CoachmarkView.Builder(this, AndesWalkthroughCoachmark(stepsNewCoachmark, scrollview) {
                println("Entro al despues de cerrar")
            }).build()
        }
    }
}
