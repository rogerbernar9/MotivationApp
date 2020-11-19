package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstants.FRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        textName.text =  mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        //logica inicial de selecao
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewFrase()

        buttonNewFrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageSun.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id

        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageSun)

        if (id == R.id.buttonNewFrase) {
            handleNewFrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id:Int) {
        imageSun.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageAll.setColorFilter(resources.getColor(R.color.white))


        when (id) {
            R.id.imageSun -> {
                imageSun.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.FRASEFILTER.MORNING
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.FRASEFILTER.HAPPY
            }
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.FRASEFILTER.ALL
            }
        }
    }

    private fun handleNewFrase() {
        val frase = Mock().getPhrase(mPhraseFilter)
        textFrase.text = frase
    }
}