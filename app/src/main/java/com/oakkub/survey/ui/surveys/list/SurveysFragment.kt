package com.oakkub.survey.ui.surveys.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eggdigital.trueyouedc.extensions.delegateTo
import com.eggdigital.trueyouedc.extensions.views.toast
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseFragment
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.extensions.observe
import com.oakkub.survey.ui.surveys.list.adapter.content.SurveysItemAdapter
import com.oakkub.survey.ui.surveys.list.adapter.content.SurveysItemAdapterMapperImpl
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_surveys.*
import javax.inject.Inject

class SurveysFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SurveysViewModel

    private val surveysItemAdapter: SurveysItemAdapter by lazy {
        SurveysItemAdapter { survey ->
            delegateTo<OnNavigationListener> { onNavigationListener ->
                onNavigationListener.onTakeSurvey(survey.surveyResponse)
            }
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_surveys, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(surveysItemRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = surveysItemAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(SurveysViewModel::class.java)
        viewModel.result.observe(this, this::updateView)

        // savedInstanceState will always be null in fragment if we don't save any state :(
        // So I decided to check first time this way
        if (viewModel.result.value == null) {
            viewModel.getSurveys()
        }
    }

    private fun updateView(surveysUiModel: SurveysUiModel) {
        when (surveysUiModel) {
            is SurveysUiModel.Error -> {
                surveysItemAdapter.submitList(listOf())
                toast(surveysUiModel.throwable.message.toString())
            }
            is SurveysUiModel.Loading,
            is SurveysUiModel.Success -> {
                val mappedResult = SurveysItemAdapterMapperImpl().map(surveysUiModel)
                surveysItemAdapter.submitList(mappedResult)
            }
        }
    }

    fun refresh() {
        viewModel.refresh()
    }

    interface OnNavigationListener {
        fun onTakeSurvey(surveyResponse: SurveyResponse)
    }

    companion object {

        @JvmStatic
        fun newInstance(): SurveysFragment = SurveysFragment()

    }


}
