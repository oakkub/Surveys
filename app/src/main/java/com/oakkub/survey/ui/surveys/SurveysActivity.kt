package com.oakkub.survey.ui.surveys

import android.os.Bundle
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseActivity
import kotlinx.android.synthetic.main.activity_surveys.*

class SurveysActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surveys)

        surveysRefreshImageView.setOnClickListener {
            val surveysFragment = supportFragmentManager.findFragmentByTag(TAG_SURVEYS_FRAGMENT)
                    as? SurveysFragment
                    ?: return@setOnClickListener

            surveysFragment.refresh()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(surveysContentRootContainer.id, SurveysFragment.newInstance(), TAG_SURVEYS_FRAGMENT)
                    .commit()
        }
    }

    companion object {

        private const val TAG_SURVEYS_FRAGMENT = "TAG_SURVEYS_FRAGMENT"

    }

}
