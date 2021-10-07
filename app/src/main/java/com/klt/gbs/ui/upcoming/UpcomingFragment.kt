package com.klt.gbs.ui.upcoming

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.klt.gbs.base.BaseFragment
import com.klt.gbs.databinding.FragmentUpcomingBinding
import com.klt.gbs.ui.adapter.MovieListAdapter
import com.klt.gbs.ui.detail.DetailActivity
import com.klt.gbs.ui.main.MainViewModel
import com.klt.gbs.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding>(FragmentUpcomingBinding::inflate) {

    private var movieListAdapter : MovieListAdapter? = null
    private val viewModel: MainViewModel by viewModels()

    override fun observe() {

        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    binding.viewLoadingState.progress.visibility = View.GONE
                    movieListAdapter?.submitList(it.data)
                }
                Resource.Status.ERROR -> {
                    binding.viewLoadingState.errorText.visibility = View.VISIBLE
                    binding.viewLoadingState.errorText.text = it.message
                }
                Resource.Status.FAILURE -> {
                    binding.viewLoadingState.errorText.visibility = View.VISIBLE
                    binding.viewLoadingState.retryButton.visibility = View.VISIBLE
                    binding.viewLoadingState.errorText.text = it.message
                }
            }
        }

    }

    override fun initUi() {
        viewModel.getList("upcoming")
        setUpRecyclerView()
    }
    private fun setUpRecyclerView(){
        movieListAdapter = MovieListAdapter { getItemClick(it) }.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            binding.recyclerView.adapter = this
        }
    }
    private fun getItemClick(position : Int){
        val item  = movieListAdapter?.getClickItem(position)
        startActivity(DetailActivity.newIntent(requireContext(),item!!.movieId))
    }
}