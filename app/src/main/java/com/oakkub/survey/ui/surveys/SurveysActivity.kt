package com.oakkub.survey.ui.surveys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.oakkub.survey.R
import kotlinx.android.synthetic.main.activity_surveys.*

class SurveysActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surveys)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(surveysRootContainer.id, SurveysFragment.newInstance())
                    .commit()
        }
    }
}
