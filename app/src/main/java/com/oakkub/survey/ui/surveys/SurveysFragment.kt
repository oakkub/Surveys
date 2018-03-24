package com.oakkub.survey.ui.surveys

import android.os.Bundle
import android.view.View
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseFragment

class SurveysFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_surveys

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {

        @JvmStatic
        fun newInstance(): SurveysFragment = SurveysFragment()

    }


}
