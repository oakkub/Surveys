package com.oakkub.survey.ui.surveys.list

import android.content.Intent
import android.os.Bundle
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseActivity
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.ui.surveys.detail.SurveyDetailActivity
import kotlinx.android.synthetic.main.activity_surveys.*

class SurveysActivity : BaseActivity(), SurveysFragment.OnNavigationListener {

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

    override fun onTakeSurvey(surveyResponse: SurveyResponse) {
        val intent = Intent(this, SurveyDetailActivity::class.java)
        startActivity(intent)
    }

    companion object {

        private const val TAG_SURVEYS_FRAGMENT = "TAG_SURVEYS_FRAGMENT"

    }

}
