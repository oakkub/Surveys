package com.oakkub.survey.ui.surveys

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eggdigital.trueyouedc.extensions.views.toast
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseFragment
import com.oakkub.survey.extensions.observe
import com.oakkub.survey.ui.surveys.adapter.content.SurveysItemAdapter
import com.oakkub.survey.ui.surveys.adapter.content.SurveysItemAdapterMapperImpl
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_surveys.*
import javax.inject.Inject

class SurveysFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SurveysViewModel

    private val surveysItemAdapter: SurveysItemAdapter by lazy {
        SurveysItemAdapter()
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
        viewModel.getSurveys()
    }

    private fun updateView(surveysUiModel: SurveysUiModel) {
        when (surveysUiModel) {
            is SurveysUiModel.Error -> toast(surveysUiModel.throwable.message.toString())
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

    companion object {

        @JvmStatic
        fun newInstance(): SurveysFragment = SurveysFragment()

    }


}
