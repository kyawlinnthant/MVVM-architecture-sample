package com.klt.gbs.ui.popular

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.klt.gbs.base.BaseFragment
import com.klt.gbs.databinding.FragmentPopularBinding
import com.klt.gbs.model.Movie
import com.klt.gbs.ui.adapter.MovieAdapter
import com.klt.gbs.ui.main.MainViewModel
import com.klt.gbs.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(FragmentPopularBinding::inflate) {

    private var movieAdapter : MovieAdapter? = null
    private val viewModel: MainViewModel by viewModels()

    override fun observe() {

        viewModel.movieList.observe(viewLifecycleOwner) {

            when (it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    binding.viewLoadingState.progress.visibility = View.GONE
                    setupRecyclerView(it.data!!)
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
        viewModel.getList("popular")
    }
    private fun setupRecyclerView(movies: List<Movie>){
        movieAdapter = MovieAdapter(movies)
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            this.adapter = movieAdapter
        }
    }
}