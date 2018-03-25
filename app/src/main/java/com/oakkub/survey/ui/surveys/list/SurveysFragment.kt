package com.oakkub.survey.ui.surveys.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.view.doOnPreDraw
import com.eggdigital.trueyouedc.extensions.delegateTo
import com.eggdigital.trueyouedc.extensions.views.toast
import com.oakkub.survey.R
import com.oakkub.survey.common.controller.BaseFragment
import com.oakkub.survey.data.services.surveys.SurveyResponse
import com.oakkub.survey.extensions.observe
import com.oakkub.survey.ui.surveys.list.adapter.content.SurveysItemAdapter
import com.oakkub.survey.ui.surveys.list.adapter.content.SurveysItemAdapterMapperImpl
import com.oakkub.survey.ui.surveys.list.adapter.content.SurveysItemAdapterModel
import com.oakkub.survey.ui.surveys.list.adapter.dot.DotIndicatorItemAdapter
import com.oakkub.survey.ui.surveys.list.adapter.dot.DotIndicatorItemMapperImpl
import com.oakkub.survey.widgets.recyclerview.EndlessScrollRecyclerViewListener
import com.oakkub.survey.widgets.recyclerview.LinearOffsetItemDecoration
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_surveys.*
import javax.inject.Inject

class SurveysFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SurveysViewModel

    private val surveysLinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    private val surveysItemAdapter: SurveysItemAdapter by lazy {
        SurveysItemAdapter { survey ->
            delegateTo<OnNavigationListener> { onNavigationListener ->
                onNavigationListener.onTakeSurvey(survey.surveyResponse)
            }
        }
    }

    private val dotIndicatorLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    private val dotIndicatorAdapter: DotIndicatorItemAdapter by lazy {
        DotIndicatorItemAdapter()
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
        initSurveysRecyclerView()
        initDotIndicatorRecyclerView()
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
        if (surveysUiModel.error != null) {
            if (surveysUiModel.surveys.isEmpty()) {
                surveysItemAdapter.submitList(listOf())
                dotIndicatorAdapter.submitList(listOf())
            }
            toast(surveysUiModel.error.message.toString())
        }

        val mappedResult = SurveysItemAdapterMapperImpl().map(surveysUiModel)
        surveysItemAdapter.submitList(mappedResult)
        updateDotIndicatorView(mappedResult.count { it is SurveysItemAdapterModel.Item })
    }

    private fun initSurveysRecyclerView() {
        with(surveysItemRecyclerView) {
            layoutManager = surveysLinearLayoutManager
            adapter = surveysItemAdapter

            PagerSnapHelper().attachToRecyclerView(this)
            setHasFixedSize(true)

            addOnScrollListener(EndlessScrollRecyclerViewListener(VISIBLE_THRESHOLD) {
                viewModel.getSurveys()
            })

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        return
                    }
                    updateDotIndicatorView(recyclerView.layoutManager.itemCount)
                }
            })
        }
    }

    private fun initDotIndicatorRecyclerView() {
        with(surveysDotItemRecyclerView) {
            layoutManager = dotIndicatorLayoutManager
            adapter = dotIndicatorAdapter

            addItemDecoration(LinearOffsetItemDecoration(resources.getDimension(R.dimen.margin_padding_normal).toInt(), false))
            setOnTouchListener { _, _ -> true }
            setHasFixedSize(true)
        }
    }

    private fun updateDotIndicatorView(totalItem: Int) {
        if (totalItem <= 1) {
            dotIndicatorAdapter.submitList(listOf())
            return
        }

        view?.doOnPreDraw {
            val selectedPosition = surveysLinearLayoutManager.findFirstCompletelyVisibleItemPosition()
            val mappedResult = DotIndicatorItemMapperImpl().map(totalItem, selectedPosition)
            dotIndicatorAdapter.submitList(mappedResult)
            dotIndicatorLayoutManager.scrollToPositionWithOffset(selectedPosition, resources.getDimension(R.dimen.margin_padding_large).toInt())
        }
    }

    fun refresh() {
        viewModel.refresh()
    }

    interface OnNavigationListener {
        fun onTakeSurvey(surveyResponse: SurveyResponse)
    }

    companion object {

        private const val VISIBLE_THRESHOLD = 3

        fun newInstance(): SurveysFragment = SurveysFragment()

    }


}
