package com.klt.gbs.ui.popular

import android.view.View
import androidx.fragment.app.viewModels
import com.klt.gbs.base.BaseFragment
import com.klt.gbs.databinding.FragmentPopularBinding
import com.klt.gbs.ui.main.MainViewModel
import com.klt.gbs.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(FragmentPopularBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun observe() {

        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.tag(PopularFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(PopularFragment::class.java.simpleName + " :data")
                        .d(it.data?.list?.size.toString())
                }
                Resource.Status.SUCCESS -> {
                    //todo setup to recycler view
                    binding.viewLoadingState.progress.visibility = View.GONE
                    Timber.tag(PopularFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(PopularFragment::class.java.simpleName + " :data")
                        .d(it.data?.list?.size.toString())
                }
                Resource.Status.ERROR -> {
                    binding.viewLoadingState.errorText.visibility = View.VISIBLE
                    binding.viewLoadingState.errorText.text = it.message
                    Timber.tag(PopularFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(PopularFragment::class.java.simpleName + " :data")
                        .d(it.data?.list?.size.toString())
                }
                Resource.Status.FAILURE -> {
                    binding.viewLoadingState.errorText.visibility = View.VISIBLE
                    binding.viewLoadingState.retryButton.visibility = View.VISIBLE
                    binding.viewLoadingState.errorText.text = it.message
                    Timber.tag(PopularFragment::class.java.simpleName + " :message").d(it.message)
                    Timber.tag(PopularFragment::class.java.simpleName + " :data")
                        .d(it.data?.list?.size.toString())
                }
            }
        }
    }

    override fun initUi() {
        viewModel.getListByType("popular")
    }
}