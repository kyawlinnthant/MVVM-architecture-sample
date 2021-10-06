package com.klt.gbs.ui.upcoming

import android.view.View
import androidx.fragment.app.viewModels
import com.klt.gbs.base.BaseFragment
import com.klt.gbs.databinding.FragmentUpcomingBinding
import com.klt.gbs.ui.main.MainViewModel
import com.klt.gbs.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding>(FragmentUpcomingBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun observe() {

        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :data")
                        .d(it.data?.size.toString())
                }
                Resource.Status.SUCCESS -> {
                    //todo setup to recycler view
                    binding.viewLoadingState.progress.visibility = View.GONE
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :data")
                        .d(it.data?.size.toString())
                }
                Resource.Status.ERROR -> {
                    binding.viewLoadingState.errorText.visibility = View.VISIBLE
                    binding.viewLoadingState.errorText.text = it.message
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :data")
                        .d(it.data?.size.toString())
                }
                Resource.Status.FAILURE -> {
                    binding.viewLoadingState.errorText.visibility = View.VISIBLE
                    binding.viewLoadingState.retryButton.visibility = View.VISIBLE
                    binding.viewLoadingState.errorText.text = it.message
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(UpcomingFragment::class.java.simpleName + " :data")
                        .d(it.data?.size.toString())
                }
            }
        }

    }

    override fun initUi() {
        viewModel.getListByType("upcoming")
    }
}