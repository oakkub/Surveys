package com.oakkub.survey.ui.surveys

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SurveysFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SurveysViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_surveys, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(SurveysViewModel::class.java)
        viewModel.result.observe(this, Observer {


        })
        viewModel.getSurveys()
    }

    companion object {

        @JvmStatic
        fun newInstance(): SurveysFragment = SurveysFragment()

    }


}
